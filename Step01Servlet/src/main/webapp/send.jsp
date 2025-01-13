<!--
[클라이언트의 관점] 
client 가 post 방식 "send.jsp" 를 요청하니 아래의 문자열이 응답되었음 

[서버의 관점]
client가 "/send.jsp" 경로 요청을 한다
/webapp/send.jsp 페이지로 응답을 한다.
= jsp 페이지를 해석한 결과가 응답한다. html 은 그대로 응답이 되지만 jsp는 해석된 결과가 응답이 된다.

-해석의 의미?
send.jsp 페이지를 class send_jsp 클래스로 변환한다.
send.jsp 클래스는 서블릿이다.
즉 send_jsp 서블릿 객체로 응답을 한것이다. 

단 서블릿은 html의 표현이 어려웠지만 jsp는 편하게 출력할 수 있다.
-->

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	/*
		여기는 서블릿의 service()의 메소드 안쪽 영역이라고 생각하고 코딩하면 된다.
		jsp 기본 객체 8개가 지역 변수에 들어있다. 필요하면 .찍어서 사용 = 객체가 이미 만들어져 있기에 사용만 하면 된다.
		
	*/
	
	//서블릿의 서비스 메서드의 안쪽 영역이랑 같은 역할
	//내장 객체
	String fortuneToday="서쪽으로 가면 귀인을 만날 수 있어요!";
	String msg=request.getParameter("msg");
	//콘솔창에 출력
	System.out.println(msg);
		
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send.jsp</title>
</head>
<body>
	<h1>나는 jsp 페이지</h1>
	<p>오늘의 운세: <strong><%out.print(fortuneToday);%></strong></p> 
	<p>오늘의 운세: <strong><%=fortuneToday%></strong></p>
	<%for(int i=0;i<3;i++){ %>
		<p><%=i %></p>
	<%} %>
	
	
	
</body>
</html>
