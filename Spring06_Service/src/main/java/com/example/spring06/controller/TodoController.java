package com.example.spring06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring06.dto.TodoDto;
import com.example.spring06.repository.TodoDao;
import com.example.spring06.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService service;
		
	@GetMapping("/todo/list")
	public String list(Model model) {
		
		List<TodoDto> list=service.findAll();
		 
		model.addAttribute("list",list);
				
		return "todo/list";
	}
	
	@GetMapping("/todo/new")
	public String newForm() {
		
		return "todo/new";
	}
	
	@PostMapping("/todo/insert")
	public String insert(TodoDto dto) {		
		service.save(dto);
		
		return "todo/insert";
	}
	
	@GetMapping("/todo/edit")
	public String edit(int id,Model model) {
		TodoDto dto=service.findById(id);
		model.addAttribute("dto",dto);
		return "todo/edit";
	}
	
	@PostMapping("/todo/update")
	public String update(TodoDto dto) { //여기서 dto는 파라미터에 입력한 내용이 자동으로 담겨져 있음
		service.update(dto);
		
		return "todo/update";
	}
	
	@GetMapping("/todo/delete")
	public String delete(int id) {
		service.deleteById(id);
		return "redirect:/todo/list";
	}
	

}
