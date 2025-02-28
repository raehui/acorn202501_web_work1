package com.example.spring10.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.example.spring10.dto.FileDto;

public interface FileService {
	public void saveFile(FileDto dto);
	public void updateFile(FileDto dto);
	public void deleteFile(long num);
	public List<FileDto> getFiles();
	public ResponseEntity<InputStreamResource> getResponse(long num);
}
