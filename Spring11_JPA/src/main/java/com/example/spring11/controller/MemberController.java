package com.example.spring11.controller;

import java.nio.file.spi.FileSystemProvider;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired private MemberService service;
	
	
//	@GetMapping("/member/delete")
//	public String delete(MemberDto dto) {
//		service.delete(dto.getNum());
//		return "redirect:/member/list";
//	}
	 
	
	@GetMapping("/member/delete")
	public String delete(int num) {
		service.deleteMember(num);
		return "redirect:/member/list";
	}

	
//	@PostMapping("/member/update")
//	public String update(RedirectAttributes ra,MemberDto dto) {
//		
//		service.update(dto);
//		System.out.println(dto.getNum());
//		ra.addFlashAttribute("updateMessage", dto.getNum()+" 번 회원을 수정했습니다.");
//		
//		return "redirect:/member/list";
//	}
	
	@PostMapping("/member/update")
	public String update(MemberDto dto, RedirectAttributes ra) {
		ra.addFlashAttribute("updateMessage", dto.getNum()+" 번 회원을 수정했습니다.");
		service.updateMember(dto);
		return "redirect:/member/list";
	}
	
	
	/*
	 * @GetMapping("/member/edit") public String edit(Integer num,Model model) {
	 * //해당 번호 맞는 이름 , 주소 데이터를 불러와야 함 MemberDto dto=service.getByNum(num);
	 * model.addAttribute("dto",dto); return "member/edit"; }
	 */
	
	@GetMapping("/member/edit")
	public String edit(int num, Model model) {
		
		MemberDto dto= service.getMember(num);
		model.addAttribute("dto",dto);
		
		return "member/edit";
	}
	
	
	@PostMapping("/member/save")
	public String save(MemberDto dto,RedirectAttributes ra) {
		service.saveMember(dto);
		ra.addFlashAttribute("saveMessage", "회원을 성공적으로 저장했습니다.");
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
