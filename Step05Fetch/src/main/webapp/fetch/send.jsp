<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//GET 방식으로 요청 파라미터 읽어오기
	String msg=request.getParameter("msg");
	System.out.println("msg: "+msg);
	
%>
<%--문자열로 작성하면 활용하기가 너무 힘듬, html도 자바스크립에서는 단지 문자열--%>
<%--자바스크립트에서는 json 형식으로 작성하면 자동으로 object 객체로 변경이 가능하다. --%>
<%--즉 html형식과 문자열은 응답해도 무용지물 --%>
메서지 잘 받았어 클라이언트야!  
1
김구라
노량진
true
78