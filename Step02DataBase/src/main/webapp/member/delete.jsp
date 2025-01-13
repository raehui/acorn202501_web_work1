<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. GET 방식 파라미터 전달되는 삭제할 회원의 번호를 얻어온다.
	int num=Integer.parseInt(request.getParameter("num"));
	//2. MemberDto 객체를 이용해서 실제 DB 에서 삭제
	MemberDao dao=new MemberDao();
	boolean isSuccess=dao.delete(num);
	
	//3. 응답하기
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/delete.jsp</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p>
				<strong><%=num%></strong>번 회원의 정보를 삭제했습니다.
				<a href="list.jsp">확인</a>
			</p>
		<%}else{ %>
			<p>
				삭제 실패!
				<a href="list.jsp">확인</a>
			</p>
		<%} %>
	
	</div>
</body>
</html>