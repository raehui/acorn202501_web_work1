<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//삭제할 데이터 갖고오기
	int num=Integer.parseInt(request.getParameter("num"));
	new FoodDao().delete(num); //응답 자체를 새로운 경로로 아예 강제 하는 경우로 장바구니 구매 시 로그인 페이지로 가게끔
	//3. 응답하기
	//특정 경로로 요청을 다시 하라는 리다일렉트 응답하기
	//list.jsp => delete.jsp => list.jsp 이런 이동이기 때문에 마치 새로 고침하는 듯한 느낌을 줄 수 있다.
	//이 자체가 응답이다!
	
	//context path 는 HttpServletResquest 객체를 이용해서 얻어낸 다음 사용해야 한다.
	//웹브라우저가 서버에 요청 시 구성을 이렇게 한다.
	//자동으로 경로를 작성할 때 생성
	String cPath=request.getContextPath();
	//서버가 사용자에게 답할 때 응답 방식 구성을 이렇게 강제한다.
	response.sendRedirect(cPath+"/food/list.jsp"); //"Step02DataBase"
%>    
