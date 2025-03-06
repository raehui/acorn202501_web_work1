package com.example.spring12.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring12.dto.PostDto;
import com.example.spring12.dto.PostPageResponse;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;



@Service
public class PosrServiceImpl implements PostService{
	// JpaRepository 객체를 주입 받는다.
	@Autowired PostRepository repo;
	
	//한페이지에 몇개의 row 를 출력할 것 인지에 대한 값
	final int PAGE_ROW_COUNT=10;
	//페이징 처리 UI에 페이지번호를 몇개씩 출력할지에 대한 값
	final int PAGE_DISPLAY_COUNT=5;
	
	@Override
	public PostDto save(PostDto dto) {
		// dto를 Entity 로 변경해서 저장하고 결과를 Entity 로 리턴받는다.
		Post post=repo.save(Post.toEntity(dto));
		// 결과 Entity 를 dto로 변경해서 리턴한다.
		return PostDto.toDto(post);
	}

	@Override
	public List<PostDto> findAll() {
		// Entity list 를 얻어내서
		List<Post> list=repo.findAll();
		// dto의 list 로 변경해서 리턴한다.
		return list.stream().map(PostDto::toDto).toList();
	}

	@Override
	public PostDto delete(long id) {
		//없는 id 를 입력하면 예외가 발생하게끔
		//삭제할 Entity 를 미리 얻어낸다.(없으면 예외가 발생하고 있으면 post 가 리턴된다.)
		//Post post= repo.findById(id).orElseThrow();
		
		//만일 원하는 예외를 발생 시키고 싶으면 (리턴한 RuntimeException 이 자동으로 throw 된다 )
		Post post= repo.findById(id).orElseThrow(()->new RuntimeException("post not found!"));
		
		
		//삭제 작업을 하고
		repo.deleteById(id);
		//삭제된 Entity  를 dto로 변경해서 리턴한다.
		return PostDto.toDto(post);
	}
	
	@Override
	public PostDto updateAll(PostDto dto) {
		//Entity 의 id를 제외한 모든 필드를 수정한다.
		Post post=repo.save(Post.toEntity(dto));
		//수정된 Entity 를 dto 로 변경해서 리턴하기
		return PostDto.toDto(post);
	}
	
	/*
	 * JPA 에서 동일한 Transaction 내에서 특정 Entity 를 find 한 다음
	 * 해당 Entity 의 setter 메소드를 이용해서 특정 필드를 수정하면
	 * Transaction 이 종료 될때 Entity 가 변경되었는지를 확인해서 
	 * 자동으로 DB에 변경된 내용만 수정 받영한다.
	 * 
	 * 자동으로 dirty check 해서 더러워지면 update 를 해주는 sql문이 자동으로 작성된다.
	 * 즉 내가 save 를 안해도 자동으로 해줌
	 */
	//일부 수정
	@Transactional //단일 메소드로 묶어줌
	// update() 메소드를 단일 transaction 으로 묶어준다.(스프링 프레임워크 패키지꺼)
	// 모든 작업이 성공해야 commit 되고 하나라도 실패하면 rollback
	@Override
	public PostDto update(PostDto dto) {
		//post id 를 이용해서 수정할 Entity 를 얻어온다.
		Post post = repo.findById(dto.getId()).orElseThrow();
		// title 이 null 이 아닐 때만 title 수정
		if(dto.getTitle()!= null) {
			post.setTitle(dto.getTitle());
		}
		// 위의 코드를 한줄로 표현하면 아래와 같다 
		// null 일 수 있지만 dto의 title이 존재한다면 post의 title 로 넣어라
		//Optional.ofNullable(dto.getTitle()).ifPresent(post::setTitle);
		
		// author 가 null 이 아닐 때만 author 수정
		if(dto.getAuthor() != null) {
			post.setAuthor(dto.getAuthor());
		}
		
		//Optional.ofNullable(dto.getAuthor()).ifPresent(post::setAuthor);
		
		return PostDto.toDto(post);
	}
	
	@Override
	public PostDto find(long id) {
		// post 를 찾고 없으면 예외 발생 시키기
		Post post=repo.findById(id).orElseThrow();
		// 찾은 Entity 를 dto 로 변경해서 리턴하기
		return PostDto.toDto(post);
	}

	@Override
	public PostPageResponse findPage(int pageNum) {
		// 한 페이지에 몇개씩 표시할 것인지
		// id 컬럼에 대하여 내림차순 정렬하라는 정보를 가지고 있는 Sort 객체 만들기
		Sort sort = Sort.by(Sort.Direction.DESC,"id");
		// JPA 는 페이징에 관한 메서드가 내장...그러면 pageable 을 구하기
		//pageNum 과 PAGE_ROW_COUNT 와 Sort 객체를 전달해서 Pageable 객체를 얻어낸다.
		Pageable pageable = PageRequest.of(pageNum-1,PAGE_ROW_COUNT,sort); 
		// 해당 페이지 정보를 얻어와서 
		//Pageable 객체를 전달해서 해당 페이지 정보(row)를 얻어온 다음
		Page<Post> page=repo.findAll(pageable);
		List<PostDto> list =page.stream().map(PostDto::toDto).toList();
		//새로운 dto 나 map 을 만들어서 담아서 React 에 전달
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 페이지의 갯수 구하기(Page 객체에 이미 계산되어서 들어 있다.)
		int totalPageCount=page.getTotalPages();
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		//위의 정보를 이용해서 PostPageResponse 객체에 담아서 리턴한다.
		return PostPageResponse.builder()
				.list(list)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.pageNum(pageNum)
				.build()
				;
	}

}
