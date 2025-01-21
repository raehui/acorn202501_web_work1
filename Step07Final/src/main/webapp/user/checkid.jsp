<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1.GET 방식 파라미처로 전달되는 입력한 id값을 읽어온다.
	String userName=request.getParameter("userName");
	//2. DB에서 해당 회원 정보가 잇는지 확인해서 사용가능 여부를 알아낸다.
	//userName 데이터가 있으면 null 아니기에 false
	//userName 데이터가 없으면 null 이기에 true
	
	//UserDao.getInstance()는 dao를 반환함
	UserDto dto=UserDao.getInstance().getData(userName);
	boolean canUse= dto ==null? true : false;
%>
{
	"canUse" : <%=canUse%>
}