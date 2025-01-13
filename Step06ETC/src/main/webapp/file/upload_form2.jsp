<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/upload_form2.jsp</title>
</head>
<body>
	<div class="container">
		<h3>이미지 업로드 폼</h3>
		<form action="${pageContext.request.contextPath}/file/upload2" method="post" enctype="multipart/form-data">
		<input type="text" name="title" placeholder="설명 입력..."/> <br />
		이미지 <input type="file"	name="myImage" accept="image/*" /> <br /> <%--이미지 파일만 선택할 수 있다. --%>
		<button type="submit">업로드</button>
		</form>
	</div>
</body>
</html>