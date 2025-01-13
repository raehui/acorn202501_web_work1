<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼으로 전송 되는 내용 추출하기
	String nick=request.getParameter("nick");
	//request는 일호성이지만 접속하면 소멸
	
	//session은 웹브라우저가 닫히기 전까지 유지된다(예를 들어서 웹브라우저를 나가지 전까지 로그인 상태가 유지되는 것처럼!)
	//여러 세션을 켜두면 모두 닫아야 함 + 세션이 기억을 함
	//session을 활용하면 접속하는 유저를 구분할 수도 있다.
	//session영역에 nick 이라는 키값으로 저장한다.
	session.setAttribute("nick", nick);
	
	//세션 유지 시간을 초단위로 전달해서 설정할 수 있다(기본 30분)
	//은행은 setMaxInactiveInterval이 짧다.
	//session.setMaxInactiveInterval(30);	
	
	
	//응답한다

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/save.jsp</title>
</head>
<body>
	<p><strong><%=nick %></strong>이라는 닉네임을 기억 하겠습니다.</p>
	<p>30분 동안 아무런 요청을 하지 않거나 웹브라우저를 닫으면 자동 삭제 됩니다.</p>
	<a href="../index.jsp">인덱스로 이동하기</a>
</body>
</html>