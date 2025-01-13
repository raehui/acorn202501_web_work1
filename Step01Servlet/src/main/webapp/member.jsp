<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%MemberDto dto=new MemberDto(1, "김구라","노량진"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member.jsp</title>
</head>
<body>
	<ul>
		<li>번호: <%=dto.getNum() %></li>
		<li>이름: <%=dto.getName() %></li>
		<li>주소: <%=dto.getAddr() %></li>

	</ul>


</body>
</html>