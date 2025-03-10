package com.example.spring14.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.CookieRequestCache;

import com.example.spring14.filter.JwtFilter;

import jakarta.servlet.http.Cookie;

@Configuration //설정 클래스라고 알려준다. 
@EnableWebSecurity //Security 를 설정하기 위한 어노테이션
public class SecurityConfig {
	// jwt 를 쿠키로 저장할 때 쿠기의 이름
	@Value("${jwt.name}")
	private String jwtName;
	
	@Autowired JwtFilter JwtFilter; 
	
	/*
	 *  매개변수에 전달되는 HttpSecurity 객체를 이용해서 우리의 프로젝트 상황에 맞는 설정을 기반으로 
	 *  만들어진 SecurityFilterChain 객체를 리턴해주어야 한다.
	 *  또한 SecurityFilterChain 객체도 스프링이 관리하는 Bean 이 되어야 한다.  
	 */
	@Bean //메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, 
			AuthSuccessHandler successHandler,
			CookieRequestCache cookieCache) throws Exception{
		String[] whiteList= {"/", "/play", "/user/loginform", "/user/login-fail", "/user/expired"};
		
		httpSecurity
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(config ->
			config
				.requestMatchers(whiteList).permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
				//그 이외의 나머지는 로그인을 해야 접속 가능
				.anyRequest().authenticated()
		)
		.formLogin(config ->
			config
				//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정 
				.loginPage("/user/required-loginform")
				//로그인 처리를 할때 요청될 url 설정 ( spring security 가 login 처리를 대신 해준다)
				.loginProcessingUrl("/user/login")
				//로그인 처리를 대신 하려면 어떤 파라미터명으로 username 과 password 가 넘어오는지 알려주기 
				//근디 Spring DB를 어떻게 자동으로 정보를 추출할까? Bean으로 만들어 놓았음 AuthenticationManager (밑으로 가보삼)
				.usernameParameter("userName")
				.passwordParameter("password")
				.successHandler(new AuthSuccessHandler()) //로그인 성공 핸들러 등록
				.failureForwardUrl("/user/login-fail") //실패시 forward 이동을 하기에 주소창에 나오지 않음
				.permitAll() //위에 명시한 모든 요청경로를 로그인 없이 요청할수 있도록 설정 
		)
		.logout(config ->
			config
				.logoutUrl("/user/logout")//Spring Security 가 자동으로 로그아웃 처리 해줄 경로 설정
				.logoutSuccessHandler((request, response, auth)->{ // 로그아웃 프로세스를 람다로 생성
					Cookie cook=new Cookie(jwtName, null);
					//쿠키를 삭제하기 위해 setMaxAge(0)
					cook.setMaxAge(0);
					cook.setPath("/");
					response.addCookie(cook);
					//쿠키 삭제후에 최상위 경로로 리다일렉트 이동
					response.sendRedirect(request.getContextPath()+"/");
				})
				.permitAll()
		)
		.exceptionHandling(config ->
			//403 forbidden 인 경우 forward 이동 시킬 경로 설정 , forward 이동시 클라이언트이 주소창에 안보임
			config.accessDeniedPage("/user/denied")
		)
		.sessionManagement(config ->
			//세션을 사용하지 않도록 설정한다.
			config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		)
		.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class)
		.requestCache(config -> config.requestCache(cookieCache));
		//설정 정보를 가지고 있는 httpSecurity 객체의 build() 메소드를 호출해서 리턴되는 객체를 리턴해준다.
		return httpSecurity.build();
	}
	
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
	@Bean
	PasswordEncoder passwordEncoder() { 
		//여기서 리턴해주는 객체도 bean 으로 된다.
		return new BCryptPasswordEncoder();
	}
	//인증 메니저 객체를 bean 으로 만든다. (Spring Security 가 자동 로그인 처리할때도 사용되는 객체)
	//여기서 UserDetailsService는 우리가 만들어 놓아서 처리 -> CustomUserDetailsService으로 가보삼
	//우리가 만든 걸 기반으로 인증을 진행한다.
	//여기서 입력한 비밀번호와 DB의 비밀번호를 일치하는지 검사를 내재적으로 진행함(코드는 명시적으로 작성되어있지 않음)
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http,
			BCryptPasswordEncoder encoder, UserDetailsService service) throws Exception{
		//적절한 설정을한 인증 메니저 객체를 리턴해주면 bean 이 되어서 Spring Security 가 사용한다 
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(service)
				.passwordEncoder(encoder)
				.and()
				.build();
	}
	//쿠키 케시를 bean 으로 만든다.
	@Bean
	CookieRequestCache getCookieRequestCache() {
		return new CookieRequestCache();
	}
	
}
