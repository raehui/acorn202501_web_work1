package com.example.spring03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring03.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	//@ResponseBody 가 없으니 위치를 리턴한다.
	@GetMapping("/")
	public String home(Model model) {
		//공지 사항
		List<String> notice=List.of("Spring Boot 시작입니다" , "안녕하세요","반가워요");
		
		//공지사항을 "notice" 라는 키값으로 담기
		model.addAttribute("notice",notice);
		
		//view page 의 위치 (응답을 위임할 jsp 페이지의 위치) 를 리턴해 준다.
		// "/WEB-INF/views/" + home + ".jsp"
		return "home";
	}
	
	@GetMapping("/fortune")
	public String fortune(HttpServletRequest req) {
		//오늘이 운세를 얻어오는 비즈니르 로직 수행
		String fortuneToday="동쪽으로 가면 귀인을 만나요!";
		
		//오늘의 운세를 request scope 에 담고
		req.setAttribute("fortuneToday", fortuneToday);
		//view page로 forward 이동해서 응답하기
		return "fortune";
	}
	
	@GetMapping("/fortune2")
	public String fortune(Model model) {
		//오늘이 운세를 얻어오는 비즈니르 로직 수행
		String fortuneToday="동쪽으로 가면 귀인을 만나요!";
		
		//Model 객체에 응답에 필요한 데이터를 담으면 자동으로 request 영역에 담긴다.
		model.addAttribute("fortuneToday",fortuneToday);
		//view page로 forward 이동해서 응답하기
		return "fortune";
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		//lombok 을 활용해서 MemberDto 객체를 만들고
		MemberDto dto=MemberDto.builder()
				.num(1)
				.name("김구라")
				.addr("노량진")
				.build();
		//model 객체에 "dto" 라는 키값으로 MemberDto 객체를 담고
		model.addAttribute("dto",dto);
		
		//  WEB-INF/views/member/info.jsp 페이지로 forward 이동해서 응답하기
		return "member/info";
	}
	
}
