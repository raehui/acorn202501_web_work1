package com.example.spring10.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.FileDto;
import com.example.spring10.service.FileService;

@Controller
public class FileController {
	
	@Autowired private FileService service;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(long num){
		
		return service.getResponse(num);
		
	}
	
	
	@PostMapping("/file/save")
	public String save(FileDto dto, RedirectAttributes ra) {
		// dto 에는 title 과 myFile 정보가 들어 있다.
		service.saveFile(dto);
		
		ra.addFlashAttribute("msg", "파일을 성공적으로 업로드 했습니다.");
		
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/new")
	public String newFile() {
		
		return "file/new";
	}
	
	@GetMapping("/file/list")
	public String list(Model model) {
		//서비스를 이용해서 파일 목록 얻어오기
		List<FileDto> list=service.getFiles();
		//응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("list", list);
		//template 페이지에서 응답하기 
		return "file/list";
	}
}










