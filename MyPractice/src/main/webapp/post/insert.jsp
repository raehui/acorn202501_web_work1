<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일기 작성 페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.css">
<style>
#editor {
	width: 100%;
	height: 300px;
}
/*일기 작성 제목과 폼을 웹페이지의 중간에 위치시키기 위해서 사용했다 */
.container {
	margin : 0 auto; /*블록 요소의 양쪽만 설정하면 중간 정렬이 가능 */
	width: 50%; /* body의 절반을 뜻함 */
	
}
</style>
	<!-- toast editor 를 사용하기 위한 css, javascript 로딩 -->
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
</head>
<body>
	<div class="container"   >
		<h1 class="mb-2">일기작성</h1>
		<form action="${pageContext.request.contextPath}/post/insertresult.jsp" method="get" id="diaryform">
			<!-- 왜 div 안에 굳이 label이랑 input 을 넣었을까? -->
			<div class="mb-2">
				<label for="title">제목</label>
				<input type="text" name="title" id="title" />
			</div>
			<div id="editor" name="editor"></div>
			<button class="mt-2" type="submit">제출하기</button>
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
	</script>
</body>
</html>