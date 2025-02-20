package com.example.spring05.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessException(DataAccessException e,Model model) {
		model.addAttribute("message","DB 접속이 잘못되었습니다.!");
		return "error/data-access";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException e,Model model) {
		model.addAttribute("message","실행 중에 오류가 발생했습니다.");
		return "error/run";
	}
	
}
