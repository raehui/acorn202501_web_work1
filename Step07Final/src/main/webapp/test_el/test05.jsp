<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_el/test05.jsp</title>
</head>
<body>
	<% 
		//post 방식 전송했을때 인코딩 설정을 하지 않으면 한글이 깨진다. ( tomcat 10 에서는 필요 없음)
		request.setCharacterEncoding("utf-8");
	
		//msg 라는 파라미터 명으로 전달되는 문자열 추출 
		String msg=request.getParameter("msg");
	%>
	<p> msg 라는 파라미터 명으로 전송된 내용 : <strong><%=msg %></strong></p>
	<%-- 위의 2 줄의 코딩 대신에 아래와 같이 응답할수도 있다 --%>
	<%--파라미터나 쿠키의 내용을 클라이언트에게 출력하고 싶을 때 사용, 많이 사용하지는 않음 --%>
	<p> msg 라는 파라미터 명으로 전송된 내용 : <strong>${param.msg }</strong></p>
</body>
</html>










