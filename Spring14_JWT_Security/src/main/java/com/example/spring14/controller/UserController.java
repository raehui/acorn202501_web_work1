package com.example.spring14.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring14.dto.UserDto;
import com.example.spring14.util.JwtUtil;

@Controller
public class UserController {
	
	@Autowired JwtUtil jwtutil;
	//SecurityConfig 클래스에서 Bean 이 된 AuthenticationManager 객체 주입받기
	@Autowired AuthenticationManager authManager;
	
	@Secured("ROLE_ADMIN")
	//@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping("/secured/ping")
	@ResponseBody
	public String securedPing() {
		
		return "pong! pong!";
	}
	
	@GetMapping("/api/ping") //white list 에 등록 되지 않은 요청은 token 이 있어야 요청 가능하다
	@ResponseBody 
	public String ping() { //여기서는 파라미터에 달고 요청했었음
		
		return "pong";
	}
	
	//인증 매니저 객체를 통해서 직접 로그인 작업
	//토큰 응답 메서드
	@PostMapping("/api/auth")
	@ResponseBody // ResponseEntity 을 리턴할때는 생략 가능(자체 기능이 있다.)
	public ResponseEntity<String> auth(@RequestBody UserDto dto) throws Exception{
		Authentication authentication = null;
		try {
			UsernamePasswordAuthenticationToken authToken =
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
			// userName, password 로 인증 매니저 객체를 이용해서 인증을 진행한다.
			authentication=authManager.authenticate(authToken);
			
		}catch (Exception e) {
			// TODO: handle exception
			// 예외가 발생하면 인증실패(아이디 비번 틀림 등등)
			e.printStackTrace();
			// 401 UNAUTHORIZED 에러를 응답하면서 문자열 한 줄 보내기
			// 응답을 커스텀마이징 할 수 있음
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패!");
		}
		
		// Authentication 객체에는 인증된 사용자 정보가 들어 잇다. userName, role 등등
		// 현재는 role 을 하나만 부여하기 때문에 0번방에 들었있는 데이터만 불러옴
		GrantedAuthority authority = authentication.getAuthorities().stream().toList().get(0);

		// ROLE_XXX 형식
		String role = authority.getAuthority();
		// "role" 이라는 키값으로 Map 에 담기
		Map<String, Object> claims = Map.of("role", role);
		
		//sample claims
//		Map<String, Object> claims=Map.of("role","USER", "email","aaa@naver.com");
		//예외가 발생하지 않고 여기까지 실행된다면 인증을 통과한 것이다. 토큰을 발급해서 응답한다.
		String token = jwtutil.generateToken(dto.getUserName(), claims);
		//발급받은 토큰 문자열을 ResponseEntity 에 담아서 리턴한다.
		//ok 정상적인 응답이면서 
		// 왜 사용? 에러페이지를 직접 응답
		return ResponseEntity.ok("Bearer "+token); 
	}
	
	//세션 허용갯수 초과시 
	@GetMapping("/user/expired")
	public String userExpired() {
		return "user/expired";
	}	
	
	//권한 부족시 or 403 인 경우 
	@RequestMapping("/user/denied")  //GET, POST 등 모두 가능
	public String userDenied() {
		return "user/denied";
	}
	
	//ROLL_STAFF , ROLL_ADMIN 만 요청 가능
	@GetMapping("/staff/user/list")
	public String userList() {
		
		return "user/list";
	}
	//ROLL_ADMIN 만 요청 가능
	@GetMapping("/admin/user/manage")
	public String userManage() {
		
		return "user/manage";
	}
	
	// GET 방식 + POST 방식 모두 가능함
	// 일반 로그인 요청은 get 요청 , 실패 시 리다일렉트는 post 요청이르모 2개를 모두 처리하기 위해서
	@RequestMapping("/user/loginform")
	public String loginform() {
		// templates/user/loginform.html 페이지로 forward 이동해서 응답 
		return "user/loginform";
	}
	
	//로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리다일렉트 되는 요청경로 
	@GetMapping("/user/required-loginform")
	public String required_loginform() {
		return "user/required-loginform";
	}
	// POST 방식 /user/login 요청후 로그인 성공인경우 forward 이동될 url 
	@PostMapping("/user/login-success")
	public String loginSuccess() {
		return "user/login-success";
	}
	
	//로그인 폼을 제출(post) 한 로그인 프로세즈 중에 forward 되는 경로이기 때문에 @PostMapping 임에 주의!
	//로그인 폼을 처리를 하고 있는 중이기에 post맵핑으로 되어있어야 한다.
	@PostMapping("/user/login-fail")
	public String loginFail() {
		//로그인 실패임을 알릴 페이지
		return "user/login-fail";
	}	
}
