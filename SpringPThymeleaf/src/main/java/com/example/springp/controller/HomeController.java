package com.example.springp.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springp.dto.TodoDto;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	@GetMapping("/todo")
	public String todo(Model model) {
		TodoDto dto=TodoDto.builder()
				.num(1)
				.title("밥먹기")
				.content("빵말고 꼭 밥 먹기!")
				.build();
		model.addAttribute("dto",dto);
		return "todo/info";
		
		
	}
}
