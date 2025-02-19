package com.example.spring06.service;

import java.util.List;

import com.example.spring06.dto.TodoDto;

public interface TodoService {
	public List<TodoDto> findAll();
	public TodoDto findById(int id);
	public void save(TodoDto dto);
	public void update(TodoDto dto);
	public void deleteById(int id);
	
	
	

}
