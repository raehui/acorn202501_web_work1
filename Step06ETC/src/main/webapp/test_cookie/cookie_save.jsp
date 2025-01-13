<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼 전송되는 문자열 읽어오기
	String msg=request.getParameter("msg");

	//1. 쿠키 객체 생성
	Cookie cook=new Cookie("savedMsg",msg);
	//2. 쿠키 유지시간 설정(초단위)
	cook.setMaxAge(60); //60초 테스트
	//3. 응답할 때 쿠키도 같이 응답하도록
	//쿠키 부스러기도 같이 보냄
	response.addCookie(cook);
	//4. 응답할 때 쿠키가 자동으로 응답되고 클라이언트의 웹브라우저에 저장이 된다.
	
	//쿠키 부스러기가 뭍어서 전송 응답이 된다.
	//각각 개인의 정보를 저장할 때 사용함
	//각각의 로컬의 브라우저에 저장했다.
	
	//로그인 안해도 장바구니에 저장되는 경우 : 쿠키에 담아놓는 것
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_cookie/cookie_save.jsp</title>
</head>
<body>
	<p>웹 브라우저에 saveMsg 라는 키값으로 "<%=msg %>"를 저장했습니다.</p>
	<a href="cookie_read.jsp">저장된 문자열 확인 해보기</a>
	<br />
	<a href="cookie_read2.jsp">저장된 문자열 확인 해보기2</a>
</body>
</html>