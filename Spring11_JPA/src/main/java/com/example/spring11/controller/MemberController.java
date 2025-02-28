package com.example.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired private MemberService service;
	
	@GetMapping("/member/edit")
	public String edit(Integer num,Model model) {
		//해당 번호 맞는 이름 , 주소 데이터를 불러와야 함
		MemberDto dto=service.getByNum(num);
		model.addAttribute("dto",dto);
		return "member/edit";
	}
	
	@PostMapping("/member/save")
	public String save(MemberDto dto) {
		service.saveMember(dto);
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/new")
	public String memberNew() {
		
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		
		List<MemberDto> list = service.getAll();
		model.addAttribute("list",list);
		
		
		return "member/list";
	}
}
