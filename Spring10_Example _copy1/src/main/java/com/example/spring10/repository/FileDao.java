package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.PostDto;

public interface FileDao {
	//리스트 얻어오기, 추가, 삭제, 수정
	public List<FileDto> getList(FileDto dto);
	public int insert(FileDto dto);
	public int delete(long num);
	public int update(FileDto dto);
	//시퀀스 얻어오기
	public long getSequence();
	//전체 파일의 갯수 얻어오기
	public int getCount(FileDto dto);

}
