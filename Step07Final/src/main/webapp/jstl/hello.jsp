<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JSTL 사용 설정하기
	uri="import 할 경로"
	prefix="접두어"
 --%>    
 <%--jsp 파일의 기능을 확장 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/hello.jsp</title>
</head>
<body>
	<%for(int i=0;i<10;i++){ %>
		<p>Hello Java Code i: <strong><%=i %></strong></p>
	<%} %>
	
	<%--마크업 형식으로 직관적이다. --%>
	<%--jsp 에서 java의 로직을 사용할 수 있음 --%>
	<h1>Java Standard Tag Library(JSTL)</h1>
	<%--java 코드를 작성하지 않고 반복문을 사용함 --%>
	<c:forEach var="i" begin="0" end="9">
		<p>
		<%--EL과 JST는 콩떡궁합 --%>
			Hello JSTL <strong>:${i}</strong>
		</p>
	</c:forEach>
</body>
</html>