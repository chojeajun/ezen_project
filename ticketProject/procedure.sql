
CREATE OR REPLACE PROCEDURE getMember(
    p_id IN member.id%type,
    p_cur OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_cur FOR SELECT * FROM member WHERE id=p_id;
END;


commit;


select * from member;
alter table member add provider varchar2(100);




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

commit;


select * from member;

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




select * from member;

commit;
--#{id}, #{pwd},
--#{name}, #{nickname},
--#{gender}, #{email},
--#{phone}, #{birth},
--#{zip_num}, #{address1} ,
--#{address2}, #{address3} , 
--#{grade} , #{success} , #{useyn}

select count(*) from member where name like '%'||'롱'||'%';


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


select * from all_sequences where sequence = 'member_mseq';

SELECT *
  FROM all_sequences
 WHERE sequence_name = 'member_mseq';
 
 --어드민 페이지 배너리스트 관리
 CREATE OR REPLACE PROCEDURE getBannerList(
    p_rc   OUT     SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM banner ORDER BY order_seq;
        
END;
 
 select * from order_view
 select * from member

