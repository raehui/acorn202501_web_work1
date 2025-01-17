<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form5.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container" id="app">
		<h1>회원가입 폼 입니다 (vuejs 이용)</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				
				<input :class="{'is-valid':isIdValid ,'is-invalid':!isIdValid && isIdDirty}"
					@input="onIdInput"
					class="form-control" type="text" name="id" id="id"/>
					
				<small class="form-text">영문자 소문자로 시작하고 5~10 글자 이네로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디 입니다</div>
				<div class="invalid-feedback">사용할수 없는 아이디 입니다</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input :class="{'is-valid':isPwdValid, 'is-invalid':isPwdValid && isPwdDirty}" 
				@input="onPwdInput"
				v-model="pwd" class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input @input="onPwdInput" 
				v-model="pwd2" class="form-control" type="password"  id="pwd2"/>
			</div>		
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label>
				<input @input="onEmailInput"
					:class="{'is-valid':isEmailValid, 'is-invalid':!isEmailValid &&isEmailDirty }"
					class="form-control" type="email" name="email" id="email"/>
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요.</div>
			</div>				
			<button class="btn btn-success" type="submit" v-bind:disabled="!isIdValid || !isPwdValid || !isEmailValid">가입</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script>
		new Vue({
			el:"#app",
			data:{
				isIdValid:false,
				isIdDirty:false,
				isPwdValid:false,
				isPwdDirty:false,
				isEmailValid:false,
				isEmailDirty:false,
				pwd:"", //e.target 할 필요없이 
				pwd2:""
			},
			methods:{
				onPwdInput(){
					this.isPwdDirty=true;
					
					
					//함수를 미리 만들어서
					const checkPwd = ()=>{
						const reg_pwd=/[\W]/;
						//일단 정규표현식을 만족하는지 확인해서 만족하지 않으면 함수를 여기서 종료(return) 해야 한다.
						//만일 첫번째 비밀번호가 정규표현식을 통과하지 못하거나 또는 
						//두번째 비밀번호가 정규표현식을 통과하지 못한다면 isPwdValid 를 false 로 변경하고 checkForm() 호출
						if(!reg_pwd.test(this.pwd) || !reg_pwd.test(this.pwd2)){
							isPwdValid=false;
							return;
						}
						//양쪽에 입력한 비밀번호가 같은지 확인해서 같으면 isPwdValid 를 true 
						// 다르면 isPwdValid 를 false 로 변경하고 checkForm() 호출 
						if(this.pwd == this.pwd2){
		
							//비밀번호가 유효 하다는 의미에서 true 를 넣어준다.
							isPwdValid=true;
						}else{
							//비밀번호가 유효 하지 않다는 의미에서 false 를 넣어준다.
							isPwdValid=false;
						}
					
				},

				
				
				onIdInput(e){
					this.isIdDirty=true;
					//아이디를 검증할 정규 표현식 
					const reg_id=/^[a-z].{4,9}$/;
					//e.target 은 id input 요소의 참조값 
					
					//현재까지 입력한 아이디를 읽어온다.
					let inputId=e.target.value;
					//만일 정규표현식을 통과하지 못했다면 
					if(!reg_id.test(inputId)){
						//아이디의 상태값 변경
						this.isIdValid=false;
						return;
					}
					//fetch() 함수를 이용해서 get 방식으로 입력한 아이디를 보내고 사용가능 여부를 json 으로 응답받는다.
					fetch("${pageContext.request.contextPath }/user/checkid.jsp?id="+inputId)
					.then(res=>res.json())
					.then(data=>{		
						//만일 사용할수 있는 아이디라면
						if(data.canUse){
							//사용할수 있는 아이디라는 의미에서 true 를 넣어준다.
							this.isIdValid=true;
						}else{
							//사용할수 없는 아이디라는 의미에서 false 를 넣어준다.
							this.isIdValid=false;
						}
					});
					
				},
				
				onEmailInput(e){
					this.isEmailDirty=true;
					
					const reg_email=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
					
						//입력한 문자열 읽어오기 
						const email=e.target.value;
						//만일 email 을 제대로 입력 했다면 
						if(reg_email.test(email)){
							this.isEmailValid=true;
							
						}else{//제대로 입력하지 않았다면
							this.isEmailValid=false;
							
						}
				}
				
			}
		});
		
		/*	
		//아이디 유효성 여부를 관리할 변수를 만들고 초기값 부여 
		let isIdValid=false;
		//비밀번호 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isPwdValid=false;
		//이메일 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isEmailValid=false;
		
		const checkForm = ()=>{
			//폼 전체의 유효성 여부에 따라 분기한다 (지금은 id 유효성 여부만)
			if(isIdValid && isPwdValid && isEmailValid){
				// type 속성이 submit 인 요소를 찾아서 disabled 속성을 제거한다.
				document.querySelector("[type=submit]").removeAttribute("disabled");
			}else{
				// type 속성이 submit 인 요소를 찾아서 disabled="disabled" 속성을 추가한다.
				document.querySelector("[type=submit]").setAttribute("disabled", "disabled");
			}
		};
		
		//아이디를 검증할 정규 표현식 
		const reg_id=/^[a-z].{4,9}$/;
		
		// id 를 입력할때마다 실행할 함수 등록 
		document.querySelector("#id").addEventListener("input", (event)=>{
			//일단 is-valid, is-invalid 클래스를 모두 지우고 
			event.target.classList.remove("is-valid", "is-invalid");
			
			//현재까지 입력한 아이디를 읽어온다.
			let inputId=event.target.value;
			//만일 정규표현식을 통과하지 못했다면 
			if(!reg_id.test(inputId)){
				
				//어떤 요소에 클래스를 추가하는 방법
				//.classList.add("클래스명")
				
				event.target.classList.add("is-invalid");
				//아이디의 상태값 변경
				isIdValid=false;
				//아이디 상태값 변경이 버튼의 disabled 속성에 변화를 주도록 checkForm() 함수 호출
				checkForm();
				return;
			}
			//fetch() 함수를 이용해서 get 방식으로 입력한 아이디를 보내고 사용가능 여부를 json 으로 응답받는다.
			fetch("${pageContext.request.contextPath }/user/checkid.jsp?id="+inputId)
			.then(res=>res.json())
			.then(data=>{
				//일단 클래스를 제거한 후에 
				event.target.classList.remove("is-valid", "is-invalid");
				//만일 사용할수 있는 아이디라면
				if(data.canUse){
					event.target.classList.add("is-valid");
					//사용할수 있는 아이디라는 의미에서 true 를 넣어준다.
					isIdValid=true;
				}else{
					event.target.classList.add("is-invalid");
					//사용할수 없는 아이디라는 의미에서 false 를 넣어준다.
					isIdValid=false;
				}
				checkForm();
			});
		});
		
		//비밀번호를 검증할 정규표현식 객체
		const reg_pwd=/[\W]/;
		
		//함수를 미리 만들어서
		const checkPwd = ()=>{
			//양쪽에 입력한 비밀번호를 읽어와서 (2줄 코딩)
			let pwd=document.querySelector("#pwd").value;
			let pwd2=document.querySelector("#pwd2").value;
			
			//일단 is-valid 와 is-invalid 클래스를 제거를 먼저하고 (1줄 코딩)
			document.querySelector("#pwd").classList.remove("is-valid", "is-invalid");
			
			//일단 정규표현식을 만족하는지 확인해서 만족하지 않으면 함수를 여기서 종료(return) 해야 한다.
			//만일 첫번째 비밀번호가 정규표현식을 통과하지 못하거나 또는 
			//두번째 비밀번호가 정규표현식을 통과하지 못한다면 isPwdValid 를 false 로 변경하고 checkForm() 호출
			if(!reg_pwd.test(pwd) || !reg_pwd.test(pwd2)){
				document.querySelector("#pwd").classList.add("is-invalid");
				isPwdValid=false;
				checkForm();
				return;
			}
			
			//양쪽에 입력한 비밀번호가 같은지 확인해서 같으면 isPwdValid 를 true 
			// 다르면 isPwdValid 를 false 로 변경하고 checkForm() 호출 
			if(pwd == pwd2){
				document.querySelector("#pwd").classList.add("is-valid");
				//비밀번호가 유효 하다는 의미에서 true 를 넣어준다.
				isPwdValid=true;
			}else{
				document.querySelector("#pwd").classList.add("is-invalid");
				//비밀번호가 유효 하지 않다는 의미에서 false 를 넣어준다.
				isPwdValid=false;
			}
			checkForm();
		};
		
		document.querySelector("#pwd").addEventListener("input", checkPwd);
		document.querySelector("#pwd2").addEventListener("input", checkPwd);
			
		const reg_email=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		document.querySelector("#email").addEventListener("input", (event)=>{
			//입력한 문자열 읽어오기 
			const email=event.target.value;
			//일단 is-valid 와 is-invalid 클래스를 제거를 먼저하고 
			document.querySelector("#email").classList.remove("is-valid", "is-invalid");
			//만일 email 을 제대로 입력 했다면 
			if(reg_email.test(email)){
				isEmailValid=true;
				event.target.classList.add("is-valid");
			}else{//제대로 입력하지 않았다면
				isEmailValid=false;
				event.target.classList.add("is-invalid");
			}
			checkForm();
		});
		*/
	</script>
</body>
</html>










