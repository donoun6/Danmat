-----------------------------------------------------------------------------------
-- 유저 테이블

DROP TABLE BOARD;

CREATE TABLE User(
	userid			VARCHAR(20)			NOT NULL PRIMARY KEY,
	passwd			VARCHAR(16)			NOT NULL,
	email			VARCHAR(20)			NOT NULL UNIQUE KEY,
	regDate			TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP
)

SELECT * FROM User;

SELECT count(*) as count FROM User;

SELECT passwd FROM User WHERE email = 'donoun6@naver.com' AND userid = 'donoun6';
  	
-----------------------------------------------------------------------------------
-- 유저 데이터

INSERT INTO danmat.`user` (userid,passwd,email,regDate) VALUES
	 ('admin','12345678','admin@admin.com','2022-08-25 17:15:43'),
	 ('skyak','kim55845972','skyak4809@naver.com','2022-08-25 17:16:39'),
	 ('test123','1q2w3e4r','test@naver.com','2022-08-25 17:16:25');


SELECT * FROM word;

SELECT * FROM word WHERE word LIKE '%수%'
-----------------------------------------------------------------------------------
-- 랭킹 테이블
CREATE TABLE Rank(
	userid		VARCHAR(20)			FOREIGN KEY REFFERNCES User(userid),
	score		INT					NOT NULL DEFAULT 0
)

-----------------------------------------------------------------------------------
-- 게시판 테이블
CREATE TABLE BOARD(
	board_idx		INT				PRIMARY KEY		AUTO_INCREMENT, -- board index num
	notice			VARCHAR(1)		NOT NULL		DEFAULT 'N', -- 공지사항 여부
	userId			VARCHAR(15)		NOT NULL, -- userId
	title			VARCHAR(30)		NOT NULL, -- 제목
	content			VARCHAR(300)	NOT NULL, -- 내용
	createDate		TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP, -- 게시글 생성 날짜
	updateDate		TIMESTAMP		NOT NULL	DEFAULT 0, -- 게시글 수정 날짜
	deleteDate		TIMESTAMP		NOT NULL	DEFAULT 0, -- 게시글  삭제 날짜
	delete_yn		CHAR(1)			NOT NULL	DEFAULT 'N', -- 삭제된 게시글 여부(Y:삭제된 글)
	readCount		BIGINT			NOT NULL	DEFAULT 0, -- 조회수
	replyCount		BIGINT			NOT NULL	DEFAULT 0 -- 댓글 수
)
select * from BOARD;
drop table board;
select * from board where board_idx=;

select * from BOARD;
SELECT * FROM WORD;
SELECT * FROM BOARD;
 
drop table board;

-----------------------------------------------------------------------------------
-- 게시판 데이터

