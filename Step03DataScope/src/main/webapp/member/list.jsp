<%@page import="java.util.ArrayList"%>
<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request scope에서 "list"라는 키값으로 담겨있는 List 객체를 얻어낸다.
	List<MemberDto> list=(List<MemberDto>)request.getAttribute("list");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 리스트의 정보</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<%for(MemberDto tmp:list){ %>
			<tr>
				<td><%=tmp.getNum() %></td>
				<td><%=tmp.getName() %></td>
				<td><%=tmp.getAddr() %></td>
				<%--EL을 사용할려면 for문을 jstl로 변경해야 함 
					<td>${requestScope.tmp.getName()}</td>
					<td>${tmp.addr}</td>
				즉, EL로 추출은 가능하나 반복문을 돌수는 없다.	
				--%>	
			</tr>
			<%} %>
		</tbody>
	</table>
</body>
</html>