<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	//1. 업데이트할 내용 가져오기
	//input 요소들로 받은 데이터가 각 name 이라는 이름표를 달아서 파라미터에 담겨있어
	int num=Integer.parseInt(request.getParameter("num"));
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	//GuestDao 객체의 참조값
	GuestDao dao=GuestDao.getInstance();
	//DB에 저장된 비밀번호
	String savePwd=dao.getDate(num).getPwd();
	//작업의 성공여부를 저장할 변수 만들고 false 를 초기값으로 부여한다.
	//html 부분에서 사용하기 위해서는 if 외부에서 선언함
	boolean isSuccess=false;
	//만일 비밀번호가 일치한다면
	if(pwd.equals(savePwd)){
		//수정할 글정보를 GuestDto 객체에 담고
		GuestDto dto=new GuestDto();
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setContent(content);
		dto.setPwd(pwd);
		//DB에 수정 반영한다.
		isSuccess=dao.update(dto);		
	}
	
	/*
	//2. DB에 전송하기
	GuestDto dto=new GuestDto();
	//파라미터에서 받아온 내용을 dto에 담기
	dto.setNum(num);
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setPwd(pwd);
	
	//DB에 있는 비밀번호랑 dto에 담긴 비밀번호 같으면 수정
	//DB에 있는 비밀번호 갖고오기
	GuestDto dto1=GuestDao.getInstance().getDate(num);
	//같으면 
	if(dto.getPwd().equals(dto1.getPwd())){
		GuestDao.getInstance().update(dto);
		//"/guest/list.jsp"페이지로 리다일렉트로 이동하라는 응답
		String cPath=request.getContextPath();
		//자체로 응답해서 비밀번호가 일치하면 여기서 끝
		response.sendRedirect(cPath+"/guest/list.jsp");		
	}
	
	//3. 응답하기
	*/
%>
	   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/update.jsp</title>
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p>
				<strong><%=writer %></strong> 님이 작성한 글이 수정되었습니다.
				<a href="list.jsp">목록보기</a>
			</p>
		<%}else { %>
			<p>
				수정실패!
				<a href="updateform.jsp?num=<%=num%>">다시 수정</a>
			</p>
		<%} %>
	</div>
	
	<%-- 내가 한 것
		<script>
		alert("비밀 번호를 확인하세요!");
		location.href="updateform.jsp?num=<%=dto.getNum()%>";	
		location.hsref = "${pageContext.request.contextPath}/guest/updateform.jsp?num=" + <%= num %>;
	</script>
	
	--%>

</body>
</html>