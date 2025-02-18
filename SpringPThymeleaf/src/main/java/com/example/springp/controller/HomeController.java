package com.example.springp.controller;

import java.util.List;

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
	
	@GetMapping("/todolist")
	public String todoList(Model model) {
		TodoDto dto1=TodoDto.builder().num(1).title("밥먹기").content("두부먹기").build();
		TodoDto dto2=TodoDto.builder().num(2).title("설거지 하기").content("물통도 설거지 하기").build();
		TodoDto dto3=TodoDto.builder().num(3).title("샤워하기").content("필링하기").build();
		
		List<TodoDto> list=List.of(dto1,dto2,dto3);
		
		model.addAttribute("list",list);
		
		return "todo/list";
	}
	
	@GetMapping("/include")
	public String include() {
		return "include/header";
	}
	
	@GetMapping("/javascript")
	public String javascript(Model model) {
		TodoDto dto=TodoDto.builder()
				.num(1)
				.title("밥먹기")
				.content("빵말고 꼭 밥 먹기!")
				.build();
		model.addAttribute("dto",dto);
		
		TodoDto dto1=TodoDto.builder().num(1).title("밥먹기").content("두부먹기").build();
		TodoDto dto2=TodoDto.builder().num(2).title("설거지 하기").content("물통도 설거지 하기").build();
		TodoDto dto3=TodoDto.builder().num(3).title("샤워하기").content("필링하기").build();
		
		List<TodoDto> list=List.of(dto1,dto2,dto3);
		
		model.addAttribute("list",list);
		
		model.addAttribute("name","래정띠");
		model.addAttribute("age",27);
		model.addAttribute("company", "삼양");
		
		return "test/javascript";
	}
}
