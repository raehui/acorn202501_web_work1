<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fetch/test01.jsp</title>
</head>
<body>
	<h3>fetch 함수 테스트	</h3>
	<button id="myBtn">눌러보셈</button>
	<script>
		document.querySelector("#myBtn").addEventListener("click",()=>{
			 fetch("${pageContext.request.contextPath }/index.jsp") 
			.then(function(res){ 
				return res.text();
			})
			.then(function(data){
				console.log(data);
			});
			
			fetch("${pageContext.request.contextPath }/index.jsp")
			.then(res=>{
				return res.text();
			})
			.then(data=>{
				console.log(data);
			});
			/*
				서버(jsp or servlet)에서 응답한 문자열이 json 형식이면
				return res.json();
			
				그 이외의 형식이면 html, xml, plain text 등등
				return res.text();
			*/
			
			 fetch("${pageContext.request.contextPath }/index.jsp")
			.then(res=>res.text()) 
			.then(data=>{
				/*
					위의 then()함수에서 res.json()를 리턴하면 data는 응답된 json 문자열을
					JSON.parse()과정을 이미 거친 object 나 array 이다.
					
					위의 then 함수에서 res.text()를 리턴하면 data는 서버가 응답한 문자열 (String)이다.
				*/
				/*
					페이지 전환없이 javascript로 요청하면 응답된 문자열이 두번째 then()에 전달한 함수의 매개변수에 전달된다.
					
					=> 페이지가 바뀌지 않고 요청이 되기위해서는
				*/
				
				console.log(data);
				}); 
				
			
			 fetch("${pageContext.request.contextPath}/index.jsp") // 애플리케이션의 루트 경로에 있는 index.jsp로 요청을 보냄
			    .then(res => res.text()) // 응답(res)을 문자열(text)로 변환
			    .then(data => {
			        console.log(data); // 변환된 데이터를 콘솔에 출력
			    });
		});
	</script>
</body>
</html>