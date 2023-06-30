select * from member;

select * from orders_view;
select * from banner;
select * from qna;
select * from content;
select * from admin;
select * from worker;


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
	rep char(1) DEFAULT '1',
	indate date DEFAULT SYSDATE,
	id varchar2(20) NOT NULL,
	PRIMARY KEY (qseq)
);

drop table qna;

CREATE SEQUENCE qna_qseq INCREMENT BY 1 START WITH 1;

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
