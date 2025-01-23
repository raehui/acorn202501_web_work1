<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.SessionDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//session 에 저장된 정보를 이용해서 
	SessionDto sessionDto=(SessionDto)session.getAttribute("sessionDto");
	//DB 에 저장된 회원 정보(UserDto) 를 얻어와서
	//모든 정보가 있다.
	UserDto dto=UserDao.getInstance().getData(sessionDto.getNum());
	//회원 정보 수정 폼을 응답한다.
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/update-form.jsp</title>
<style>
	#profileImage{
		width: 100px;
		height: 100px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
	#profileFile{
		display: none;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>회원 정보 수정 양식</h3>
		<%--파일 업로드 때문에 서블릿으로 작업한다. --%>
		<%--경로가 서블릿으로 접속하게끔 설정되어있다. --%>
		<%--
			enctype="multipart/form-data"은 인코딩하는 방법을 설정한다. 
			파일 업로드가 포함된 폼에는 반드시 작성
		--%>
		<form action="${pageContext.request.contextPath }/user/protected/update-profile" 
			method="post" id="myForm" enctype="multipart/form-data">
			<%--이미지만 입력할 수 있다.  --%>
			<%--이미지를 여러개 선택 가능 multiple--%>
			<input type="file" name="profileFile" id="profileFile" accept="image/*"/>
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" value="<%=dto.getUserName() %>" readonly/>
			</div>
			<div>
				<label for="email">이메일</label>
				<input type="text" id="email" name="email" value="<%=dto.getEmail()%>"/>
			</div>
			<div>
				<label>프로필 이미지</label>
				<div>
					<a href="javascript:" id="profileLink">
						<%if(dto.getProfileImage()==null){ %>
							<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
								<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
								<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
							</svg>
						<%}else{ %>
						<%--alt는 속성의 접근성을 위해 작성 = 리더기 --%>
							<img id="profileImage" src="${pageContext.request.contextPath}/upload/<%=dto.getProfileImage() %>" alt="프로필 이미지" />
						<%} %>
					</a>
				</div>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
		
	</div>
	<script>
		//프로필이미지 출력란에 원래 있었던 html 을 변수에 담아 두었다가
		const saved=document.querySelector("#profileLink").innerHTML;
		//취소 버튼을 누르면
		//html의 reset type의 버튼은 input type의 초기값을 기억하는 기능이 내재되어 있음.
		//그렇기 때문에 자동으로 복구되는 것이다.
		document.querySelector("#myForm").addEventListener("reset", ()=>{
			//변수에 담아둔 내용을 이용해서 원상 복구 시킨다.
			//이미지 담아둔 영역에만 초기값을 집어넣는다.
			document.querySelector("#profileLink").innerHTML=saved;
		});
		
		//링크를 클릭했을때
		//프로파일링크 (이미지)를 클릭하면 이미지를 클릭하는 창(profileFile)이 나오게 설계
		//profileFile은 css로 숨겼음
		document.querySelector("#profileLink").addEventListener("click", ()=>{
			// input type="file" 요소를 강제 클릭해서 파일 선택 창을 띄운다.
			document.querySelector("#profileFile").click();
		});
		//새로운 이미지가 선택되었을때
		document.querySelector("#profileFile").addEventListener("change", (e)=>{
			console.log("hi")
			//선택된 파일 배열 객체를 얻어낸다.
			//e.target는 이미지가 아닌 파일의 정보를 가지고 옴
			const files = e.target.files; //사용자가 선택한 이미지의 파일 정보를 배열에 담는다.
			//만일 파일 데이터가 존재한다면
			if(files.length > 0){
				//파일로 부터 데이터를 읽어들일 객체 생성
				const reader=new FileReader();
				//로딩이 완료(파일데이터를 모두 읽었을때) 되었을때 실행할 함수 등록
				//파일을 다 읽으면 함수가 실행된다.
				//끝나는 시점에 이제 실행되는 함수를 등록함(다 읽어왔을 때의 시점을 맞추기 위해서  .onload)
				//다 읽은 결과는 event로 들어온다.
				reader.onload=(event)=>{
					//읽은 파일 데이터 얻어내기 
					//다 읽으면 긴 문자열이 나옴
					//다 읽은 다음에 작업을 하고싶으면 함수 안에 작성
					const data=event.target.result;
					//사진 데이터를 특별한 문자로 읽어오면 웹브라우저가 그걸 이미지로 해석한다.
					const img=`<img src="\${data}" id="profileImage" alt="프로필이미지">`;
					document.querySelector("#profileLink").innerHTML=img;
				};
				//파일을 DataURL 형식의 문자열로 읽어들이기
				reader.readAsDataURL(files[0]);
				
			}
		});
	</script>
</body>
</html>