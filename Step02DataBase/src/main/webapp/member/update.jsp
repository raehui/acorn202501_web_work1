<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1.폼 전송되는 수정할 회원의 번호, 이름, 주소를 추출한다.
	int num=Integer.parseInt(request.getParameter("num"));
	String name=request.getParameter("name");
	String addr=request.getParameter("addr");	
	//2.  DB에서 수정 반영한다.
	MemberDto dto=new MemberDto(num,name,addr);
	boolean isSuccess=new MemberDao().update(dto);
	//3. 응답하기
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>
	<script>
		<%if(isSuccess){%>
			//알림창을 띄우고
			alert("수정했습니다");
			//list.jsdp 페이지로 이동한다.
			location.href="list.jsp";
		<%}else{%>				
			//알림창의 띄우고
			alert("수정 실패");
			//updateform.jsp페이지로 이동하면서 num이라는 파라미터명으로 수정할 회원의 번호를 가지고 간다.
			location.href="updateform.jsp?num=<%=num%>";
		<%}%>
		
	</script>
</body>
</html>