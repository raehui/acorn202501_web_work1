<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼 전송되는 내용 읽어오기
	String title=request.getParameter("title");
	String content=request.getParameter("content");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/insert.jsp</title>
<style>
	    #contents {
        margin-top: 20px;
        padding: 20px;
        background-color: #fefefe;
        border-radius: 10px; 
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        border: 1px solid #ddd;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }
    #contents:hover {
        transform: translateY(-5px);
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.3);

    }
    #content{
    	width: 100%;
    	height: 300px;
    }
</style>
</head>
<body>
	<div class="container">
		<h1>글 작성한 내용 입니다.</h1>
		<p>제목 : <strong><%=title %></strong></p>
		<%--마크업 형식의 html --%>
		<%--내가 작성한 내용과 동일하게 나옴 --%>
		<div id="contents"><%=content %></div>
		
		<h1>글 수정 양식 입니다.</h1>
		<form action="update.jsp" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" value="<%=title %>" />
			</div>
			<div>
				<label for="content">내용</label>
				<%--textarea는 value 속성이 없다.--%>
				<textarea name="content" id="content"><%=content %></textarea>
			</div>
			<%--this는 버튼을 뜻함 --%>
			<button type="submit" onclick="submitContents(this)">저장</button>
		</form>
	</div>
	
	<!-- SmartEditor 에서 필요한 javascript 로딩  -->
	<script src="${pageContext.request.contextPath }/SmartEditor/js/HuskyEZCreator.js"></script>
	<!-- 밑의 자바스크립트가 작동함으로써 textarea 에 있는 위치에 변화가 생김 -->
	<script>
		let oEditors = [];
		
		//추가 글꼴 목록
		//let aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
		
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "content",
			sSkinURI: "${pageContext.request.contextPath}/SmartEditor/SmartEditor2Skin.html",	
			htParams : {
				bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
				fOnBeforeUnload : function(){
					//alert("완료!");
				}
			}, //boolean
			fOnAppLoad : function(){
				//예제 코드
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator: "createSEditor2"
		});
		
		function pasteHTML() {
			let sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
			oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
		}
		
		function showHTML() {
			let sHTML = oEditors.getById["content"].getIR();
			alert(sHTML);
		}
		//이 함수는 submit 버튼의 참조값이 매개 변수에 전달된다.	
		function submitContents(elClickedObj) {
			//this의 정체는? 버튼
			//자바스크립트에서의 this는 자신의 참조값일수도 아닐수도 있음...	
						
			//SmartEditor 에 의해 만들어진(작성한글) 내용이 textarea 의 value 가 되도록 한다. 
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
			
			// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
			
			try {
				// submit 버튼의 제출 대상이 되는 form의 참조값을 얻어와서 submit() 호출해서 폼 제출
				elClickedObj.form.submit();
			} catch(e) {}
		}
		
		function setDefaultFont() {
			let sDefaultFont = '궁서';
			let nFontSize = 24;
			oEditors.getById["content"].setDefaultFont(sDefaultFont, nFontSize);
		}
	</script>
</body>
</html>