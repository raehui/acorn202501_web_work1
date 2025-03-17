package com.example.spring15.dto;

import lombok.Data;

/*
 * 댓글 목록 요청시 전달되는 파라미터를 담은 객체를 만들기 위해
 */

@Data
public class CommentListRequest {
	private int pageNum;
	private long postNum;
}
