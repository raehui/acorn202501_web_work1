<%@page import="test.food.dto.FoodDto"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//수정할 데이터를 읽어오기
	int num=Integer.parseInt(request.getParameter("num"));
	//수정할 음식의 정보를 DB에서 읽어온다.
	FoodDao dao=new FoodDao();
	FoodDto dto=dao.getData(num);	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/updateform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav> <%-- --%>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath}"}>HOME</a>
			</li>
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath}/food/list.jsp"}>음식목록</a>
			</li>
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath}/food/updateform.jsp?num=<%=num%>"}>음식수정</a>
			</li>
			
		</ol>
		</nav>
	
	<h1>메뉴 정보 수정 품</h1>
	
	<p><%=true ? "selected":"" %></p> <%--삼항 연산자는 if else 문이 너무 더러울 때 사용 --%>
	<form action="update.jsp" method="post"> <%--method는 단순히 주소창에 보일지 말지의 결정으로 파라미터와 내용은 존재함--%>
		<!-- 화면에 보지이는 않지만 폼 제출할 때 같이 전송되는 값 -->
		<input type="hidden" name="num" value="<%=dto.getNum() %>" /> <%--input[type=hidden] : 속성 작성하기--%>
		<div class="mb-3">
			<label class="form-label" for="type">타입</label>
			 
			<select name="type" id="type">
				<option value="">선택</option>
				<%--.equals()는 자바에서 참조 객체값의 실제값을 비교할 때 사용함 --%>
				<option <%=dto.getType().equals("kor") ? "selected":"" %>>한식</option>
				<option <%=dto.getType().equals("cha") ? "selected":"" %> >중식</option>
				<option <%=dto.getType().equals("jap") ? "selected":"" %> >일식</option>
				<option <%=dto.getType().equals("for") ? "selected":"" %>>양식</option>
				<%--<option <%if(dto.getType().equals("kor")){ %>selected<%}else{ %> <%}%>>한식</option> --%>
				<%--jsp문으로 if else 문 활용해서 값 얻어오기 
				<option <%if(dto.getType().equals("kor")){String a="selected";}else{String a="";}%>a>한식</option>
				--%>
								
			</select>
		</div>

		<div class="mb-3" >
			<label for="name" class="form-label">이름</label>
			<input type="text" name="name" id="name" value="<%=dto.getName()%>" class="form-control"/> 
		</div>
		<div class="mb-3" >
			<label for="price" class="form-label">가격</label>
			<input type="number" name="price" id="price" max="100000" min="1000" step="100" value="<%=dto.getPrice()%>" class="form-control"/>
		</div>
		<button type="submit" class="btn btn-success">수정</button>
		<button type="reset" class="btn btn-danger ">취소</button>
	</form>
	</div>

</body>
</html>