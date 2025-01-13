<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//리스트에는 내가 작성한 목록이 나와야 함
	//방명록 글 목록을 얻어온다.
	List<GuestDto>list=GuestDao.getInstance().getList();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/list.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include> 
</head>
<body>
	<jsp:include page="/include/navbar.jsp"> 
		<jsp:param value="guest" name="current"/> 
	</jsp:include>
	
	<div class="container">
		<a href="insertform.jsp">새글 작성</a>
		<h1>방명록 목록 입니다.</h1>
		<table class="table table-bordered">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>등록일</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%for(GuestDto tmp:list){ %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getWriter()%></td>
						<td>
							<textarea readonly style="height: 100px; resize:none;" class="form-control"><%=tmp.getContent() %></textarea>	
												
						</td>
						<td><%=tmp.getRegdate() %></td>
						<td>
							<%--번호에 따라서 각자의 수정페이지로 접속 --%>
							<a href="updateform.jsp?num=<%=tmp.getNum()%>">수정</a>
						
						</td>
						<td>
							<form action="delete.jsp" method="post"> <%--폼은 여러가지 요소들을 카테고리화 하기 위해 --%>
								<%--
									num이라는 이름으로 데이터가 전송되지만 화면에 보이지 않음
									그렇다면 개발자가 데이터를 보여주지 않고 이용만 할 때 사용
								 --%>
								<input type="hidden" name="num" value="<%=tmp.getNum() %>" /> 
								<input type="text" name="pwd" placeholder="비밀번호 입력..." />
								<button class="btn btn-danger btn-sm" type="submit">삭제</button>
							</form>
						</td>
					</tr>
				<%} %>
			</tbody>

		</table>
	</div>
</body>
</html>