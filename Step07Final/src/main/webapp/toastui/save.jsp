<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//Editor 로 입력한 html 형식의 문자열을 DB 에 저장했다가 필요시 불러와서 그대로 html 형식으로 출력하면 된다.
	String content=request.getParameter("content");
	System.out.println(content);
	//javascript 문자열의 구조를 망가트리지 않게 하기 위해  
	String content2 = content.replace("\"", "\\\""); // " 를  \"   로 변경하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/toastui/save.jsp</title>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<style>
	.container{
		width:80%;
		margin:0 auto;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>게시글</h1>
		<div><%=content %></div>
		
		<h1>글 수정 폼</h1>
		<div id="editor"></div>
		<form action="save.jsp" method="post" id="updateForm">
			<input type="hidden" name="content" id="content"/>
			<button type="submit">수정확인</button>
		</form>
	</div>
	<script>
		// Editor 클래스 얻어내기
	    const Editor = toastui.Editor;
	    // Editor 객체 생성하면서 option 전달하기 
	    const editor = new Editor({
	        el: document.querySelector('#editor'), //어디에 Editor 를 만들것인지
	        height: '500px', // 높이 
	        initialEditType: 'wysiwyg', //Editor type 설정  markdown | wysiwyg 
	        previewStyle: 'vertical' //미리 보기 설정  vertical | tab
	    });
	    
	    editor.setHTML("<%=content2 %>");
	    
        document.querySelector("#updateForm").addEventListener("submit", (event)=>{
        	//에디터에 입력한 내용을 input type="hidden" 의 value 값으로 넣어준다.
        	document.querySelector("#content").value = editor.getHTML();
        	//폼에 이거말고 다른 내용도 입력했다면 검증을 여기서 하고 
        	alert(editor.getHTML())
        	//폼 전송을 막고 싶으면
        	// event.preventDefault();  가 실행되게 하면 된다. 
        });
	</script>
</body>
</html>






