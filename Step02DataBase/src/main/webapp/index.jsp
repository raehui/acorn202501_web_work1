<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<%--bottstrap css와 자바스크립트 소스 갖고오기 / 자바스크립트는 css 를 사용하기 위해서는 필요함--%>
<%--이 부분은 resource.jsp 페이지가 응답하도록 한다. / <jsp 입력 후 탭--%>
<jsp:include page="/include/resource.jsp"></jsp:include> <%--경로는 / 으로 webapp에서 시작해야 함 , emmet snippets에 추가 --%>
</head>
<body class="d-flex flex-column min-vh-100"> <%--main과 foother 를 열로 정렬/ 컬럼을 100으로 정하고 나눠 갖도록 --%>
	<div class="main flex-grow-1"> <%--남는 공간이 있으면 main이 다 가지도록 --%>
		<!-- 웹 브라우저가 무시하는 주석 -->
		<%--이 부분은 navbar.jsp 페이지가 응답하도록 한다 --%>
		<jsp:include page="/include/navbar.jsp">
			<jsp:param value="index" name="current"/>
		</jsp:include> <%--emmet snippets에 추가 --%>
		<div class="container">
			<h1>인덱스 페이지 입니다.</h1>
			<ul>
				<li><a href="connection/test.jsp">Connection 테스트</a></li>
				<li><a href="member/list.jsp">회원 목록보기</a></li>
				<li><a href="food/list.jsp">맛집 목록보기</a></li>
			</ul>
		</div>
	</div>
	<%--foother.jsp include --%>
	<%--<jsp:i 치고 탭! --%>
	<jsp:include page="/include/foother.jsp"/>
  	
  	
	
</body>
</html>