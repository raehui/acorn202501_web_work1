<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//1. 번호에 맞는 폼에 수정되는 내용 이름과 주소를 추출한다.
	//1.get 방식 파라미터 전달되는 수정할 회원의 번호를 얻어오기
	int num=Integer.parseInt(request.getParameter("num"));
	//2. num에 해당하는 회원의 정보를 Memberdto를 이용해서 실제 DB에서의 내용 얻어오기
	MemberDto dto=new MemberDao().getData(num);	
	//응답한다
		
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/updateform.jsp</title> 
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/member/list.jsp">회원목록</a>
				</li>
				<li class="breadcrumb-item active">회원수정</li>
			</ol>
		</nav>
		<h1>회원정보 수정 폼</h1>
		<form action="update.jsp" method="post">
			<%--${~}작성해도 동일한가? --%>
			<%--"${pageContext.request.contextPath}/member/update.jsp"랑 동일함--%>
			<div class="mb-3">
				<label class="form-label" for="num">번호</label> <input class="form-cotrol" type="text" name="num" id="num"
					value="<%=dto.getNum()%>" readonly />
			</div>

			<div class="mb-3">
				<label for="name" class="form-label">이름</label> <input class="form-cotrol" type="text" name="name"
					id="name" value="<%=dto.getName()%>" />
			</div>
			<div class="mb-3">
				<label for="addr" class="form-label">주소</label> <input class="form-cotrol" type="text" name="addr"
					id="addr" value="<%=dto.getAddr()%>" />
			</div>
			<button class="btn btn-success"type="submit">저장</button>
			<button class="btn btn-danger"type="reset">취소</button>
		</form>
	</div>
</body>
</html>