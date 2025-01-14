<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fileupload2.jsp</title>
</head>
<body>
	<div class="container">
		<h1>업로드 결과페이지</h1>
		<p>이미지를 업로드 했습니다.</p>
		<p>title : <strong>${requestScope.title}</strong></p>
		<p>orgFileName : <strong>${orgFileName }</strong></p>
		<p>saveFileName : <strong>${saveFileName }</strong></p>
		<p>fileSize: <strong>${fileSize}</strong></p>
		<p>uploadPath: <strong>${uploadPath}</strong></p>
		<p>
			<a href="${pageContext.request.contextPath }/file/download?orgFileName=${orgFileName }&saveFileName=${saveFileName}&fileSize=${fileSize}">다운로드</a>
		</p>
		<%--저장된 경로만 알면 webapp에 있는 걸 가지고 올 수 잇음 --%>
		<img src="${pageContext.request.contextPath}/upload/${saveFileName}" alt="업로드된 이미지" />
	</div>
</body>
</html>