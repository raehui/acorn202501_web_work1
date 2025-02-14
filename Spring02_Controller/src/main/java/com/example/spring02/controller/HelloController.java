package com.example.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/*
 * 클라이언트의 요청을 처리할 컨트롤러를 정의하고 bean 으로 만들기
 */
// 왜 scan 이 되는가? com.example.spring02의 하위패키지이기 때문에..
@Controller
public class HelloController {
	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		//String type 을 리턴하면서 메소드 @ResponseBody 어노테이션을 붙여 놓으면
		//여기서 리턴한 문자열이 클라이언트에게 그래도 출력한다.
		return "Nice to meet you!";
	}
	
	@ResponseBody
	@GetMapping("/fortune1")
	public String fortune1(){
		return "당신의 마법사!";
	}

}
