<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//세션에 저장된 아이디가 있는지 읽어와 온다.
	//jsp에 session은 내장되어 있기에 가져와서 사용 가능함  없으면 request.getsession()해야함
	String id=(String)session.getAttribute("id");
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<div class="container">
		<%if(id==null){ %>
			<a href="user/loginform.jsp">로그인 하러가기</a>
		<%}else{ %>
			<strong><%=id %></strong> 님 로그인 중...
			<a href="user/logout.jsp">로그아웃</a>
		<%} %>
		
		<h1>인덱스 페이지</h1>
		<ul>
			<li><a href="test/study.jsp">공부하러 가기</a></li>
			<li><a href="test/game.jsp">게임하러 가기</a></li>
			<li><a href="admin/manage.jsp">관리자 페이지</a></li>
		</ul>	
	</div>
</body>
</html>