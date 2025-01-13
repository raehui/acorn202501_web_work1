<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//HttpServletRequest 객체를 이용해서 요청과 함께 전달된 쿠키 읽어내기
	//쿠키 하나당 하나의 객체를 생성해서 담는다.
	Cookie[] cooks=request.getCookies();

	//반복문 돌면서 
	for(Cookie tmp:cooks){
		//쿠키의 키값을 읽어온다.
		String key=tmp.getName();
		//해당 키값으로 저장된 value 값을 읽어온다.
		String value=tmp.getValue();
		
		System.out.println("key: "+key+" value: "+value);
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_cookie/cookie_read.jsp</title>
</head>
<body>
	<h1>저장된 쿠키 목록</h1>
	<table>
		<thead>
			<tr>
				<th>key</th>
				<th>value</th>
			</tr>
		</thead>
		<tbody>
			<%for(Cookie tmp:cooks){ %>
				<tr>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getValue() %></td>
				</tr>
			<%} %>
		</tbody>
	</table>
	<a href="cookie_form.jsp">다시 테스트</a>
</body>
</html>