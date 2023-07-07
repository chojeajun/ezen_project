select * from member;

select * from orders_view;
select * from banner;
select * from qna;
select * from content;
select * from admin;
select * from worker;
select * from order_view;
ALTER TABLE content MODIFY age varchar2(50);

SELECT * FROM USER_CONSTRAINTS;
DROP TABLE content CASCADE CONSTRAINTS;


CREATE TABLE content
(
	cseq number(10,0) NOT NULL,
	title varchar2(100) NOT NULL,
	locationNum number(5),
	artist varchar2(100) NOT NULL,
	image varchar2(1000) DEFAULT 'images/content/blankIMG.jpg',
	content varchar2(3000) NOT NULL,
	category number(2,0) NOT NULL,
	age varchar2(50) DEFAULT '전체관람가',
	bestyn char(1) DEFAULT 'N',
	PRIMARY KEY (cseq)
);

create or replace procedure getMember(
    p_id 
)

is
begin

end;

CREATE TABLE worker
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	phone varchar2(20) NOT NULL,
	PRIMARY KEY (id)
);

insert into worker values('admin', 'admin', '관리자', '010-7777-7777');
insert into worker values('scott', 'tiger', '홍길동', '010-6400-6068');


CREATE TABLE qna
(
	qseq number(5) NOT NULL,
	subject varchar2(300) NOT NULL,
	content varchar2(1000) NOT NULL,
	reply varchar2(1000),
	passcheck varchar2(1000),
	pass varchar2(1000),
	rep char(1) DEFAULT '1',
	indate date DEFAULT SYSDATE,
	id varchar2(20) NOT NULL,
	PRIMARY KEY (qseq)
);
select * from content;
drop table qna;
drop table content;
drop SEQUENCE content_cseq;
CREATE SEQUENCE qna_qseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE content_cseq INCREMENT BY 1 START WITH 1;

--관리자 qna 페이지
insert into qna(qseq, subject, content, id)
values (qna_qseq.nextval, '종료된 티켓팅', '종료된 티켓팅도 취켓팅으로 가능한가요?','dsan');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval, '문의합니다', '수고비는 자리에 따라 조정되나요?', 'bts2');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval, '문의드립니다~', '사이트에 없는 티켓팅도 가능한가요?', 'gangji');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval, '수고비 문의', '취소하면 전액 환불 해주시나요?', 'jrong');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval, '환불문의', '아직 환불이 안 들어왔는데 언제쯤 들어올까요?', 'kinder');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval, '문의합니다!', '원하는 열과 구역을 정확히 지정할 수 있을까요?', 'babo');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval, '취소 문의', '취소하고 싶어요 ㅠㅠ 취소 가능할까요?', 'gildong');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval, '문의드려용!!', '꼭 성공 부탁드리겠습니당!!?', 'work');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval, '아이디 변경 문의', '아이디와 비번을 변경할 수 있을까요?', 'sleep');

insert into qna(qseq, subject, content, id)
values(qna_qseq.nextval,  '문희드립니당', '수고비도 조정이 가능한가요?', 'java');


insert into content (CSEQ, TITLE, locationNum,ARTIST, content,CATEGORY, AGE)
values(content_cseq.nextVal, '2023 김기태 전국투어 콘서트 '||CHR(091)||'위로'||CHR(093)||'', 8, '김기태',
'김기태만의 짙고 울림있는 감성으로 여러분의 마음을 위로하며 가슴 깊은 곳 위로의 메세지를 전할 2023 김기태 전국투어 콘서트 '||CHR(091)||'위로'||CHR(093)||'', 1,'만 7세 이상');

insert into content (CSEQ, TITLE, locationNum,ARTIST, content,CATEGORY, AGE)
values(content_cseq.nextVal, '2023 이민우 M 20th Anniversary Live "STORY"', 5, '이민우', 
'꿈으로 가득한 소년에서 최고의 무대를 보여주는 뮤지션으로 성장한 20년
뜨거운 열정과 에너지, 응원과 위로, 사랑, 이별까지....수많은 이야기를 노래에 담아온 M
끝없이 이어지는 플레이리스트처럼 20년 동안 이어온 M과 팬들의 시간을 축하하는 자리에 여러분을 초대합니다',
1, '8세 이상');

insert into content (CSEQ, TITLE, locationNum,ARTIST, content,CATEGORY, AGE)
values(content_cseq.nextVal, '나윤선 Waking World', 1, '나윤선','나윤선 음악에 내재되어 있는 깊은 감동과 치유의 시간을 만나다',1, '8세 이상');


insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY, AGE)
values(content_cseq.nextVal, '크랙샷 단독 콘서트 '||CHR(034)||'SPEAK OUT'||CHR(034)||'', 7, '크랙샷',
'INFP 발매 기념 콘서트 개최! 크랙샷의 SPEAK OUT 많은 기대 부탁드립니다!',1, '8세 이상');
	
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY, AGE)
values(content_cseq.nextVal, '현대카드 슈퍼콘서트 27 브루노 마스(Bruno Mars)', 4, '브루노 마스(Bruno Mars)',
'21세기를 대표하는 싱어송라이터이자 글로벌 팝스타 '||CHR(034)||'브루노 마스(Bruno Mars)'||CHR(034)||'가 오는 6월 한국을 찾는다. 이번 '||CHR(034)||'현대카드 슈퍼콘서트'||CHR(034)||' 내한 공연으로서는 역대 최대 규모다.',1, '만 7세 이상');

insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY, AGE)
values(content_cseq.nextVal, '2023 폴 아웃 보이(Fall Out Boy) 내한공연',4,'폴 아웃 보이(Fall Out Boy)',
'세계적인 락밴드 폴 아웃 보이(Fall Out Boy)의 최초 내한 콘서트!',1, '8세 이상');

insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY, AGE)
values(content_cseq.nextVal, '2023 테일러 스위프트(Taylor Swift) 내한공연',12,'테일러 스위프트(Taylor Swift)',
'테일러 스위프트, 13년만의 내한공연! 
21세기의 음악 산업 그 자체, 싱어송라이터 테일러 스위프트가 오는 8월 서울을 찾는다',1, '8세 이상');


--뮤지컬 데이터 추가


insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY, AGE)
values(CONTENT_CSEQ.nextVal, '뮤지컬 '||CHR(060)||'백작'||CHR(062)||'', 14, '이승현 외','무패의 군신으로 불리는 백작. 
인간들을 상대로 밤에만 전투를 하고, 새벽이 되면 적장이 가장 사랑하는 사람을 인질로 잡아 포에나리성으로 퇴각한다. 
백작에게 인질로 잡힌 적장의 아들 V. 태양을 가린 커튼 아래 빛의 세계와 밤의 세계가 교차한다. 기록되지 않은 포에나리 성주의 전설',2, '만 12세 이상');

insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY, AGE)
values(CONTENT_CSEQ.nextVal, '뮤지컬 '||CHR(060)||'더 테일 에이프릴 풀스'||CHR(062)||'', 15, '최석진 외',
'2022년 초연 흥행 돌풍, 예매처 관람 평점 9.7로 매진 신화 기록!
아름다운 무대, 감각적 미장센으로 관객이 가장 기다려온 뮤지컬
완벽한 호흡을 선보인 배우들과 함께 다시 돌아왔다!',2, '만 14세 이상');

insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY, AGE)
values(CONTENT_CSEQ.nextVal, '뮤지컬 '||CHR(060)||'빠리빵집'||CHR(062)||'', 9, '고훈정 외',
'그리움이 만든 기적, 지금 여기 우리',2, '만 7세 이상');


--스포츠
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY)
values(CONTENT_CSEQ.nextVal, '두산 베어스', 10, '두산 베어스','두산 베어스 잠실야구장 홈경기입니다.',3);
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY)
values(CONTENT_CSEQ.nextVal, '키움 히어로즈', 12, '키움 히어로즈','키움 히어로즈 고척스카이돔 홈경기입니다.',3);
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY)
values(CONTENT_CSEQ.nextVal, '삼성 라이온즈', 10, '삼성 라이온즈','삼성 라이온즈 삼성 라이온즈파크 홈경기입니다.',3);
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY)
values(CONTENT_CSEQ.nextVal, '수원삼성블루윙즈', 11, '수원삼성블루윙즈','수원삼성블루윙즈 수원월드컵경기장 홈경기입니다.',3);

--페스티벌
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY)
values(content_cseq.nextVal, '제 15회  서울재즈페스티벌 2023', 3, 'Sergio Mendes 외',
'15th Seoul Jazz Festival 2023', 4);
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY, AGE)
values(content_cseq.nextVal, '톤앤뮤직 페스티벌 2023', 16, '다이나믹 듀오 외',
'Tone&Music Festival 2023', 4,'만 12세 이상');

--전시/행사
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY)
values(content_cseq.nextVal, '더현대서울 프랑스국립현대미술관전 : 라울 뒤피', 17, '라울 뒤피(Raoul Dufy)',
'화려한 빛과 색으로 기쁨과 환희를 노래하는 '||CHR(034)||'뒤피'||CHR(034)||'의 원화 130여점 공개', 5);
insert into content (CSEQ, TITLE, locationNum,ARTIST,content,CATEGORY)
values(content_cseq.nextVal, '하리보 골드베렌 100주년 생일 기념전', 18, '(주)피플리',
'100년 역사의 글로벌 1위 젤리 하리보의 세계 최초 미디어 전시', 5);

