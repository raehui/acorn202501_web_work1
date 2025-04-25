package com.example.spring18.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// @Data 는 settet, getter, toString 메소드를 자동으로 만들어 준다.
// toString() 메소드가 만들어져 있기때문에 콘솔창에 GalleryDto 객체를 출력하면
// 필드에 들어 있는 숫자 혹은 문자 데이터를 확인할 수 잇다.
@Data
public class GalleryDto {
	private long id;
	private String uploader;
	private String caption;
	private String uploadedAt;
	private MultipartFile image;
}
