package com.example.spring16.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring16.service.GeminiService;
import com.example.spring16.service.GeminiService2;

import reactor.core.publisher.Mono;
/*
 * [ Mono<T> 객체 ]
 * 
 * - 비동기 동작을 지원하는 객체
 * - 컨트롤러에서 아직 동작이 완료되지 않은 Mono 객체를 리턴한다.
 * - 응답을 기다리지 않고 모노 객체를 바로 리턴해서 메소드가 바로 종료됨
 * - 리턴된 Mono 객체를 Spring WebFlux 가 받아서 동작이 완료되면 결과를 클라이언트에게 응답함 
 *	
 */

@RestController
public class GeminiController {
	@Autowired GeminiService2 service;
	/*
	 * POST 방식 /ask 요청을 하면서 아래의 형식과 같은 json 문자열을 전송하면 된다.
	 * 
	 * {"prompt" : "질문"}
	 */
	
	// Map 에 질문을 담고 있다.
	// Mono 객체를 리턴하고 그걸 스프링이 비동기 동작으로 해석해서 우리에게 보여줌
	@PostMapping("/ask")
	public Mono<String> ask(@RequestBody Map<String, String> request){
		//질문 얻어내기
		String prompt = request.get("prompt");
		//서비스를 이용해서 질문에 대한 답을 리턴한다.
		//모노가 리턴되고 spring 이 받아서 결과를 우리에게 뿌림
		return service.getChatResponse(prompt);
	}
	
	@PostMapping("/ask2")
	public String ask2(@RequestBody Map<String, String> request){
		//질문 얻어내기
		String prompt = request.get("prompt");
		//서비스를 이용해서 질문에 대한 답을 리턴한다.
		//모노가 리턴되고 spring 이 받아서 결과를 우리에게 뿌림
		return service.getChatResponseSync(prompt);
	}
	
	@PostMapping("/food")
	public Mono<String> food(@RequestBody Map<String, String> request){
		//질문 얻어내기
		String prompt = request.get("prompt");
		//서비스를 이용해서 질문에 대한 답을 리턴한다.
		return service.getFoodCategory(prompt);
	}

}
