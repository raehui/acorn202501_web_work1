<%@page import="java.util.List"%>
<%@page import="test.post.dto.PostDto"%>
<%@page import="test.post.dao.PostDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//검색조건이 있는지 읽어와 본다.
	String condition=request.getParameter("condition");
	String keyword=request.getParameter("keyword");
	String findQuery=null;
	//있다면 dto 에 해당 정보를 담는다.
	//데이터를 담기 위해서 dto를 만듬
	PostDto dto=new PostDto();
	
	if(condition != null){
		dto.setCondition(condition);
		dto.setKeyword(keyword);
		//검색조건과 키워드에 관한 정보를 계속 뽑기위해서 작성 
		findQuery="&condition="+condition+"&keyword="+keyword;
	}
	//글 5개를 1페이지에 보여주기위해 작성
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단에 글 페이지 번호를 5개를 표시하기 위해서 작성한다.
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	
	//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	int pageNum=1;
	
	//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	String strPageNum=request.getParameter("pageNum");
	//만일 페이지 번호가 파라미터로 넘어 온다면
	//아마도 보여줄 페이지가 2로 바뀐다면 그때 실행될 거 같음
	if(strPageNum != null){
		//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
		pageNum=Integer.parseInt(strPageNum);
	}	
	// 페이지에 따라서 그 페이지에 보여줄 행의 내용을 변경을 위한 조건을 만듬
	// 보여줄 페이지의 시작 ROWNUM 
	int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	//보여줄 페이지의 끝 ROWNUM
	int endRowNum=pageNum*PAGE_ROW_COUNT;
	
	//페이징 처리
	//하단 시작 페이지 번호 
	int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
	//하단 끝 페이지 번호
	int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	//전체 글의 갯수
	int totalRow=PostDao.getInstance().getCount(dto);
	//전체 페이지의 갯수 구하기
	//몇 페이지로 페이징 처리 = 글의 갯수/하나의 페이지에 보일 글의 수
	int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	// 우리가 설정한 값으로 구한 endPageNum와 글의 목록으로 구한 totalPageCount을 비교해서 보정해준다.
	if(endPageNum > totalPageCount){
		endPageNum=totalPageCount; //보정해 준다. 
	}	
	
	// startRowNum 과 endRowNum 을 PostDto 객체에 담아서
	// 한 페이지에 들어가는 시작번호, 끝번호를 리스트에 담기위해서 dto에 담는다.
	dto.setStartRowNum(startRowNum);
	dto.setEndRowNum(endRowNum);
	
	
	//보여줄 페이지에 해당하는 글 목록을 얻어온다.
	//검색조건이 들어 있을수도 있는 dto 를 메소드에 넘겨 주면서 글 목록을 얻어낸다.
	//검색조건과 키워드에 맞는 글을 startRowNum,endRowNum을 통해서 불러온다.
	List<PostDto> list=PostDao.getInstance().getList(dto);
	/*
		jsp 페이지에서 응답에 필요한 데이터를 el에서 활용할 수 있도록
		request 객체에 특정 키값으로 담는다.
	*/
	request.setAttribute("list", list);
	request.setAttribute("startPageNum", startPageNum);
	request.setAttribute("endPageNum", endPageNum);
	request.setAttribute("totalPageCount", totalPageCount);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("totalRow", totalRow);
	request.setAttribute("dto", dto);
	request.setAttribute("findQuery", findQuery);
	
	//어떤 키값으로 어떤 type 을 담았는지 기억하기
	//request와 session은 기억할만한 데이터인가? 생각하기
	System.out.println(pageNum);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/post/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath }/post/protected/new.jsp">새글 작성</a>
		<h1>게시글 목록 입니다</h1>
		<table class="table table-striped">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${list}">
					<tr>
						<td>${post.num}</td>
						<td>${post.writer}</td>
						<td>
							<%--findQuery(=condition, keyword 정보)이면서 null이면 null이 아니라 빈 값이 나옴--%>
							<%--검색조건과 키워드에 해당하는 글의 정보를 제목 링크로 가지고 오기위해서 --%>
							<a href="view.jsp?num=${post.num}${findQuery}">${post.title}</a>
						</td>
						<td>${post.viewCount}</td>
						<td>${post.createdAt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav>
			<ul class="pagination">
				<!-- Prev 버튼 -->
				<!-- 페이징 처리의 첫번째 번호가 1이 아니면 Prev 버튼이 생기게 -->
				<c:if test="${startPageNum ne 1}">
					<li class="page-item">
						<a class="page-link" href="list.jsp?pageNum=${startPageNum - 1}${findQuery}">Prev</a>
					</li>
				</c:if>
				<!-- 페이지 번호 -->
				<c:forEach begin="${startPageNum}" end="${endPageNum}" var="i">
					<li class="page-item ${i == pageNum ? 'active' : ''}">
						<a class="page-link" href="list.jsp?pageNum=${i}${findQuery}">${i}</a>
					</li>
				</c:forEach>
				<!-- Next 버튼 -->
				<!-- 현재 페이지 블록의 마지막 페이지 번호가 전체 페이지 수보다 작을때만 실행된다.-->
				<!-- 즉 아직 남아있는 페이지가 잇을 때만 Next 버튼이 생긴다. -->
				<c:if test="${endPageNum < totalPageCount}">
					<li class="page-item">
						<a class="page-link" href="list.jsp?pageNum=${endPageNum + 1}${findQuery}">Next</a>
					</li>
				</c:if>
			</ul>		
		</nav>
        <form action="${pageContext.request.contextPath }/post/list.jsp" method="get">
        	<label for="condition">검색조건</label>
        	<select name="condition" id="condition">
        		<option value="title_content" ${dto.condition eq 'title+content' ? 'selected' : ''}>제목 + 내용</option>
        		<option value="title" ${dto.condition eq 'title' ? 'selected' : ''}>제목</option>
        		<option value="writer" ${dto.condition eq 'writer' ? 'selected' : ''}>작성자</option>
        	</select>
        	<!-- 검색을 눌러도 내가 입력한 키워드가 입력창에 남아있음 -->
        	<input type="text" name="keyword" placeholder="검색어..." value="${dto.keyword }"/>
        	<button class="btn btn-primary btn-sm" type="submit">검색</button>
        	<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath }/post/list.jsp">새로고침</a>
        </form>
        <c:if test="${not empty dto.keyword }">
        	<p>
        		<strong>${totalRow }</strong> 개의 자료가 검색 되었습니다.
        	</p>
        </c:if>				
	</div>
</body>
</html>