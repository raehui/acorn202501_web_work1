<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" id="msg" placeholder="문자열 입력..." />
	<button id="sendBtn">전송</button>
	<button id="sendBtn2">전송2</button>
	<!-- jquery 로딩 -->
	<!-- jquery 영역이랑 스크립트 영역이랑 구분해야 함 -->
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		//id 가 sendBtn2 인 곳에 "click" 이벤트가 발생하면 호출될 함수 등록
		$("#sendBtn2").on("click",()=>{
			//id 가 msg 인 곳의 value 값을 읽어와서 msg 라는 상수에 담기
			const msg=$("#msg").val(); 
			
			fetch("send2.jsp?msg="+msg) //페이지에 요청하고 응답을 받아오기
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
			})
			//예외 추가하기
			.catch(error=>{
				console.log("에러정보"+error);
			});
		});
	
	document.querySelector("#sendBtn").addEventListener("click",()=>{
		//입력한 문자열 읽어오기
		const msg=document.querySelector("#msg").value;
		//fetch 함수를 호출하면서 GET 방식 파라미터 send.jsp 페이지를 요청하면서 전달한다.
		fetch("send.jsp?msg="+msg) <%--send.jsp 페이지로부터 요청하고 받아옴/ 입력한 내용을 파라미터로 가지고 가서 요청함--%>
		.then(res=>res.text()) <%--응답--%>
		.then(data=>{ <%--data 매개 변수로 받아와서 콘솔에 출력--%>
			console.log(data);
			alert(data);
		});
		
	});
	<%-- 
		test02.jsp 에서 웹브라우저는 서버에 요청을 하고
		서버는 가지고 온 파라미터를 가지고 
		콘솔창에 응답을 준다.
		웹브라우저와 서버와의 대화 단 , 페이지의 이동 없이 응답이 이루어짐
		회원가입 할 때 아이디가 사용 가능한지 알아보기 위해서 서버에 보내봐야 안다.
	--%>
	
	<%--.json과 .text는 자동으로 파싱이 되느냐의 차이만 존재함--%>
	</script>
</body>
</html>