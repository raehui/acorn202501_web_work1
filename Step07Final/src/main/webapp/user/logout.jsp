<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 세션 초기화
	session.invalidate();
	//2. 응답하기
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/logout.jsp</title>
</head>
<body>
	<script>
		alert("로그 아웃 되었습니다.");
		//javascript 로 페이지 이동 시키기
		location.href="${pageContext.request.contextPath}/";
	</script>
</body>
</html>