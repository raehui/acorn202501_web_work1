package com.example.spring12.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

@RequestMapping("/v1") // 모든 요청에 대해서 
@RestController // dto의 응답을 json 으로 응답한다.
public class PostController {
	
	@Autowired private PostRepository repo;
	
	//@ResponseBody RestController 에서는 @ResponseBody가 기본으로 json으로 응답한다.
	/*
	 * 보통 API 서버에는 클라이언트가 json 문자열을 전송한다.
	 * 해당 json 문자열에서 데이터를 추출하기 위해서는 @RequestBody 라는 어노테이션이 필요하다. 
	 * 요청 json 문자열을 dto 에 담았음
	 */
		
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto){
		// dto를 Entity 로 변경해서 save 메소드에 전달한다.
		Post post=repo.save(Post.toEntity(dto)); // 방금 저장된 정보가 들어 있는 Entity 가 리턴된다.
		
		// Entity 를 dto 로 변경해서 리턴하기
		// postEntity 에 있는 sequence 로 id 에 숫자가 부여되고 그걸 dto 로 담아서 리턴
		return PostDto.toDto(post); //json 형식
	}
	
	// 글 목록 요청 처리
	// 방식으로 구분해서 list를 가져온다.(이게 특이한 듯...)
	@GetMapping("/posts")
	public List<PostDto> list(){
		//Entity  List를 dto List 로 변경해서 리턴해준다.
		
		return repo.findAll().stream().map(PostDto::toDto).toList(); //json 형식
	}
	
	@DeleteMapping("/posts/{id}")
	public PostDto delete(@PathVariable("id") long id) { // id 로 넘어오는 값은 int id 에 담겠다.
		//삭제할 post 를 읽어온다.
		Post post= repo.findById(id).get();
		
		//id 를 이용해서 삭제한다.
		repo.deleteById(id);
		
		//이미 삭제한 데이터를 응답해 준다.
		return PostDto.toDto(post); //json 형식
	}
	
	@PutMapping("/posts/{id}") //경로 변수로 그때마다 달라지는 id를 받아옴
	public PostDto updateAll(@PathVariable("id") long id, @RequestBody PostDto dto) { //@RequestBody 는 json 에서 데이터 추출
		dto.setId(id);
		// Entity 에 id 가 null 이 아니기 때문에 insert 가 아닌 update 가 수행된다.
		repo.save(Post.toEntity(dto));
		
		return dto;
	}
	
}
