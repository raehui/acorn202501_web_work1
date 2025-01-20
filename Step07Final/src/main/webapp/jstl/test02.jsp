<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	MemberDto d1=new MemberDto(1, "김구라", "노량진");
	MemberDto d2=new MemberDto(2, "해골", "행신동");
	MemberDto d3=new MemberDto(3, "원숭이", "동물원");
	
	List<MemberDto> list=new ArrayList<>();
	list.add(d1);
	list.add(d2);
	list.add(d3);
	
	//위의 데이터는 DB 에서 읽어온 sample 데이터라고 가정 
	
	//EL 로 사용할수 있도록 request 영역에 담기 
	//JSTL 영역에서 ${requsetScope.list} 로 추출할려고 작성
	//requsetScope이라는 그릇에 담겼음
	
	//DB에서의 내용을 request에 담으면 EL과 JSTL로 추출이 가능함
	request.setAttribute("list", list);	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/test02.jsp</title>
</head>
<body>
	<h1>회원 목록</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<%--tmp는 MemberDto 객체이다, list는 List<MemberDto> 객체 --%>
			<c:forEach var="tmp" items="${list}">
				<tr>
					<td>${tmp.num }</td> <%--tmp.getNum 대신해서 EL에서 활용할 수 잇음 --%>
					<td>${tmp.name }</td> 
					<td>${tmp.addr }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>