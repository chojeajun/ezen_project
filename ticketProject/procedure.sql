-- 멤버 회원 리스트 아이디 조회 
CREATE OR REPLACE PROCEDURE getMember(
    p_id IN member.id%type,
    p_cur OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_cur FOR SELECT * FROM member WHERE id=p_id;
END;

select * from review_board;
select * from success_board;
commit;
-- 성공후기 테이블에 IMGFILENAME 이라는 칼럼 추가 
alter table success_board add imgfilename varchar2(100);

commit;

alter table member modify email varchar2(100) null;
commit;
select * from qna;
select * from qna_board;

select * from success_reply;
select * from member;

select * from banner;
select * from success_board;

select * from member;
alter table member add provider varchar2(100);

select * from banner;
-- 메인페이지 배너 리스트 
create or replace procedure getBestNewSuccessBannerList(
    p_rc1 out sys_refcursor,
    p_rc2 out sys_refcursor,
    p_rc3 out sys_refcursor,
    p_rc4 out sys_refcursor
)
is
begin
    open p_rc1 for
    select * from banner where order_seq <= 5;
    open p_rc2 for
    select * from content where bestyn = 'Y';
    open p_rc3 for
    select * from(select * from content order by cseq desc) where rownum <=4;
    open p_rc4 for
    select * from(select * from success_board order by sucseq desc) where rownum <=4;
end;

commit;


select * from (  select rownum rn, s.* from ( (select * from success_board order by num desc) s )  ) where rn >= 1 and rn <= 10;



 select * from content;
CREATE OR REPLACE PROCEDURE getAdmin(
    p_id IN   worker.id%TYPE,
    p_rc   OUT     SYS_REFCURSOR )
IS
BEGIN
    OPEN p_rc FOR
        select * from worker where id=p_id;
        
END;

DROP PROCEDURE getAdmin;

DELIMITER $$

CREATE PROCEDURE IF NOT EXISTS

END$$

DELIMITER ;
--카카오로그인
CREATE OR REPLACE PROCEDURE joinKakao(
    p_id IN member.id%TYPE,
    p_name IN member.name%TYPE,
    p_email IN member.email%TYPE,
    p_provider IN member.provider%TYPE
)
IS
BEGIN
    INSERT INTO member( mseq, id, name, email, provider)
    VALUES( member_mseq.nextVal, p_id , p_name , p_email , p_provider);
    COMMIT;
END;

--CREATE OR REPLACE PROCEDURE insertReply(
--    p_rseq IN review_reply.rseq%TYPE,
--    p_mseq IN review_reply.mseq%TYPE,
--    p_replycontent IN review_reply.replycontent%TYPE
--)
--IS
--BEGIN
--    INSERT INTO review_reply(repseq, rseq, mseq, replycontent)
--    VALUES(REVIEW_REPLY_REPSEQ.nextVal, p_rseq, p_mseq, p_replycontent);
--    COMMIT;
--END;


-- 성공후기 댓글추가
CREATE OR REPLACE PROCEDURE insertSuccessReply(
    p_sucseq IN success_board.sucseq%TYPE,
    p_mseq in success_reply.mseq%type,
    p_successcontent IN success_reply.successcontent%TYPE
)
IS
BEGIN
    INSERT INTO success_reply(srseq, mseq, sucseq,  successcontent)
    VALUES(SUCCESS_REPLY_SRSEQ.nextVal,  p_mseq, p_sucseq, p_successcontent );
    COMMIT;
END;

select * from member;
select * from success_board;
select * from success_reply;

commit;
select * from success_reply;
select * from review_reply;
select  *from content;


commit;
select * from member;
-- 멤버 회원가입
CREATE OR REPLACE PROCEDURE insertMember(
    p_id member.id%type,
    p_pwd member.pwd%type,
    p_name member.name%type,
    p_nickname member.nickname%type,
    p_gender member.gender%type,
    p_email member.email%type,
    p_phone member.phone%type,
    p_birth member.birth%type,
    p_zip_num member.zip_num%type,
    p_address1 member.address1%type,
    p_address2 member.address2%type,
    p_address3 member.address3%type
)
IS
BEGIN
    INSERT INTO member(mseq, id, pwd, name, nickname, gender, email, phone, birth, zip_num, address1 ,address2 ,address3) 
    VALUES( member_mseq.nextVal, p_id, p_pwd, p_name, p_nickname, p_gender, p_email, p_phone, p_birth, p_zip_num, p_address1, p_address2, p_address3);
    COMMIT;
END;
commit;
update member set pwd = null where pwd = 'pwd';
select * from member;
commit;
-- 멤버 정보수정
CREATE OR REPLACE PROCEDURE updateMember(
    p_id member.id%type,
    p_pwd member.pwd%type,
    p_name member.name%type,
    p_nickname member.nickname%type,
    p_email member.email%type,
    p_phone member.phone%type,
    p_birth member.birth%type,
    p_zip_num member.zip_num%type,
    p_address1 member.address1%type,
    p_address2 member.address2%type,
    p_address3 member.address3%type
)
IS
BEGIN
    UPDATE member SET  pwd=p_pwd, name=p_name, nickname=p_nickname, email=p_email, phone=p_phone, 
     birth=p_birth, zip_num=p_zip_num, address1=p_address1, address2=p_address2, address3=p_address3 
    WHERE id=p_id;
    COMMIT;
END;

commit;
select * from member;
delete from member where provider = 'kakao'; 

SELECT COUNT(*) as cnt FROM content WHERE title LIKE '%'||'하리보'|| '%';
select * from member;
select * from member;

commit;

select count(*) from member where name like '%'||'롱'||'%';

-- 테이블 칼럼 내용 변경
update member set name = '홍낄동' where mseq = 29;
commit;
select * from member;
alter table member modify id varchar2(30);
alter table member modify pwd varchar2(30) null;

alter table member modify name varchar2(30) null;
alter table member modify nickname varchar2(30) null;
alter table member modify phone varchar2(30) null;
alter table member modify birth date null;
alter table member modify zip_num varchar2(30) null;
alter table member modify address1 varchar2(100) null; -- null 과 not null 을 변경하면서 칼럼의 바이트 를 적게 설정할 수 없다
alter table member modify address2 varchar2(30) null;
alter table member add address3 varchar2(50) null;



commit;

select * from order_view;

select * from all_sequences where sequence = 'member_mseq';

select * from  REVIEW_REPLY_MEMBER;
-- 성공후기 리스트 번호로 리스트 전체 조회 , 성공후기 댓글리스트 조회
create or replace PROCEDURE getSuccessListBySucseq(
    p_sucseq IN success_reply.sucseq%type,
    p_cur1 OUT SYS_REFCURSOR,
    p_cur2 OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_cur1 FOR
        SELECT * FROM success_board WHERE sucseq=p_sucseq;
    OPEN p_cur2 FOR
        SELECT * FROM success_REPLY WHERE sucseq=p_sucseq ORDER BY srseq DESC;
END;
commit;


SELECT *
  FROM all_sequences
 WHERE sequence_name = 'member_mseq';


select * from success_board;
-- 성공후기 작성 프로시져 ㅇㅋㅅㄱ
create or replace PROCEDURE insertSuccessBoard(
    p_mseq IN success_BOARD.mseq%type,
    p_id IN success_BOARD.id%type,
    p_pwd IN success_BOARD.pwd%type,
    p_title IN success_BOARD.title%type,
    p_content IN success_BOARD.content%type,
    p_imgfilename IN success_BOARD.imgfilename%type
)
IS
BEGIN
    INSERT INTO success_board( sucseq, mseq, id, pwd, title, content, imgfilename )
    VALUES( success_board_sucseq.nextVal, p_mseq, p_id, p_pwd, p_title, p_content, p_imgfilename );
    COMMIT;
END;





-- 성공후기 수정
create or replace PROCEDURE updateSuccess(
    p_sucseq IN SUCCESS_BOARD.sucseq%type,
    p_mseq IN SUCCESS_BOARD.mseq%type,
    p_id IN SUCCESS_BOARD.id%type,
    p_pwd IN SUCCESS_BOARD.pwd%type,
    p_title IN SUCCESS_BOARD.title%type,
    p_content IN SUCCESS_BOARD.content%type,
    p_imgfilename IN SUCCESS_BOARD.imgfilename%type
)
IS
BEGIN
    UPDATE SUCCESS_BOARD SET pwd=p_pwd, id=p_id, mseq=p_mseq, title=p_title,
    content=p_content, imgfilename=p_imgfilename WHERE sucseq=p_sucseq;
    COMMIT;
END;


-- 성공후기 삭제
create or replace PROCEDURE deleteSuccess(
    p_sucseq IN SUCCESS_BOARD.sucseq%TYPE
)
IS
BEGIN
    DELETE FROM SUCCESS_BOARD WHERE sucseq=p_sucseq;
    DELETE FROM success_reply WHERE srseq=p_sucseq;
    COMMIT;
END;


commit;
