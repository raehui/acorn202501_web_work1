<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/game.jsp</title>
</head>
<body>
	<div class="container">
		<h1>게임 페이지 입니다.</h1>
		<%--로그인을 해야지만 접속할 수 있도록 --%>
		<a href="${pageContext.request.contextPath}/test/protected/buy.jsp">game 아이템 구매</a>
		<p>신나게 놀아 보아요</p>
		<a href="${pageContext.request.contextPath}/">인덱스로</a>
	</div>
</body>
</html>