<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지</h1>
		
		<ul>
			<%--Servlet 를 거치지 않으면 값이 나오지 않음. 왜냐면 서블릿에서 작업을 하고 Scope에서 담아서 주는데 그 과정을 안 거침  --%>
			<%--webapp안에 fortune 파일이 없으니 서블릿으로 접속했겠군 --%>
			<li><a href="${pageContext.request.contextPath}/fortune">오늘의 운세</a></li>
			<li><a href="${pageContext.request.contextPath}/member">회원 한명의 정보</a></li>
			<%--경로를 맞추기 위해서는 member라는 파일의 생성이 꼭 필요하다. --%>
			<li><a href="${pageContext.request.contextPath}/member/list">회원 목록</a></li>
			<li><a href="${pageContext.request.contextPath}/test/fortune.jsp">테스트1</a></li>
			
		</ul>
		
		<form action="${pageContext.request.contextPath}/test/save.jsp" method="post" >
			<input type="text" name="nick" placeholder="닉네임 입력..." />			
			<button type="submit">닉네임 기억 시키기</button>
		</form>
		<%
				//session scope 에 "nick"이라는 키값으로 저장된 문자열이 있는지 읽어와 본다.
				String nick=(String)session.getAttribute("nick");
			%>
			<%if(nick!=null){ %>
				<p>
					<strong><%=nick %></strong>님 반갑습니다.
					<a href="${pageContext.request.contextPath}/test/logout.jsp">로그아웃</a>
				</p>
			<%} %>		
	</div>
</body>
</html>