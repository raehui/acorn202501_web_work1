<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--클라이언트가 최상위 경로 요청을 해오면 컨트롤러 GETMapping("/") 를 생성 후 jsp 페이지로 응답을 위임해서 home.jsp 페이지가 응답 --%>
<!--컨트롤러가 index를 home.jsp로 설정한 것이다.-->
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<p>context path : <strong>${pageContext.request.contextPath}</strong></p>
		<h2>공지사항</h2>
		<ul>
			<c:forEach var="tmp" items="${notice }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
		
		<ul>
			<li><a href="${pageContext.request.contextPath}/fortune">오늘의 운세</a></li>
			<li><a href="${pageContext.request.contextPath}/fortune2">오늘의 운세2</a></li>	
			<li><a href="${pageContext.request.contextPath}/member">회원 한명의 정보</a></li>		
		</ul>
	</div>
</body>
</html>