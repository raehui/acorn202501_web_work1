package com.example.spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;
import com.example.spring10.service.PostService;

@Controller
public class PostController {
	/*
	 * pageNum 이 파라미터로 넘어오지 않으면 pageNum 의 default 값은 1로 설정
	 * 검색 키워드도 파라미터로 넘어오면 PostDto 에는 condition과 keyword 가 null 이 아니다.
	 * 검색 키워드가 넘어오지 않으면 PostDto의 condition과 keyword 가 null 이다.
	 */
	@Autowired private PostService service;
	
	@GetMapping("/post/list")
	//pageNum이 null 값이 올수도 있으니 default 값을 정한다. String 타입으로 넣으면 자동으로 형변환을 해줌
	public String list(@RequestParam(defaultValue = "1")  int pageNum,PostDto search,Model model) {
		PostListDto dto=service.getPosts(pageNum, search);
		model.addAttribute("dto",dto);
		return "post/list";
	}
	
	
	
	

}
