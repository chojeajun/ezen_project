
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


