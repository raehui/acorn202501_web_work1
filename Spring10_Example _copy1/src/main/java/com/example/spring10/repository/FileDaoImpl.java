package com.example.spring10.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.PostDto;
@Repository
public class FileDaoImpl implements FileDao {
	
	@Autowired SqlSession session;
	
	@Override
	public List<FileDto> getList(FileDto dto) {
		
		return session.selectList("file.getList", dto);
	}

	@Override
	public int insert(FileDto dto) {
		
		return session.insert("file.insert", dto);
	}

	@Override
	public int delete(long num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(FileDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSequence() {
		
		return session.selectOne("file.getSequence") ;
	}

	@Override
	public int getCount(FileDto dto) {
		
		return session.selectOne("file.getCount",dto);
	}

}
