<%@page import="java.net.URLEncoder"%>
<%@page import="test.user.dto.SessionDto"%>
<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 폼 전송되는 userName, password 를 읽어와서
	String userName=request.getParameter("userName");
	String password=request.getParameter("password");
	//2. id 에 해당하는 회원정보를 UsreDao 객체를 이용해서 얻어와서
	UserDto dto=UserDao.getInstance().getData(userName);
	//=> 입력한 아이디로 DB에서 일치하는 dto를 가지고 옴
			
	//3. 실제로 존재하는 아이디이고 존재한다면 비밀번호도 일치하는지 비교해서
	boolean isLoginSuccess=false; //밑에서 로그인의 성공여부를 알고싶어서 상태값을 정했다.
	
	if(dto!=null){ //dto가 존재한다 = 회원정보가 일단 존재함
		//비밀번호 확인 해서 비밀번호까지 일치 한다면 로그인 처리를 한다.
		if(dto.getPassword().equals(password)){
			//로그인 처리를 한다.(로그인된 user 정보를 session 영역에 담는다.)
			//딱 필요한 정보만 session 영역에 담는다.-> session Dto 정의
			SessionDto sessionDto=new SessionDto();
			sessionDto.setNum(dto.getNum());
			sessionDto.setUserName(dto.getUserName());
			sessionDto.setRole(dto.getRole());
			//=>로그인에 필요한 정보만 session 그릇에 담기
			
			//로그인 처리해주기
			//setAttribute로 키: value로 담는다.
			session.setAttribute("sessionDto", sessionDto);
			//로그인과 비밀번호가 일치하여 로그인에 성공했다.
			isLoginSuccess=true;
		}
		
	}
	
	//로그인 후에 가야할 목적지 정보
	String url=request.getParameter("url");
	//로그인 실패를 대비해서 목적지 정보를 인코딩한 결과도 준비한다.
	String encodedUrl=URLEncoder.encode(url,"UTF-8");
	
	//일치하면 로그인 처리 후 응답, 일치하지 않으면 일치하지 않다고 응답
	
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
</head>
<body>
	<div class="container">
		<%if(isLoginSuccess){ %>
			<p>
				<strong><%=dto.getUserName() %></strong> 님 로그인 되었습니다.
				<a href="<%=url%>">확인</a>
			</p>
		<%}else { %>
			<p>
				아이디 혹은 비밀 번호를 확인하세요!
				<a href="${pageContext.request.contextPath}/user/login-form.jsp?url="<%=encodedUrl %> >다시 입력</a>
			</p>
		<%} %>
	</div>
	
	
</body>
</html>