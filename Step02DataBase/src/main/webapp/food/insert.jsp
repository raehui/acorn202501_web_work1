<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//요청 객체를 통해서 insert form에서 제출된 파라미터 갖고오기
	//getParameter는 무조건 string 타입으로 출력
	String type=request.getParameter("type");
	String name=request.getParameter("name");
	int price=Integer.parseInt(request.getParameter("price"));
	
	//Fooddto에 담기
	FoodDto dto=new FoodDto();
	//dto 객체 안에 존재하는 메서드만을 사용 가능함
	dto.setType(type);
	dto.setName(name);
	dto.setPrice(price);
	//DB에 저장하기
	//dao에서 insert 실행하기
	FoodDao dao=new FoodDao();
	boolean isSuccess=dao.insert(dto);
	
	//비지니스 로직 수행
	
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/insert.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container mt-5">
		<h3>알림</h3>
		<script>
		//자바 영역과 자바 스크립트 영역의 구분? 동적인 느낌의 데이터 처리 , 뒤에서의 역할 / 정적인 느낌으로 UI 혹은 상호작용
		<%if(isSuccess){ %>
			alert("<%=name%>을(를) 추가했습니다!");
			location.href="list.jsp"; 
			//위치를 list.jsp로 이동하거라 / 결과에 따라서 다른 위치로 이동할 때 사용/ 이동하면 그 list.jsp의 로직 발동
		<%}else {%>
			alert("추가 실패");
			location.href="insertform.jsp"; //위치를 insertform.jsp로 이동해라
		<%} %>
		</script>
	</div>
	

</body>
</html>