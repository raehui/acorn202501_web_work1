<%@page import="test.user.dto.SessionDto"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="test.post.dto.CommentDto"%>
<%@page import="test.post.dao.CommentDao"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//세션에 저장된 정보를 이용해서
	SessionDto sessionDto=(SessionDto)session.getAttribute("sessionDto");
	//댓글 작성자의 username 을 얻어낸다.
	String writer=sessionDto.getUserName();
	// ftech() 를 이용해서 전송되는 정보를 추출한다.
	long postNum = Long.parseLong(request.getParameter("postNum"));
	String targetWriter = request.getParameter("targetWriter");
	String content = request.getParameter("content");
	//원글의 댓글일 경우 parentNum 이 넘어 오지 않아서 null 이다.
	String strParentNum = request.getParameter("parentNum");
	long parentNum = 0;
	
	CommentDao dao=CommentDao.getInstance();
	//저장할 댓글의 글번호를 미리 얻어낸다.
	long num=dao.getSequence();
	//만일 parentNum 이 넘어오지 않으면 
	if(strParentNum == null){
		parentNum=num; // 댓글의 글번호가 parentNum 이 된다.
	}else{
		//넘어 온다면 넘어오는 값을 parentNum 으로 설정한다.
		parentNum=Long.parseLong(strParentNum);
	}
	//저장할 댓글 정보
	CommentDto dto=new CommentDto();
	dto.setNum(num);
	dto.setWriter(writer);
	dto.setPostNum(postNum);
	dto.setTargetWriter(targetWriter);
	dto.setContent(content);
	dto.setParentNum(parentNum);

	boolean isSuccess=dao.insert(dto);
	if(!isSuccess){
		response.sendError(500, "댓글 추가 실패!");
		return;
	}
	//DB 에 저장된 정보를 다시 읽어오기
	dto=dao.getData(num);
	//Gson  객체를 이용해서 CommentDto 에 저장된 정보를 json 문자열로 변환해서 응답한다.
	Gson gson=new Gson();
%>
<%--자동으로 CommentDto 에 저장된 정보를 json 문자열 형식으로 응답! --%>
<%=gson.toJson(dto) %>










