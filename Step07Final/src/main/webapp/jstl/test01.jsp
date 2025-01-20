<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%
	//테스트를 위해 sample 데이터를 request scope 에 담는다.
	List<String> names=new ArrayList<String>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
	//"list"라는 키값으로 request scope 에  ArrayList 객체 담아두기
	//Selvlet에서 request 영역에 담고 jsp 페이지로 응답을 위임 ${requsetScope.list}해서 추출해서 
	//JSTL의 도움을 받아서 클라이언트에게 출력
	request.setAttribute("list", names); //키 : 값 형식으로 담음
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>친구 목록</h1>
	<%
		//requset 영역에 "list" 라는 키값으로 담긴 친구목록을 얻어와서 원래 type 으로 casting
		List<String> list=(List<String>)request.getAttribute("list"); 
	%>
	<ul>
		<%for(String tmp:list){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
	
	<h1>친구 목록</h1>
	<ul>
		<%--tmp는 String type --%>
		<%--requestScope. 는 생략도 가능함 --%>
		<c:forEach var="tmp" items="${requestScope.list}">
		<li>${tmp}</li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 EL + JSTL</h1>
	<ul>
		<%--status 는 인덱스가 필요할 때 사용--%>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스 :${status.index }</strong></li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 순서 표시</h1>
	<ul>
		<%--count 는 순서가 필요할 때 사용--%>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스 :${status.count }</strong></li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 첫번쩨 순서인지 여부</h1>
	<ul>
		<%--first 는 첫번째 순서가 필요할 때 사용--%>
		<%--boolean 값으로 출력 --%>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스 :${status.first }</strong></li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 마지막 번째 순서인지 여부</h1>
	<ul>
		<%--last 는 마지막 순서가 필요할 때 사용--%>
		<%--boolean 값으로 출력 --%>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>마지막 번째 : ${status.last }</strong></li>
		</c:forEach>
	</ul>
	
	
</body>
</html>