package com.example.spring14.config;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.CookieRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.example.spring14.util.JwtUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;

/*
 *  로그인 성공후에 호출될 메소드를 가지고 있는 클래스 정의하기 
 */


@Component // bean 이 된다.
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	//Jwt 토큰 유틸
	@Autowired private JwtUtil jwtUtil;
	
	
	//jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	//쿠키 유지시간
	@Value("${jwt.cookie.expiration}")
	private int cookieExpiration;
	
	
	//입력한 username 과 password 가 일치해서 로그인이 성공하면 이 메소드가 호출된다 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		Map<String, Object> claims=Map.of("role","USER", "email","aaa@naver.com");
	
    	//여기 까지 실행순서가 넘어오면 인증을 통과 했으므로 토큰을 발급해서 응답한다.
		String jwtToken=jwtUtil.generateToken(authentication.getName(), claims);
		
		//공백문자때문에 인코딩을 해서 저장해야 함
		String encoded=URLEncoder.encode("Bearer "+jwtToken, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie(jwtName, encoded);
        cookie.setMaxAge(cookieExpiration); // 쿠키 유지 시간 초 단위로 설정
        cookie.setHttpOnly(true); //웹브라우저에서 JavaScript에서 접근 불가 하도록 설정 
        cookie.setPath("/"); // 모든 경로에서 쿠키를 사용할수 있도록 설정 
        response.addCookie(cookie);
     
    	//로그인 환영 페이지로 foward 이동 (POST 방식 요청임에 주의!!!)
    	RequestDispatcher rd=request.getRequestDispatcher("/user/login-success");
    	rd.forward(request, response);
      
	}
}