INSERT INTO danmat.board (notice,userId,title,content,createDate,updateDate,deleteDate,delete_yn,readCount,replyCount) VALUES
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0);
INSERT INTO danmat.board (notice,userId,title,content,createDate,updateDate,deleteDate,delete_yn,readCount,replyCount) VALUES
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','Y',1,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0);
INSERT INTO danmat.board (notice,userId,title,content,createDate,updateDate,deleteDate,delete_yn,readCount,replyCount) VALUES
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',1,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',1,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0);
INSERT INTO danmat.board (notice,userId,title,content,createDate,updateDate,deleteDate,delete_yn,readCount,replyCount) VALUES
	 ('N','wkrdmsqha','테스트 데이터','테스트내용','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0);
INSERT INTO danmat.board (notice,userId,title,content,createDate,updateDate,deleteDate,delete_yn,readCount,replyCount) VALUES
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('y','admin','테스트 데이터admin','12121222','2022-08-29 09:46:36','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','admin','dfdsfa','fsdsff','2022-08-29 10:21:50','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','asfsf','sfsff','2022-08-29 10:44:52','0000-00-00 00:00:00','0000-00-00 00:00:00','N',2,0),
	 ('N','admin','sdfsafs','fsffsf','2022-08-29 10:46:44','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','admin','공지 테스트','1213132','2022-08-29 10:47:48','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('Y','admin','오오오오오오오오오 공지 db등록','ㅇㄹㄴㅁㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㄹㄴㄹ','2022-08-29 11:24:55','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('Y','admin','ㄴㄹㅇㄹㄴㅁㄹㄴ','ㄹㄴㄹㄴㄹㄴㄹ','2022-08-29 11:25:28','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0);
INSERT INTO danmat.board (notice,userId,title,content,createDate,updateDate,deleteDate,delete_yn,readCount,replyCount) VALUES
	 ('Y','admin','일반등록','ㄹㄴㅁㄹㄹ','2022-08-29 11:26:26','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('Y','admin','일반공지등록','ㅇㅇㅇ','2022-08-29 11:27:59','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('Y','admin','공지등록','ㅇㅇ','2022-08-29 12:39:05','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('Y','admin','ㅇㅇㅁㄹㅇㄹ','ㅇㄹㅇㄴㄻㄹ','2022-08-29 12:46:17','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','admin','관리자 일반등록','ㅇㅇ','2022-08-29 12:47:57','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','admin','관리자 공지 등록','ㅇㅇ','2022-08-29 12:48:05','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0),
	 ('N','lustel','ddasf','dd','2022-08-29 13:30:20','0000-00-00 00:00:00','0000-00-00 00:00:00','N',0,0);



-----------------------------------------------------------------------------------
-- 단어 테이블
CREATE TABLE word (
wid			INTEGER			PRIMARY KEY AUTO_INCREMENT,
word		VARCHAR(100)	NOT NULL,
len			INTEGER			NOT NULL,				
sameMean	INTEGER			NOT NULL,
class		VARCHAR(100)	NOT NULL,
pos			VARCHAR(100)	NOT NULL,
grade		VARCHAR(100)	NOT NULL,
category	VARCHAR(100)	DEFAULT NULL,
category2	VARCHAR(100)	DEFAULT NULL,
def			VARCHAR(2000)	NOT NULL,
ex			VARCHAR(2000)	NOT NULL	
)
select * from word

drop table word


-----------------------------------------------------------------------------------
-- 사용 단어 테이블
CREATE TABLE isuse (
	usedWord		VARCHAR(100)	NOT NULL
)
select * from isuse;
-----------------------------------------------------------------------------------
-- 출력 단어 테이블
CREATE TABLE answer (
	answerWord		VARCHAR(100)	NOT NULL
)
select * from answer;

-----------------------------------------------------------------------------------
-- 점수 저장 테이블
CREATE TABLE point (
	point		INT 	NOT NULL
)
select * from point;
insert into point (point) values(10);

Select sum(point) from point;

-------------------------------------------------------------------------------------
-- 게임리스트 테이블

CREATE TABLE CWList (      -- 게임 생성 테이블입니다. 해당 테이블에서 생성된 게임 중 원하는 게임만 gameList 에 추가되도록 합니다. 

   gid            BIGINT         NOT NULL,      -- 자동 생성된 게임 순번입니다. 선택 후 삭제시 해당 번호를 기준으로 전체가 삭제됩니다.
   gameSize      BIGINT         NOT NULL,   -- 해당 게임의 크기입니다 ex) 11*11, 13*13 ...
   wordNum         INTEGER         NOT NULL,   -- 단어의 순번입니다.
   dir            CHAR         NOT NULL,   -- 단어의 방향입니다.   dir값이 x일 경우 가로로 작성된 단어, dir값이 y일 경우 세로로 작성된 단어
   word         VARCHAR(10)      NOT NULL,   -- 단어의 명칭입니다.   ex)수수방관, 구구절절, 초시계 ...
   xLocation      INTEGER         NOT NULL,   -- 해당 단어의 시작 x좌표값입니다.
   yLocation      INTEGER         NOT NULL   -- 해당 단어의 시작 y좌표값입니다.
);

SELECT * FROM CWList;

DROP TABLE CWList;

INSERT INTO CWList (gid, gameSize, wordNum, dir, word, xLocation, yLocation) VALUES (1, 0, 0, 'x', '시작지점', 0, 0);

   -- 최초에 한개 이상 등록되어있어야 gid값 관련 메소드들이 작동할 수 있습니다.


SELECT DISTINCT gid FROM CWList ORDER BY gid ASC;


SELECT COUNT ( DISTINCT ) gid FROM CWList  ;

SELECT COUNT(wordNum) FROM CWList WHERE gid=7;

SELECT * FROM CWList WHERE gid=7;

SELECT MAX(wordNum) FROM CWList WHERE gid=7
