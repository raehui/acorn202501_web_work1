<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 폼 전송되는 writer,content, pwd 를 추출
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	//2. DB에 저장 글정보를 GuestDto에 담기
	//DB에 저장하기 위해서는 Dao를 이용해야 하는데
	//Dao를 사용하기 위해서는 Dto에 추출한 데이터를 담아야함
	GuestDto dto=new GuestDto();
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setPwd(pwd);
	//GuestDao 객체의 참조값을 얻어오기
	GuestDao dao=GuestDao.getInstance();
	boolean isSuccess=dao.insert(dto);
	
	//3. 응답하기
%>
<%--이 부분을 서버는 웹브라우저 응답해준다. --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guset/insert.jsp</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p>
				<%=writer %> 님이 작성한 글을 성공적으로 저장 했습니다.
				<a href="list.jsp">목록보기</a>
			</p>
		<%}else{ %>
			<p>
				저장 실패!
				<a href="insertform.jsp">다시 작성</a>
			</p>
		<%} %>
	</div>
	
</body>
</html>