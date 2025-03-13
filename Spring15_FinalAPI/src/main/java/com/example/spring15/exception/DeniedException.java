package com.example.spring15.exception;

// 거부된 요청일때 발생시킬 Exception 정의하기
//부모가 .getMessage 메소드를 갖고있기에 
public class DeniedException extends RuntimeException{
	
	public DeniedException(String msg) {
		super(msg);
	}

}
