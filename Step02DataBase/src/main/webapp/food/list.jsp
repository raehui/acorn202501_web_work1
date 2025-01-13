<%@page import="java.util.List"%>
<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//맛집 데이터 리스트 얻어오기
	FoodDao dao=new FoodDao();
	//리스트 생성후 dto을 담아서 list 반환
	List<FoodDto>list=dao.getList();
	
	//불러올 객체가 List<FoodDto>의 리스트며 dao를 이용해서 fooddto 리스트를 불러오기

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/list.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include> <%-- --%>
</head>
<body>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="food" name="current"/>
	</jsp:include> 
	<div class="container">
		<a href="${pageContext.request.contextPath}/food/insertform.jsp">
		<svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
		  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
		  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/>
		</svg>
		<span class="visually-hidden">맛집추가</span>
		</a> 
		<%--이동할 인서트를 할 폼페이지로 이동 , 소문자 & _ 정도만 작성--%>

		<h1>맛집 목록입니다</h1>
		<table class="table table-borederd"> 
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>타입</th>
					<th>이름</th>
					<th>가격</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%for(FoodDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td> <%--DB에 있는 데이터를 불러옴/ 동적인 내용 --%>
					<td><%=tmp.getType()%></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getPrice() %></td>
					<td><a href="updateform.jsp?num=<%=tmp.getNum()%>">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
					  <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492M5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0"/>
					  <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115z"/>
					</svg>
					<span class="visually-hidden">수정</span>
					</a>
					</td> <%--나의 정보가 있는 페이지에 들어감 --%>
					<td><a href="javascript: deleteConfirm(<%=tmp.getNum() %>)">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
					  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
					  <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
					</svg>
					<span class="visually-hidden">삭제</span>
					</a>
					</td>
					<%--자바스크립트의 영역으로 작성된 코딩이 실행 
					
					--%>				
				</tr>
				<%}%>

			</tbody>
		</table>
	</div>
	<script>
		function deleteConfirm(num){ //매개변수를 하나 받아서 
			const isDelete= confirm("삭제할까요?");
			//확인을 눌었을 때만 delete.jsp 페이지로 이동하도록 한다.
			if(isDelete){
				//javascript 를 이용해서 페이지 이동
				//내가 원하는 페이지로 이동하기 
				location.href = "delete.jsp?num="+num;
			}
			
		}
	</script>

</body>
</html>