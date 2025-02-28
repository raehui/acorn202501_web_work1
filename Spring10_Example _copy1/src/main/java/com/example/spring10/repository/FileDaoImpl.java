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
	public int insert(FileDto dto) {
		// 파일의 번호는 files_seq라는 이름의 시퀀스로 넣기
		return session.insert("file.insert", dto);
	}

	@Override
	public int delete(long num) {
		// 번호를 이용해서 title 만 수정하도록 한다.
		return session.delete("file.delete",num);
	}

	@Override
	public int update(FileDto dto) {
		// 번호를 이용해서 title 만 수정하도록 한다.
		return session.update("file.update", dto);
	}

	@Override
	public long getSequence() {
		
		return session.selectOne("file.getSequence") ;
	}

	@Override
	public int getCount(FileDto dto) {
		
		return session.selectOne("file.getCount",dto);
	}

	@Override
	public List<FileDto> getList() {
		// 번호에 대해서 내림차순 정렬된 파일 목록
		return session.selectList("file.getList");
	}

	@Override
	public FileDto getData(long num) {
		// TODO Auto-generated method stub
		return session.selectOne("file.getData",num);
	}

}
