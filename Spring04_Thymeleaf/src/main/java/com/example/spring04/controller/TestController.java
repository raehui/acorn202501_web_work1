package com.example.spring04.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring04.dto.MemberDto;

@Controller
public class TestController {
	
	@GetMapping("/sequence")
	public String sequence() {
		return "test/sequence";
	}
	
	@GetMapping("/member/list")
	public String memberList(Model model) {
		
		//DB 에서 select 한 결과라고 가정
		MemberDto dto1=MemberDto.builder().num(1).name("김구라").addr("노량진").build();
		MemberDto dto2=MemberDto.builder().num(2).name("원숭이").addr("행신동").build();
		MemberDto dto3=MemberDto.builder().num(3).name("해골").addr("상도동").build();
		
		//readonly list
		List<MemberDto> list=List.of(dto1,dto2,dto3);
		
		model.addAttribute("list",list);
		
		return "member/list";
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		// lombok 의 기능을 이용해서 MemberDto 객체를 얻어내고
		MemberDto dto=MemberDto.builder()
				.num(1)
				.name("김구라")
				.addr("노량진")
				.build();
		//해당 데이터를 Model 객체에 담고
		//데이터를 html에 박기위해서 담았음
		model.addAttribute("dto",dto);
		//view page로 이동해서 응답하기
		return "member/info";
	}
	
	@GetMapping("/notice")
	public String notice(Model model) {
		List<String> noticeList=List.of("Thymeleaf view engine 을 배워 보아요!", "안녕", "헬로","나는","래희야");
		
		model.addAttribute("noticeList",noticeList);
		
		return "post/notice";
	}
	

	
	
}
