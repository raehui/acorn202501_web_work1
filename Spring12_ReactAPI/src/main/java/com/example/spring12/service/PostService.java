package com.example.spring12.service;

import java.util.List;

import com.example.spring12.dto.PostDto;

public interface PostService {
	public PostDto save(PostDto dto);
	public List<PostDto> findAll();
	public PostDto delete(long id);
	public PostDto updateAll(PostDto dto);
	public PostDto update(PostDto dto);
	public PostDto find(long id);
}
