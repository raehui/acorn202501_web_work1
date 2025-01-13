<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>file/upload_form3.jsp</title>
</head>
<body>
		<div class="container">
		<h3>이미지 업로드 폼</h3>
		<form action="${pageContext.request.contextPath}/file/upload3" method="post" enctype="multipart/form-data" id="myForm">
			<input type="text" name="title" placeholder="설명 입력"/><br />
			이미지 <input type="file" name="myImage" accept="image/*"/><br />
			<button type="submit">업로드</button>
		</form>
		<img id="image" width="300" src="${pageContext.request.contextPath}/upload/"/>
	</div>
	<script>
		document.querySelector("#myForm").addEventListener("submit",(event)=>{
			//발생한 이벤트에 대한 정보를 담고있는 object가 매개변수에 전달된다.
			
			//기본동작(폼제출)을 막기
			event.preventDefault();
			
			//envet.target => 해당 이벤트가 발생한 바로 그 요소의 참조값 (form의 참조값)
			//document.querySelector("#myForm")과 동일함
			//입력된 데이터가 담긴다.
			const data=new FormData(event.target);
			//fetch 함수를 이용해서 FormData 전송하기
			fetch("${pageContext.request.contextPath}/file/upload3",{
				//object는 키 : 값 생각하기
				method :"post",
				body:data
			})
			.then(res=>res.json())
			.then(data=>{
				//함수 영역
				console.log(data);
				//data.saveFileName 은 upload 폴더에 저장된 파일명이다.
				const requestPath="${pageContext.request.contextPath}/upload/"+data.saveFileName;
				//img 요소에 src 속성 추가하기		
				document.querySelector("#image").setAttribute("src",requestPath);
			});
			
			
			
		});
	</script>
</body>
</html>