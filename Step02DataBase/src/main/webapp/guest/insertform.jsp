<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/insertform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav> <!-- navbar를 뜻함 -->
		 <ol class="breadcrumb"> 
		 <!-- 웹사이트의 탐색 경로를 표시하는 UI요소/ 위의 위치바를 만들고 싶을 때 사용-->
		 	<li class="breadcrumb-item">
		 		<a href="${pageContext.request.contextPath}">Home</a>
		 	</li>
		 	<li class="breadcrumb-item">
		 		<a href="${pageContext.request.contextPath}/guest/list.jsp">방명록 목록</a>
		 	</li>
		 	<li class="breadcrumb-item active">새글 작성</li>
		 </ol>
		</nav>
		<h1>좋은 글을 남겨 주세요!</h1>
		<form action="insert.jsp" method="post">
			<div class="mb-2">
				<%-- form-label은 라벨 요소를 스타일링--%>
				<label for="writer" name="writer" id="writer" class="form-label">작성자</label>
				<%--form-control 은 입력 필드를 스타일링--%>
				<input type="text" name="writer" id="writer" class="form-control"/>
			</div>
			<div class="mb-2">
				<label for="content" class="form-label">내용</label>
				<textarea name="content" id="content" style="height:200px" class="form-control"></textarea>
			</div>
			<div class="mb-2">
				<label for="pwd" class="form-label">비밀 번호 입력</label>
				<%--비밀번호는 type에 password 입력하기 --%>
				<input type="password" name="pwd" id="pwd" class="form-control" />
			</div>
			<button type="submit" class="btn btn-outline-success btn-sm">버튼</button>
		</form>
	</div>
</body>
</html>