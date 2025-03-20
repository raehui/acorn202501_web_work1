package com.example.spring15.dto;

import java.util.List;

import com.example.spring15.dto.GeminiResponse.Content;
import com.example.spring15.dto.GeminiResponse.Part;

import lombok.Data;

/*
 * { "contents": [{ "parts":[{"text": "Explain how AI works"}] }] } 라는 구조 만듬
 */

@Data
public class GeminiRequest {
	private List<Content> contents;
	
	@Data
	public static class Content {
		private List<Part> parts;
	}
	
	@Data
	public static class Part {
		private String text;
	}

}
