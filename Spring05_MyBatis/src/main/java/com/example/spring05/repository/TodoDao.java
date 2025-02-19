package com.example.spring05.repository;

import java.util.List;

import com.example.spring05.dto.TodoDto;

public interface TodoDao {
	public List<TodoDto> getList();
	public void insert(TodoDto dto);
	public void delete(int id);
	public void update(TodoDto dto);
	public TodoDto getData(int id);

}
