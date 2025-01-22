package test.filter;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import test.user.dto.SessionDto;

@WebFilter({"/member-only/*","/staff/*","/admin/*","/user/protected/*"}) 
public class LoginFilter implements Filter {
	//@WebFilter()에 명시한 패턴의 요청을 하면 아래의 메소드가 호출된다.
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		//매개변수에 전달된 객체를 이용해서 자식 type 객체의 참조값을 얻어낸다.
		//부모를 자식으로 casting
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // session 영역에서 로그인된 정보를 얻어내기 위한 객체
        HttpSession session = req.getSession();
        // session 영역에 SessionDto 라는 키값으로 저장된 값이 있으면 얻어내서 원래 type으로 casting
        SessionDto dto = (SessionDto) session.getAttribute("sessionDto");
        //만일 로그인을 하지 않았다면
        if (dto == null) { 
        	//로그인 페이지로 리다일렉트 시키는 메소드를 호출해허 리다일렉트 시킨다.
        	//리다일렉트 = 새롭게 이 경로로 요청을 다시해!
        	//그러면 여기서 요청이 오는 login-form.jsp 로 다시 이동 입력한 정보를 가지고 
            redirectToLogin(req, res);
            //메소드를 여기서 끝내기
            return;
        }   
        // role을 확인해서 /admin/* , /staff/* 요청도 필터링을 해준다.
        String role = dto.getRole();
        String requestURI = req.getRequestURI();
        //만약 admin이 아닌데 admin경로로 들어오려고 한다면 
        if (requestURI.startsWith(req.getContextPath() + "/admin") && !"ADMIN".equals(role)) {
        	//금지된 요청이라고 응답한다.
        	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
            return;
        }

        if (requestURI.startsWith(req.getContextPath() + "/staff") && !"STAFF".equals(role) && !"ADMIN".equals(role)) {
        	//금지된 요청이라고 응답한다.
        	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
            return;
        }

        // 여기까지 실행의 흐름이 넘어오면 요청의 흐름을 계속 이어간다.
        // 경로가 /admin이고 실제 admit이거나   /staff이거나 실제 staff이거나 /user이면 흐름을 계속 이어간다.
        chain.doFilter(request, response);
    }
	//리다일렉트(요청을 새로운 경로로 다시 하라는 응답) 응답을 하는 메소드
    private void redirectToLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	//원래 요청 url 을 읽어온다.
        String url = req.getRequestURI();
        //혹시 뒤에 query parameter 가 있다면 그것 역시 읽어온다. ?num=XXX&count=XXX
        //예를 들어서 로그인 전 장바구니에 담은 상품정보와 개수 정보는 로그인 후에 갖고와야 함
        String query = req.getQueryString();
        String encodedUrl = query == null ? URLEncoder.encode(url, "UTF-8") : URLEncoder.encode(url + "?" + query, "UTF-8");
        // 원래의 목적지 정보를 달고 간다.
        //로그인 페이지로 리다일렉트 이동하면서 원래 가려던 목적지 정보도 같이 넘겨준다.
        res.sendRedirect(req.getContextPath() + "/user/login-form.jsp?url=" + encodedUrl);
    }
	
}
