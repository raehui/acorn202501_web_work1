package com.example.spring07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	/*
	 * <input type="file" name="myFile">
	 * 
	 * 은 MultipartFile myFile 로 해서 업로드된 파일의 정보를 얻어내야 한다.
	 * spring이 자동으로 임시파일에 저장한다. = 서버의 파일 시스템 어딘가에 저장한다...(c:에 있는 어딘가의 파일에...)
	 * 그러면 우리가 원하는 파일로 옮기고 DB에 저장할 수도 있다.
	 * 
	 * 
	 * 
	 */
	@PostMapping("/file/upload")
	public String upload(String title, MultipartFile myFile) {
		System.out.println("title:"+title);
		System.out.println("원본파일명:"+myFile.getOriginalFilename());
		System.out.println("파일의 크기:"+myFile.getSize());
		
		return "file/upload";
	}
	
	@GetMapping("/file/new")
	public String newForm() {
		
		return "file/new";
	}
}
