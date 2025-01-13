<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//삭제할 키값을 가지고 있는 cookie 객체 생성
	Cookie cook=new Cookie("savedMsg","");
	//Cookie 유지시간을 0으로 설정
	cook.setMaxAge(0);
	//응답하면 Cookie 가 강제로 삭제된다.
	response.addCookie(cook);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cookie_test/cookie_delete.jsp</title>
</head>
<body>
	<p><strong>savedMsg</strong></p>라는 키값으로 저장된 쿠키를 삭제 했습니다.
	<a href="cookie_read2.jsp">쿠키 다시 읽어보기</a>
	
</body>
</html>