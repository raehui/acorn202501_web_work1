package com.example.spring01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		List<String> notice= List.of("안녕","오늘은","월요일");
		
		model.addAttribute("notice",notice);
		
		
		return "home";
	}

}
