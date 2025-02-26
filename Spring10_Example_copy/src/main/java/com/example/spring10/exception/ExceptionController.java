package com.example.spring10.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//예외 컨트롤러는 @ControllerAdvice 어노테이션을 붙여서 bean 으로 만든다.
@ControllerAdvice
public class ExceptionController {
	
	/*
	 *  "/user/update-password" 요청을 처리하는 중에 기본 비밀번호가 일치 하지 않으면 
	 *  UserService 객체에서 PasswordException 이 발생하고 
	 *  해당 예외가 발생하면 이 메소드가 호출되고 
	 *  결과 적으로 비밀번호 수정 폼으로 다시 리다일렉트 된다.
	 */
	@ExceptionHandler(PasswordException.class)
	public String password(PasswordException pe, RedirectAttributes ra) {
		// 리다일렉트 이동된 페이지에서도 한번 사용할수 있다. 
		// Thymeleaf 에서  ${exception} 으로 참조 가능 
		ra.addFlashAttribute("exception", pe);
				
		// user/edit-password 로 요청을 다시 하라고 리다일렉트 응답하기 
		return "redirect:/user/edit-password";
	}
}






