<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form2.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다.</h1>
		<!--novalidate 는 웹브라우저가 제공하는 기본 폼 검증 방법 비활성-->
		<!-- 우리가 원하는 검증 방법을 사용해야 하니 -->
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div>
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id" /> <%--is-invalid or valid는 유효한지의 상태를 알려줌--%>
				<small class="form-text">영문자 소문자로 시작하고 5~10글자 이네로 입력하세요!</small>
				<div class="valid-feedback">잘 입력했군요!짱구가 아니네요~</div> 
				<%--유효할 때(input 클래스속성이 valid일 때) 보여주는 text --%>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>	
				<%--유효하지 않을 때(=input 클래스속성이 invalid일 때)보여주는 text --%>			
			</div> 
			<button class="btn btn-success" type="submit" disabled="disabled">가입</button>
		</form>
	</div>
	<script>
		//state(상태)는 분기를 나누기 위해 존재
		//아이디 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isIdValid=false;
		//비밀번호 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isPwdValid=false;
		//이멜일 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isEmailValid=false;
		
		const checkForm=()=>{
			//폼 전체의 유효성 여부에 따라 분기한다(지금은 id 유효성 여부만)
			if(isIdValid){ // 상태에 따라 다른 동작을 하도록 분기한다.
				//type 속성이 submit인 요소를 찾아서 disabled 속성을 제거한다.
				//[type=submit]는 버튼이다
				//폼이 유효한 상태 버튼을
				document.querySelector("[type=submit]").removeAttribute("disabled"); //버튼의 속성에 disabled 삭제해서 안보이게
			}else{
				//type 속성이 submit인 요소를 찾아서 disabled="disabled" 속성을 추가한다.
				//폼이 유효하지 않은 상태
				document.querySelector("[type=submit]").setAttribute("disabled","disabled"); //버튼의 속성을 
			}
		};
		
		//아이디의 상태에 따라 버튼을 변화시켜라
		//아디의 input 상태가 is-valid이면 버튼 변화
		/*
		if(document.querySelector("#id").classList=="is-invalid"){
			isIdValid=true;
			checkForm(isIdValid);
		}else{
			isIdValid=false;
			checkForm(isIdValid);
		}
		*/
		
		//아이디를 검증할 정규표현식
		const reg_id=/^[a-z].{4,9}$/; //대부분의 시작과 끝을 표시해야 한다.
		//정규표현식이 아니면 제출을 막게끔
		//폼안에 있는 submit  버튼을 누르면 form 이 제출되는 기본 동작을 한다.
		
		//id를 입력할 때마다 실행할 함수 등록
		document.querySelector("#id").addEventListener("input",(event)=>{
			//일단 is-valid, is-invalid 클래스를 모두 지우고
			//첫 글자를 입력하는 순간에는 코드가 실행되도 영향을 끼치지 않는다.
			event.target.classList.remove("is-valid","is-invalid")
			//현재까지 입력한 아이디를 읽어온다.
			let inputId=event.target.value;
			//만일 정규표현식을 통과하지 못한다면
			if(!reg_id.test(inputId)){
				/*
					어떤 요소에 클래스를 추가하는 방법
					.classList.add("클래스명")
				*/
				event.target.classList.add("is-invalid"); //요소에서 valid-feedback를 화면에 보여준다.
				//아이디 상태값 변경
				isIdValid=false;
			}else{
				event.target.classList.add("is-valid");
				//아이디 상태값 변경
				isIdValid=true;
			}
			//상태값을 이용해서 UI를 변경하는 함수 호출
			checkForm();
			
		});	
		
	</script>
</body>
</html>