
CREATE OR REPLACE PROCEDURE getMember(
    p_id IN member.id%type,
    p_curvar OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_curvar For SELECT * FROM member WHERE id=p_id;
END;


commit;


select * from member;
alter table member add provider varchar2(100);

