package com.example.spring12.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring12.dto.PostDto;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;
import com.example.spring12.service.PostService;

@RequestMapping("/v2") // 모든 요청의 앞에 /v2을 붙인다.
@RestController // dto의 응답을 json 으로 응답한다.
public class PostController2 {
	
	@Autowired PostService service;
		
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto){
					
		return service.save(dto); //json 형식
	}
	
	// 글 목록 요청 처리
	// 방식으로 구분해서 list를 가져온다.(이게 특이한 듯...)
	@GetMapping("/posts")
	public List<PostDto> list(){

		return service.findAll();
	}
	
	@DeleteMapping("/posts/{id}")
	public PostDto delete(@PathVariable("id") long id) { // id 로 넘어오는 값은 int id 에 담겠다.
	
		return service.delete(id);
	}
	
	@PutMapping("/posts/{id}") //경로 변수로 그때마다 달라지는 id를 받아옴
	public PostDto updateAll(@PathVariable("id") long id, @RequestBody PostDto dto) { //@RequestBody 요청하는 React 의 요청에서 body 가 있기에 작성한 듯
		//경로 변수에 있는 수정할 글의 id 를 dto 에 넣어준다.
		//service 에서 매개변수가 dto 가 있기에 넣어줘야 함.
		dto.setId(id);
		
		return service.updateAll(dto);
	}
	
	// post 일부 수정 요청
	@PatchMapping("/posts/{id}")
	public PostDto update(@PathVariable("id") long id,  @RequestBody PostDto dto ) {
		dto.setId(id);
		return service.update(dto);
	}
	
	@GetMapping("/posts/{id}")
	public PostDto findPost(@PathVariable("id") long id) {		
		
		return service.find(id);
	}
	
	
}
