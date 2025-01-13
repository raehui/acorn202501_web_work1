<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num=Integer.parseInt(request.getParameter("num"));
	GuestDto dto=GuestDao.getInstance().getDate(num);
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/updateform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include> <%--경로를 보면 어떤 파일인지 알 수 있음 --%>
</head>
<body>
	<div class="container">
		<nav> <%-- --%>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath}/"}>HOME</a> 
					<%--마지막에 / 작성하지 않아도 최상위 경로의 index.jsp--%>
				</li>
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath}/guest/list.jsp"}>방명록 목록</a>
				</li>
				<li class="breadcrumb-item">글수정</li>
			</ol>
		</nav>
	
		<h1>방명록 글 수정 폼</h1>
		<form action="update.jsp" method="post">
			<div class="mb-2">
				<label for="num" class="form-label">번호</label>
				<%--name이 없으면 서버에 데이터가 전송되지 않음 --%>
				<input type="text" name="num" id="num" value=<%=dto.getNum() %> readonly class="form-control" />
			</div>
			<div class="mb-2">
				<label for="writer" class="form-label">작성자</label>
				<input type="text" name="writer" id="writer" value="<%=dto.getWriter() %>" class="form-control" />
			</div>
			<div class="mb-2">
				<label for="content" class="form-label">내용</label>
				<%--textarea는 style로 조절 --%>
				<textarea name="content" id="content" style="height: 200px" class="form-control"><%=dto.getContent() %></textarea>
			</div>
			<div class="mb-2">
				<label for="pwd" class="form-label">글 작성시 입력했던 비밀 번호</label>
				<%--type을 password로 하면 입력된 번호가 가려짐 --%>
				<%--하지만 서버에 데이터가 전송되는 것은 같으므로 UI만 변경됨--%>
				<input type="password" class="form-control" name="pwd" id="pwd" />
			</div>
			
			<button class="btn btn-outline-success btn-sm" type="submit">수정확인</button>
			<button class="btn btn-outline-danger btn-sm" type="reset">취소</button>
		</form>
	</div>
</body>
</html>