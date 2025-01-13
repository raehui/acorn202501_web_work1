<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDto dto=(MemberDto)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>회원 한명의 정보</h1>
		<p>번호 : <strong><%=dto.getNum()%></strong></p>
		<p>이름 : <strong> <%=dto.getName()%></strong></p>
		<p>번호 : <strong><%=dto.getAddr()%></strong></p>
		
		<%--EL을 이용해서 requestScope에 담긴 내용을 추출 할 수 있다. --%>
		<%--담은 키값이 핵심! --%>
		<p>번호 :<strong>${requestScope.dto.getNum()}</strong></p>
		<p>이름 :<strong>${requestScope.dto.getName()}</strong></p>
		<p>주소 :<strong>${requestScope.dto.getAddr()}</strong></p>
		<%--requestScope.은 생략 가능 --%>
		<p>번호 :<strong>${dto.getNum()}</strong></p>
		<p>이름 :<strong>${dto.getName()}</strong></p>
		<%--.필드명 만 명시해도 getter 메소드가 자동 호출된다. --%>
		<p>주소 :<strong>${dto.addr}</strong></p>
		
		
		
		
		
		
	</div>

</body>
</html>