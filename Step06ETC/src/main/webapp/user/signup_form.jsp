<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form.jsp</title>
<style>
	.invalid-feedback{
		display: none;
		color: red;
	}
</style>

</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다.</h1>
		<!--novalidate 는 웹브라우저가 제공하는 기본 폼 검증 방법 비활성-->
		<!-- 우리가 원하는 검증 방법을 사용해야 하니 -->
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" />
				<small>영문자 소문자로 시작하고 5~10글자 이네로 입력하세요!</small>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>		
			</div>
			<button type="submit">가입</button>
		</form>
	</div>
	<script>
		/*
			폼에 submit 이벤트가 발생하면 입력한 내용을 검증해서 조건을 만족하지 못하면
			폼 제출을 막는 예제
		*/
		const reg_id=/^[a-z].{4,9}$/; //대부분의 시작과 끝을 표시해야 한다.
		//정규표현식이 아니면 제출을 막게끔
		//폼안에 있는 submit  버튼을 누르면 form 이 제출되는 기본 동작을 한다.
		
		document.querySelector("#signupForm").addEventListener("submit",(e)=>{
			//입력한 id를 읽어온다.
			const id=document.querySelector("#id").value;
			const result=reg_id.test(id);
			//만일 아이디를 검증하는 정규표현식을 통과 하지 않았다면
			if(!result){
				//폼 제출을 막기
				//여기서 e는 폼 제출할려는 동작
				//e.target는 제출하는 동작이 발생되는 form 이다.
				e.preventDefault();
				//.invalid-feedback인  div를 보이게 해보세요 (display:block)
				document.querySelector(".invalid-feedback").style.display="block";
				
			}
			
		});
		
		
		
		
		
		
	</script>
</body>
</html>