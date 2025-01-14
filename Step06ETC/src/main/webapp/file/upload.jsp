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
		<p>
			<%--
				파일을 다운로드 하기위해서는 원본파일명, 저장된 파일명, 파일의 크기가 필요
				원래는 파일의 id가 전달하면 id를 이용해서 파일 정보를 DB에서 읽어와서 가지고 옴				
			 --%>
			<a href="${pageContext.request.contextPath }/file/download?orgFileName=${orgFileName }&saveFileName=${saveFileName}&fileSize=${fileSize}">다운로드</a>
		</p>
		
	</div>
</body>
</html>