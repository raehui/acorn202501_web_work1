package com.example.spring06.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//예외처리를 하는 컨틀롤러는 @ControllerAdvice 어노테이션을 붙여서 bean 으로 만든다.
@ControllerAdvice
public class ExceptionController {
	/*
	 * Dao 에서 SQLException 이 발생하면 자동으로 DataAccessException이 발생한다.
	 * 
	 */
	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessException(DataAccessException e,Model model) {
		model.addAttribute("message","DB 관련 작업 도중 예외가 발생했습니다.");
		return "error/data-access";
	}
	
	//RuntimeException type 예외가 발생하면 이 메소드가 실행된다.
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException e,Model model) {
		//예외 메세지를 Model 객체에 담고
		model.addAttribute("message",e.getMessage());
		// view page 에서 에러 정보를 응답한다.
		return "error/runtime";
	}
	

}
