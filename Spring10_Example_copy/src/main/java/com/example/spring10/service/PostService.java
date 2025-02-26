package com.example.spring10.service;

import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;

public interface PostService {
	public PostListDto getPosts(int pageNum, PostDto search);
	public long createPost(PostDto dto);
	public PostDto getByNum(long num);
	public PostDto getDetail(PostDto dto);
	public void updatePost(PostDto dto);
	public void deletePost(long num);
	public void manageViewCount(long num, String sessionId);
}
