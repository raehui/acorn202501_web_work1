package com.example.spring13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //응답 json 
public class TestController {
	
	@GetMapping("/ping")
	public String ping() {
		
		return "pong";
	}

}
