<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/upload_form4.jsp</title>
<style>
	#image{
		display: none;	
	}
	#profileImage{
		width: 200px;
		height: 200px;
		border: 1px solid #cecece;
		border-radius:50%;
	}
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
		//이미지를 클릭하면 링크가 클릭되고 
		document.querySelector("#profileLink").addEventListener("click",()=>{
			//input type="file" 요소를 강제 클릭해서 파일 선택 창을 띄운다.
			//클릭되면 강제로 파일 선택창을 클릭하게 만들어 창을 띄움
			document.querySelector("#image").click();
		});
		
		//새로운 이미지를 선택 했을 때 input 요소에는 change 이벤트가 발생한다.
		//사진을 선택하면 change가 발생하면 
		document.querySelector("#image").addEventListener("change",(event)=>{
			//여기서 event.target은 input type="file"요소이다.
			//선택된 파일 데이터 이벤트가 발생한 대상
			//입력된 이미지 데이터를 저장
			const fileData=event.target.files[0];
			//FormData 객체에 myImage 라는 키값으로 파일 데이터 담기
			const data=new FormData();
			//form요소가 없으니 수동으로 담아주기
			//파라미터명에 파일 데이터를 담을 수 있음
			//수동으로 우리가 form을 만들어서 거기에 파라미터명과 가지고 가는 데이터를 정해줌
			//데이터의 이름은 myImage이고 데이터 타입은 파일이야
			data.append("myImage",fileData);
			
			
			//fetch()함수를 이용해서 업로드 하고 응답받은 데이터를 이용해서 이미지 출력하기
			//이미지 프로필 올리기
			fetch("${pageContext.request.contextPath}/file/upload4",{
				method:"post",
				body:data,
			}) 
			//data인 UploadServlet4 페이지와 upload4.jsp에서 응답이 옴
			//UploadServlet4에서는 
			
			.then(res=>res.json())
			.then(data=>{
				//data.savaFileName은 업로드된 이미지의 저장된 파일명이다.
				console.log(data);
				//img 요소를 만들 문자열 구성하기
				//img src는 서버가 해석하면 안되고 웹브라우저가 해석해야 한다.
				//jsp(서버)가 해석하지 못하게 / 붙여서 저장

				//backtic으로 html요소를 만들면서 원하는 데이터를 넣을 수 있음
				<%--하지만 여기서 자바스크립트의 ${}는 원하는 데이터를 넣는 것이고--%>
				<%--jsp(서버도) ${}를 사용한다. 여기서는 jsp가 ${}도 자신이 해석하는 거라고 착각해서 내용이 안나올수도 있으니 \를 사용하는 것임--%>
				const img=`
					<img id="profileImage" src="${pageContext.request.contextPath}/upload/\${data.saveFileName}">
				`;
				//innerHTML로 해야지 이미지로 주가됨
				document.querySelector("#profileLink").innerHTML=img;
			})
			//예외 추가하기
			.catch(error=>{
					console.log("에러정보"+error);
			});
			
			
		});
	</script>
</body>
</html>