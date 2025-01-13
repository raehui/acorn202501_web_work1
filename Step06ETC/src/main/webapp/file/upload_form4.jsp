<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/upload_form4.jsp</title>
<style>

</style>
</head>
<body>
		<div class="container">
		<h3>이미지 단독 업로드 테스트</h3>
		<a href="javascript:" id="profileLink" >
			<svg xmlns="http://www.w3.org/2000/svg" width="200" height="200" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
				<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
				<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			</svg>
		</a>
		<br />
		<input type="file" id="image" accept="image/*" />
	</div>
	<script>
		//링크를 클릭했을 때
		document.querySelector("#profileLink").addEventListener("click",()=>{
			//input type="file" 요소를 강제 클릭해서 파일 선택 창을 띄운다.
			document.querySelector("#image").click();
		});
		
		//새로운 이미지를 선택 했을 때 input 요소에는 change 이벤트가 발생한다.
		document.querySelector("#image").addEventListener("change",(event)=>{
			//여기서 event.target은 input type="file"요소이다.
			//선택된 파일 데이터
			const fileData=event.target.files[0];
			//FormData 객체에 myImage 라는 키값으로 파일 데이터 담기
			const data=new FormData();
			data.append("myImage",fileData);
			//fetch()함수를 이용해서 업로드 하고 응답받은 데이터를 이용해서 이미지 출력하기
			//이미지 프로필 올리기
			
		});
	</script>
</body>
</html>