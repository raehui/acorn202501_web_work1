package com.example.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring11.dto.EmpDeptDto;
import com.example.spring11.dto.EmpDto;
import com.example.spring11.entity.Emp;
import com.example.spring11.repository.EmpRepository;

@Controller
public class EmployController {
	//테스트의 편의를 위해 서비스를 만들지 않고 바로 Repository 객체 활용하기
	@Autowired 
	private EmpRepository empRepo;
	
	@GetMapping("/emp/view")
	public String view(int empno,Model model) {
		Emp emp=empRepo.findById(empno).get();
		
		model.addAttribute("emp",EmpDto.toDto(emp));
		return "emp/view";
	}
	
	
	@GetMapping("/emp/list2")
	public String list2(Model model) {
		
		List<EmpDeptDto> list= empRepo.findAll().stream().map(EmpDeptDto::toDto).toList();
		model.addAttribute("list",list);
		return "emp/list2";
	}
	
	@GetMapping("/emp/list")
	public String list(Model model) {
		// List<Emp> 를 stream() 을 이용해서 List<EmpDto> 로 만든다.
		
		// @Id(empno) 칼럼에 대해서 오름차순 정렬된 결과
		//List<EmpDto> list = empRepo.findAll().stream().map(EmpDto::toDto).toList();
		
		// ename 칼럼에 대해서 오름차순 정렬된 결과
		List<EmpDto> list = empRepo.findAllByOrderByEnameAsc().stream().map(EmpDto::toDto).toList();		
		
		model.addAttribute("list",list);
		return "emp/list";
	}
}
