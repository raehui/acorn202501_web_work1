package com.example.spring10.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostListDto { //글 목록도 나오게 하고 UI 도 나오게 데이터를 담았다.
	private List<PostDto> list;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	private int pageNum;
	private int totalRow;
	private String findQuery;
	private String condition;
	private String keyword;

}
