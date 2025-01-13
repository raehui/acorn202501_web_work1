<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fetch/test05.jsp</title>
</head>
<body>
	<h1>폼에 입력한 내용을 페이지 전환 없이 전송하기</h1>
	<form action="${pageContext.request.contextPath}/user/login.jsp" method="post" id="myForm">
		<input type="text" name="id" placeholder="아이디 입력..." />
		<input type="password" name="pwd" placeholder="비밀번호 입력..." />
		<button type="submit">로그인</button>
	</form>
	<script>
		//폼에 submit 이벤트가 발생했을 때 실행할 함수 등록
		document.querySelector("#myForm").addEventListener("submit",(e)=>{
			//event 객체의 .preventDefault() 함수를 호출하면서 기본 동작을 막는다(폼 제출이 막아진다.)
			e.preventDefault();
			console.log("submit!");
			//폼에 입력한 내용을 FormData Type 으로 얻어내기
			const data=new FormData(e.target); //e.target은 form 의 참조값이다. 파라미터가 들어가지 않은 찐 form의 참조값
			//FormData를 이용해서 query string 얻어내기
			//form의 참조값을 string로
			const queryString=new URLSearchParams(data).toString();
			//query string 을 콘솔에 출력하기
			console.log(queryString);
			//action 속성의 value 읽어오기
			const url=e.target.action;
			//fetch()함수를 이용해서 post 방식 요청하면서 queryString 을 요청의 body 에 전달하기
			//form 을 제출하는 것을 그냥 fetch로 구현한 것으로
			fetch(url,{
				method:"POST",
				headers:{"Content-Type":"application/x-www-form-urlencoded; charset=utf-8"},
				body:queryString
				
			})
			.then(res=>res.json())
			.then(data=>{
				//data는 object 이고 id와 pwd 의 유효성 여부가 담겨져 있다.
				console.log(data);
				
			})
			.catch(error=>{
				console.log("에러정보: "+error);
			});
			
		});
		
	</script>
</body>
</html>