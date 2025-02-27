CREATE TABLE files(
	num NUMBER PRIMARY KEY,
	uploader VARCHAR2(100) NOT NULL, 
	orgFileName VARCHAR2(100) NOT NULL, <!-- 다운로드 받을 때 원본 파일명으로 다운받아야 하기에 -->
	saveFileName VARCHAR2(100) NOT NULL, <!-- 업로드한 바뀐 파일명 -->
	fileSize NUMBER NOT NULL, <!-- 다운로드시 파일의 크기가 필요 -->
	uploadedAt DATE  
)

CREATE SEQUENCE files_seq;