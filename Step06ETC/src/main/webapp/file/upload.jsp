<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/upload.jsp</title>
</head>
<body>
	<div class="container">
		<h1>업로드 결과페이지</h1>
		<p>파일을 업로드 했습니다.</p>
		<p>title : <strong>${requestScope.title}</strong></p>
		<p>orgFileName : <strong>${orgFileName }</strong></p>
		<p>saveFileName : <strong>${saveFileName }</strong></p>
		<p>fileSize: <strong>${fileSize}</strong></p>
		<p>uploadPath: <strong>${uploadPath}</strong></p>
		
	</div>
</body>
</html>