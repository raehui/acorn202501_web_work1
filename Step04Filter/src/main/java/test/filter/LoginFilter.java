package test.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/test/protected/*","/xxx/protected/*"}) //자바에서 {}은 배열임
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpSession session=request.getSession();
		
		//session 영역에 id 라는 키값으로 저장된 값이 있는지 확인한다(로그인된 사용자인지 확인)
		//로그인 하지 않았으면 null 값이 된다.
		String id=(String)session.getAttribute("id");
		//만일 로그인 하지 않았다면
		if(id==null) {
			//로그인 페이지로 리다일레트 이동 시킨다.
			String cPath=request.getContextPath();
			HttpServletResponse response=(HttpServletResponse)resp;
			response.sendRedirect(cPath+"/user/loginform.jsp");
			
		}else {//로그인한 사용자라면 
			//관여하지 않고 요청의 흐름을 이어간다.
			chain.doFilter(req, resp);
		}
	}
	
}
