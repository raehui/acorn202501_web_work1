<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼 전송되는 파라미터를 읽어와서 
	int num=Integer.parseInt(request.getParameter("num"));
	String type=request.getParameter("type");
	String name=request.getParameter("name");
	int price=Integer.parseInt(request.getParameter("price"));
	//FoodDto 에 담고
	FoodDto dto=new FoodDto();
	dto.setNum(num);
	dto.setName(name);
	dto.setType(type);
	dto.setPrice(price);
	//DB 에 수정 반영
	boolean isSuccess=new FoodDao().update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/update.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p>
				음식 정보를 수정 했습니다. 
				<a href="list.jsp">확인</a>
			</p>
		<%}else{ %>
			 <p>
			 	음식 정보 수정 실패!
			 	<a href="updateform.jsp?num=<%=num %>">다시 수정하러 가기</a>
			 </p>
		<%} %>
	</div>
</body>
</html>




