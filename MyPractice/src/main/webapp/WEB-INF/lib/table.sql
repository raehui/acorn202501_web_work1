-- 나의 일기 글 목록을 저장할 테이블
CREATE TABLE diarys (
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB, --용량이 큰 VARCHAR2
	viewCount NUMBER DEFAULT 0,
	createdAt DATE DEFAULT SYSDATE,
	updatedAt DATE DEFAULT SYSDATE
);

-- 나의 일기 번호를 얻어낼 시퀀스
CREATE SEQUENCE diarys_seq;