
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

