package com.example.spring10.service;

import org.springframework.stereotype.Service;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;
@Service
public interface FileService {
	public FileListDto getFiles(int pageNum,FileDto search);
	public long createFile(FileDto dto);
	
	
	

}
