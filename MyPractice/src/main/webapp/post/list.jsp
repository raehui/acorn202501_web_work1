<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%
	
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 보기</title>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath}/post/insert.jsp ">일기작성</a>
		<h1>일기목록</h1>
		<table class="table table-striped">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성한 날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
</body>
</html>