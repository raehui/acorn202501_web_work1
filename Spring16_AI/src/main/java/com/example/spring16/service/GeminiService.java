package com.example.spring16.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import reactor.core.publisher.Mono;

@Service
public class GeminiService {
	// gemini api 키를 custom.properties 파일에서 읽어오기 
	@Value("${gemini.key}") 
	private String apiKey;
	// google ai 에 요청을 할 클라이언트 객체
	private WebClient webClient;
	
	//생성자
	public GeminiService(WebClient.Builder builder) {
		this.webClient = builder
			.baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash")
			.build();
	}
	
	public Mono<String> getFoodCategory(String food){
		String str = """
			클라이언트가 입력한 음식: "%s" 
			
			해당 음식의 카테고리를 반환해줘.
			반환할 문자열은 ["한식", "중식", "일식", "양식", "기타등등"] 중에서 하나만 반환해줘야 해
			다른 설명 없이 오직 카테고리 이름만 반환해서 마크다운 형식인 {"category":"카테고리 이름"} 형식으로 답변해줘.
		""".formatted(food);
		
		return getChatResponse(str);
	}
	
	//질문을 던지면 Mono<String> 객체를 리턴하는 메소드
	//Mono 는 답변이 오는 과정을 비동기로 도와줌
	public Mono<String> getChatResponse(String prompt){
		//요청의 body 구성하기
		Map<String, Object> requestBody=Map.of(
			"contents", List.of(
				Map.of("parts", List.of(Map.of("text", prompt)))
			)
		);
		
		Mono<String> mono=webClient.post()
				.uri(uriBuilder -> uriBuilder.path(":generateContent")
					.queryParam("key", apiKey)
					.build()
				)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(requestBody)
				.retrieve()
				.bodyToMono(String.class)
				.doOnNext(responseBody -> System.out.println(responseBody))
				.flatMap(responseBody -> {
					try {
						return Mono.just(extractResponse(responseBody));
					}catch(Exception e) {
						return Mono.error(new RuntimeException("JSON 파싱오류", e));
					}
				});		
		return mono;
	}
	private final Gson gson = new Gson();
	// json 으로 응답된 내용을 추출 및 병합해서 하나의 String 으로 얻어내는 유틸 메소드
    private String extractResponse(String responseJson) {
        try {
        	
            GeminiResponse geminiResponse = gson.fromJson(responseJson, GeminiResponse.class);

            if (geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()) {
                GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);

                if (firstCandidate.getContent() != null && firstCandidate.getContent().getParts() != null) {
                    return firstCandidate.getContent().getParts().stream()
                            .map(GeminiResponse.Part::getText)
                            .reduce((a, b) -> a + "\n" + b) // 여러 개의 응답을 합침
                            .orElse("응답 없음");
                }
            }
        } catch (JsonSyntaxException e) {
            return "JSON 파싱 오류: " + e.getMessage();
        }
        return "응답 없음";
    }
	
}







