package com.example.spring06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring06.dto.TodoDto;
import com.example.spring06.repository.TodoDao;
@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoDao dao;

	@Override
	public List<TodoDto> findAll() {
		List<TodoDto> list=dao.getList();
		return list;
	}

	@Override
	public TodoDto findById(int id) {
		TodoDto dto=dao.getData(id);
		return dto;
	}

	@Override
	public void save(TodoDto dto) {
		dao.insert(dto);
		
	}

	@Override
	public void update(TodoDto dto) {
		dao.update(dto);
		
	}

	@Override
	public void deleteById(int id) {
		dao.delete(id);
		
	}
	

}
