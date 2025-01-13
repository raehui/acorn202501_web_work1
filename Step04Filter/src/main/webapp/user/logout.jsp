<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//jsp에 내장된 session 객체의 참조값을 이용해서 removeAttribute의 메서드를 이용해서
	//id를 지우고 밑에 내용을 응답하는 구조임
	session.removeAttribute("id");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/logout.jsp</title>
</head>
<body>
	<script>
		alert("다시 로그인 하세요!");
		location.href="${pageContext.request.contextPath}/";
	</script>
</body>
</html>