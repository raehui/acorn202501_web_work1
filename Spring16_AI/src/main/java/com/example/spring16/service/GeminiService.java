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
	/*
	 * 클라이언트가 입력한 내용을 이용해서 Gemini 에 질문할 새로운 질문을 만들어낸다.
	 * 
	 * 대답의 형식을 구체적으로 제한한다.
	 * 요청을 한 번 더 가공해서 보낸다.
	 */
	public Mono<String> getFoodCategory(String food){
		String str = """
			클라이언트가 입력한 음식: "%s" 
			
			해당 음식의 카테고리를 반환해줘.
			반환할 문자열은 ["한식", "중식", "일식", "양식", "기타등등"] 중에서 하나만 반환해줘야 해
			다른 설명 없이 오직 카테고리 이름만 반환해서 마크다운 형식이 아닌 {"category":"카테고리 이름"} 형식으로 답변해줘.
		""".formatted(food);
		
		return getChatResponse(str);
	}
	
	//질문을 던지면 Mono<String> 객체를 리턴하는 메소드
	//질문을 받아옴
	public Mono<String> getChatResponse(String prompt){
		/*
		 * { "contents": [{ "parts":[{"text": "Explain how AI works"}] }] }
		 */
		//요청의 body 구성하기
		//위의 형식에 맞게 요청의 body 구성하기
		Map<String, Object> requestBody=Map.of(
			"contents", List.of(
				Map.of("parts", List.of(Map.of("text", prompt)))
			)
		);
		// Mono 객체를 요청을 하고 응답이 오기 전에 바로 리턴된다.
		Mono<String> mono=webClient.post()
				.uri(uriBuilder -> uriBuilder.path(":generateContent") //서비스 선택
					.queryParam("key", apiKey) //api 키
					.build()
				)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(requestBody) // Map 에 담긴 내용이 json 문자열로 자동 변환된다.
				.retrieve()
				.bodyToMono(String.class) // Mono<String> type 을 반환하기 위한 설정
				.doOnNext(responseBody -> { // 밑에 과정 부터 응답 과정
					System.out.println("Gemini 가 응답함!");
					//응답된 문자열을 이용해서 무언가 할 동작이 잇으면 여기서 한다.
					System.out.println(responseBody);
					})
				.flatMap(responseBody -> {
					try {
						return Mono.just(extractResponse(responseBody)); // 우리가 원하는 데이터만을 Mono 에 담아서 응답
					}catch(Exception e) {
						return Mono.error(new RuntimeException("JSON 파싱오류", e));
					}
				});	
		System.out.println("서비스 메소드가 리턴됨");
		return mono;
	}
	private final Gson gson = new Gson();
	// json 으로 응답된 내용을 추출 및 병합해서 하나의 String 으로 얻어내는 유틸 메소드
	// 우리가 원하는 문자열만을 빼내는 과정
    private String extractResponse(String responseJson) {
        try {
        	// 응답된 json 문자열을 파싱해서 GeminiResponse 객체로 변환
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







