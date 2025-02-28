package com.example.spring10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;
import com.example.spring10.repository.PostDao;
@Service
public class PostServiceImpl implements PostService{
	
	@Autowired private PostDao postDao;

	// 한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT = 5;
	// 하단에 글 페이지 번호를 5개를 표시하기 위해서 작성한다.
	// 하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT = 5;
	
	/*
	 * pageNum 과 검색 조건, 키워드가 담겨 있을 수 잇는 PostDto를 전달하면
	 * 해당 글 목록을 리턴하는 메소드 
	 */
	@Override
	public PostListDto getPosts(int pageNum, PostDto search) { 
		
		// 페이지에 따라서 그 페이지에 보여줄 행의 내용을 변경을 위한 조건을 만듬
		// 보여줄 페이지의 시작 ROWNUM 
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//페이징 처리
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int totalRow=postDao.getCount(search);
		//전체 페이지의 갯수 구하기
		//몇 페이지로 페이징 처리 = 글의 갯수/하나의 페이지에 보일 글의 수
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		// 우리가 설정한 값으로 구한 endPageNum와 글의 목록으로 구한 totalPageCount을 비교해서 보정해준다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}	
		
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		//글 목록 얻어오기
		List<PostDto> list=postDao.getList(search);
		
		String findQuery="";
		if(search.getKeyword() != null) {
			findQuery="&keyword="+search.getKeyword()+"&condition="+search.getCondition();
		}
		// 글 목록 페이지에서 필요한 정보를 모두 PostListDto에 담는다.
		
		PostListDto dto=PostListDto.builder()
				.list(list)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.pageNum(pageNum)
				.totalRow(totalRow)
				.findQuery(findQuery)
				.condition(search.getCondition())
				.keyword(search.getKeyword())
				.build();
		
		return dto;
	}

	@Override
	public long createPost(PostDto dto) {
		// 제목 내용 작성자 글번호 얻어와서 dto에 얻어오고
		// 작성자
		String writer=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		//글번호
		long num=postDao.getSequence();
		dto.setNum(num);
		// 그걸 DB에 저장하기
		postDao.insert(dto);
		return num;		
		
	}

	@Override
	public PostDto getDetail(PostDto dto) {
		
		return postDao.getDetail(dto);
	}

}
