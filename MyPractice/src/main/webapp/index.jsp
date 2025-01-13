<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.html</title>
</head>
<body>
	<h1>인덱스 페이지입니다.</h1>
	<li><a href="fortune.jsp">오늘의 운세</a></li>
	<li><a href="fortune">오늘의 운세는?</a></li>
	<li><a href="member/list.jsp">운세 본 유저</a></li>
		
	<!-- 폼요소는 경로, 전송 방법, 입력 박스, 버튼 모두 설정 -->
	<form action="send.jsp" method="get">
		<input type="text" name="msg" placeholder="당신이 하고싶은 말은..."/>
		<button type="submit">보내기</button>
	</form>
	
	
</body>
</html>