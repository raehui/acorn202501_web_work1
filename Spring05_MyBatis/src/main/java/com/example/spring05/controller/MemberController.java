package com.example.spring05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring05.dto.MemberDto;
import com.example.spring05.repository.MemberDao;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDao dao;
	/*
	 * 매개변수에 MemberDto type을 선언하면 form 전송되는 파라미터가 자동 추출되어서
	 * MemberDto 객체에 담긴체로 참조값이 전달된다.
	 * 요소의 이름과 MemberDto의 필드명이 동일해야 한다. - request 담을 수고가 없음
	 */
	@GetMapping("/member/update")
	public String update(MemberDto dto,Model model) {
		// 수정하고 난 후의 정보를 dto 에 담아서 수정하기
		model.addAttribute("dto",dto);		
		
		
		return "member/update";
	}
	
	@GetMapping("/member/updateform")
	public String updateform(int num,Model model) {
		MemberDto dto=dao.getData(num);
		
		model.addAttribute("dto",dto);
		
		return "member/updateform";
	}
	
	@GetMapping("/member/delete")
	public String delete(int num) {		
		dao.delete(num);
		return "member/delete";
	}
	
	@GetMapping("/member/deleteform")
	public String deleteForm() {
		return "member/deleteform";
	}
	
	
	@PostMapping("/member/insert")
	public String insert(MemberDto dto) {
		dao.insert(dto);
		return "member/insert";
	}
	
	@GetMapping("/member/new")
	public String newForm() {
		
		return "member/new";		
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		//주입받은 MemberDao 객체를 이용해서 회원목록을 얻어온다.
		List<MemberDto> list = dao.getList();
		//Model 객체에 담는다.
		model.addAttribute("list",list);
		//Thymeleaf view 페이지에서 회원 목록을 응답한다.
		
		return "member/list";
	}
}
