<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/upload_form.jsp</title>
</head>
<body>
	<%--
		[파일 업로드 폼 구성하는 방법]
		method="post"
		enctype="multipart/form-data"   파일을 업로드 해야하기에
		<input type="file">
	 --%>
	 <div class="container">
	 	<h3>파일 업로드 폼</h3>
	 	<%--파일 업로드는 요청은 서블릿이 실행함--%>
	 	<form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
	 		<input type="text" name="title" placeholder="설명 입력..."/>
	 		<br>
	 		<input type="file" name="myFile" />
	 		<br>
	 		<button type="submit">업로드</button>
	 	</form>
	 </div>
</body>
</html>