package com.example.spring05.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.example.spring05.dto.TodoDto;
//Dao에 붙히는 어노테이션
@Repository
public class TodoDaoImpl implements TodoDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<TodoDto> getList() {
		List<TodoDto> list=session.selectList("todo.getList");
		
		return list;
	}

	@Override
	public void insert(TodoDto dto) {
		session.insert("todo.insert",dto);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		session.delete("todo.delete",id);
		
		
	}

	@Override
	public void update(TodoDto dto) {
		
		session.update("todo.update",dto); // id,content,regdate 담겨져 있음
		
	}

	@Override
	public TodoDto getData(int id) {
		TodoDto dto=session.selectOne("todo.getData",id);
		
		return dto;
	}
	

}
