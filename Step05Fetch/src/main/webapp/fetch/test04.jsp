<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test04.jsp</title>
</head>
<body>
	<button id="getBtn">친구이름 목록 받아오기</button>
	<ul id="friendList">
	
	</ul>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		$("#getBtn").on("click", ()=>{
			fetch("friends.jsp")
			.then(res=>res.json())
			.then(data=>{ 
				//data 는 친구 이름이 순서대로 들어 있는 배열
				console.log(data);
				//반복문 돌면서 
				for(let i=0; i<data.length; i++){
					// li 요소를 만들어서 선택하고 innerText 출력한후 jquery 객체(배열)을 상수에 담기
					const li = $("<li>").text(data[i]);
					// id 가 friendList 인 요소에 추가하기
					$("#friendList").append(li);
				}
			})
			.catch(error=>{
				console.log("에러정보:"+error);
			});
		});
	</script>
</body>
</html>







