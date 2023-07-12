------------------------------------------------------ ERDiagram ----------------------------------------------
/* Drop Triggers */

DROP TRIGGER TRI_adminQna_reply_qrseq;
DROP TRIGGER TRI_cart_cartseq;
DROP TRIGGER TRI_content_cseq;
DROP TRIGGER TRI_locationNum_locationNum;
DROP TRIGGER TRI_member_mseq;
DROP TRIGGER TRI_orders_oseq;
DROP TRIGGER TRI_order_detail_odseq;
DROP TRIGGER TRI_qna_board_qseq;
DROP TRIGGER TRI_qna_board_sucseq;
DROP TRIGGER TRI_registerTime_rtseq;
DROP TRIGGER TRI_review_board_rseq;
DROP TRIGGER TRI_review_reply_repseq;
DROP TRIGGER TRI_seat_seatseq;
DROP TRIGGER TRI_success_board_srseq;
DROP TRIGGER TRI_success_board_sucseq;



/* Drop Tables */

DROP TABLE address CASCADE CONSTRAINTS;
DROP TABLE adminQna_reply CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE order_detail CASCADE CONSTRAINTS;
DROP TABLE contentTime CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE content CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;
DROP TABLE seat CASCADE CONSTRAINTS;
DROP TABLE locationNum CASCADE CONSTRAINTS;
DROP TABLE qna_board CASCADE CONSTRAINTS;
DROP TABLE registerTime CASCADE CONSTRAINTS;
DROP TABLE review_reply CASCADE CONSTRAINTS;
DROP TABLE review_board CASCADE CONSTRAINTS;
DROP TABLE success_board CASCADE CONSTRAINTS;
DROP TABLE success_board CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_adminQna_reply_qrseq;
DROP SEQUENCE SEQ_cart_cartseq;
DROP SEQUENCE SEQ_content_cseq;
DROP SEQUENCE SEQ_locationNum_locationNum;
DROP SEQUENCE SEQ_member_mseq;
DROP SEQUENCE SEQ_orders_oseq;
DROP SEQUENCE SEQ_order_detail_odseq;
DROP SEQUENCE SEQ_qna_board_qseq;
DROP SEQUENCE SEQ_qna_board_sucseq;
DROP SEQUENCE SEQ_registerTime_rtseq;
DROP SEQUENCE SEQ_review_board_rseq;
DROP SEQUENCE SEQ_review_reply_repseq;
DROP SEQUENCE SEQ_seat_seatseq;
DROP SEQUENCE SEQ_success_board_srseq;
DROP SEQUENCE SEQ_success_board_sucseq;




/* Create Sequences */

CREATE SEQUENCE SEQ_adminQna_reply_qrseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_cart_cartseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_content_cseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_locationNum_locationNum INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_member_mseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_orders_oseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_order_detail_odseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_qna_board_qseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_qna_board_sucseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_registerTime_rtseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_review_board_rseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_review_reply_repseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_seat_seatseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_success_board_srseq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_success_board_sucseq INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE address
(
	-- 우편번호
	zip_num varchar2(10) NOT NULL,
	sido varchar2(100) NOT NULL,
	gugun varchar2(100) NOT NULL,
	dong varchar2(100),
	bunji varchar2(100),
	zip_code varchar2(100)
);


CREATE TABLE admin
(
	id varchar2(50) NOT NULL,
	pwd varchar2(30) NOT NULL,
	name varchar2(50) NOT NULL,
	phone varchar2(20) NOT NULL,
	email varchar2(50),
	adminyn char DEFAULT '''Y''',
	PRIMARY KEY (id)
);


CREATE TABLE adminQna_reply
(
	qrseq number(5,0) NOT NULL,
	qseq number(5,0) NOT NULL,
	id varchar2(50) NOT NULL,
	qnaContent varchar2(3000) NOT NULL,
	writeDate date DEFAULT sysdate,
	PRIMARY KEY (qrseq)
);

CREATE TABLE cart
(
	cartseq number(5) NOT NULL,
	mseq number(5,0) NOT NULL,
	cseq number(10,0) NOT NULL,
	contentDate date NOT NULL,
	contentTime varchar2(10) NOT NULL,
	locationNum number(5) NOT NULL,
	area varchar2(10) NOT NULL,
	quantity number(5,0),
	indate date DEFAULT sysdate,
	buyyn char DEFAULT '''N''',
	PRIMARY KEY (cartseq, mseq, cseq, contentDate, contentTime, locationNum, area)
);


CREATE TABLE content
(
	cseq number(10,0) NOT NULL,
	title varchar2(100) NOT NULL,
	locationNum number(5) NOT NULL,
	artist varchar2(100) NOT NULL,
	image varchar2(1000) DEFAULT '''images/content/blankIMG.jpg''',
	content varchar2(3000) NOT NULL,
	category number(2,0) NOT NULL,
	age varchar2(20) DEFAULT '''전체관람가''',
	tDateTime varchar2(100) DEFAULT '0',
	bestyn char(1) DEFAULT '''N''',
	PRIMARY KEY (cseq)
);


CREATE TABLE contentTime
(
	cseq number(10,0) NOT NULL,
	contentDate date NOT NULL,
	contentTime varchar2(10) NOT NULL,
	PRIMARY KEY (cseq, contentDate, contentTime)
);


CREATE TABLE grade
(
	gseq number(1) NOT NULL,
	gname varchar2(10) DEFAULT '''일반''',
	gprice number(10) DEFAULT 0,
	PRIMARY KEY (gseq)
);


CREATE TABLE locationNum
(
	locationNum number(5) NOT NULL,
	locationName varchar2(50) NOT NULL UNIQUE,
	areaImage varchar2(1000) DEFAULT 'images/content/blankIMG.jpg',
	PRIMARY KEY (locationNum)
);


CREATE TABLE member
(
	mseq number(5,0) NOT NULL,
	id varchar2(50) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(50) NOT NULL,
	nickname varchar2(50) NOT NULL,
	gender number(1) DEFAULT 0,
	email varchar2(50) NOT NULL,
	phone varchar2(20) NOT NULL,
	birth date NOT NULL,
	zip_num varchar2(10) NOT NULL,
	address1 varchar2(100) NOT NULL,
	address2 varchar2(100) NOT NULL,
	grade number(1) DEFAULT 0,
	success number(5,0) DEFAULT 0,
	indate date DEFAULT sysdate,
	useyn char(1) DEFAULT '''N''',
	PRIMARY KEY (mseq)
);


CREATE TABLE orders
(
	oseq number(5,0) NOT NULL,
	indate date DEFAULT sysdate,
	mseq number(5,0) NOT NULL,
	cseq number(10,0) NOT NULL,
	PRIMARY KEY (oseq)
);


CREATE TABLE order_detail
(
	odseq number(5,0) NOT NULL,
	oseq number(5,0) NOT NULL,
	mseq number(5,0) NOT NULL,
	cseq number(10,0) NOT NULL,
	locationNum number(5) NOT NULL,
	contentDate date NOT NULL,
	contentTime varchar2(10) NOT NULL,
	area varchar2(10) NOT NULL,
	-- 매칭된 대리인 회원번호
	mseq2 number(5) NOT NULL,
	quantity number(5,0) DEFAULT 1,
	-- 티켓팅 결과-티켓팅 했으면(성공했으면) Y
	-- 티켓팅 날짜 전이면 N
	-- 티켓팅을 했지만 대리인이 성공하지 못했다면 F(이건 사용자에게 환불해줘야하는 결과)
	result char DEFAULT '''N''',
	indate date DEFAULT sysdate NOT NULL,
	PRIMARY KEY (odseq)
);


CREATE TABLE qna_board
(
	qseq number(5,0) NOT NULL,
	mseq number(5,0) NOT NULL,
	id varchar2(50) NOT NULL,
	pwd varchar2(30) NOT NULL,
	title varchar2(200) NOT NULL,
	indate date DEFAULT sysdate,
	content varchar2(3000) NOT NULL,
	reply varchar2(1000),
	repyn char(1) DEFAULT '''N''',
	image varchar2(0),
	readCount number(5) DEFAULT 0,
	PRIMARY KEY (qseq)
);


CREATE TABLE registerTime
(
	rtseq number(5,0) NOT NULL,
	mseq number(5,0) NOT NULL,
	registerDate varchar2(100) DEFAULT '00000000' NOT NULL,
	startTime varchar2(100) NOT NULL,
	endTime varchar2(100) NOT NULL,
	PRIMARY KEY (rtseq, mseq, registerDate, startTime, endTime)
);


CREATE TABLE review_board
(
	rseq number(5) NOT NULL,
	mseq number(5,0) NOT NULL,
	id varchar2(50) NOT NULL,
	pwd varchar2(30) NOT NULL,
	title varchar2(200) NOT NULL,
	indate date DEFAULT sysdate,
	content varchar2(3000) NOT NULL,
	reply varchar2(1000),
	repyn char(1) DEFAULT '''N''',
	image varchar2(0),
	PRIMARY KEY (rseq)
);


CREATE TABLE review_reply
(
	repseq number(5,0) NOT NULL,
	mseq number(5,0) NOT NULL,
	rseq number(5) NOT NULL,
	replyContent varchar2(3000) NOT NULL,
	writeDate date DEFAULT sysdate,
	PRIMARY KEY (repseq)
);


CREATE TABLE seat
(
	locationNum number(5) NOT NULL,
	area varchar2(10) NOT NULL,
	price number(10,0),
	PRIMARY KEY (locationNum, area)
);


CREATE TABLE success_board
(
	sucseq number(5) NOT NULL,
	mseq number(5,0) NOT NULL,
	id varchar2(50) NOT NULL,
	pwd varchar2(30) NOT NULL,
	title varchar2(200) NOT NULL,
	indate date DEFAULT sysdate,
	content varchar2(3000) NOT NULL,
	reply varchar2(1000),
	repyn char(1) DEFAULT '''N''',
	image varchar2(0),
	PRIMARY KEY (sucseq)
);



/* Create Foreign Keys */

ALTER TABLE adminQna_reply
	ADD FOREIGN KEY (id)
	REFERENCES admin (id)
;


ALTER TABLE contentTime
	ADD FOREIGN KEY (cseq)
	REFERENCES content (cseq)
;


ALTER TABLE orders
	ADD FOREIGN KEY (cseq)
	REFERENCES content (cseq)
;


ALTER TABLE cart
	ADD FOREIGN KEY (cseq, contentDate, contentTime)
	REFERENCES contentTime (cseq, contentDate, contentTime)
;


ALTER TABLE order_detail
	ADD FOREIGN KEY (cseq, contentDate, contentTime)
	REFERENCES contentTime (cseq, contentDate, contentTime)
;


ALTER TABLE content
	ADD FOREIGN KEY (locationNum)
	REFERENCES locationNum (locationNum)
;


ALTER TABLE seat
	ADD FOREIGN KEY (locationNum)
	REFERENCES locationNum (locationNum)
;


ALTER TABLE cart
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE orders
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE order_detail
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE qna_board
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE registerTime
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE review_board
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE review_reply
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE success_board
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE success_board
	ADD FOREIGN KEY (mseq)
	REFERENCES member (mseq)
;


ALTER TABLE order_detail
	ADD FOREIGN KEY (oseq)
	REFERENCES orders (oseq)
;


ALTER TABLE adminQna_reply
	ADD FOREIGN KEY (qseq)
	REFERENCES qna_board (qseq)
;


ALTER TABLE review_reply
	ADD FOREIGN KEY (rseq)
	REFERENCES review_board (rseq)
;


ALTER TABLE cart
	ADD FOREIGN KEY (locationNum, area)
	REFERENCES seat (locationNum, area)
;


ALTER TABLE order_detail
	ADD FOREIGN KEY (locationNum, area)
	REFERENCES seat (locationNum, area)
;


ALTER TABLE success_board
	ADD FOREIGN KEY (sucseq)
	REFERENCES success_board (sucseq)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_adminQna_reply_qrseq BEFORE INSERT ON adminQna_reply
FOR EACH ROW
BEGIN
	SELECT SEQ_adminQna_reply_qrseq.nextval
	INTO :new.qrseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_cart_cartseq BEFORE INSERT ON cart
FOR EACH ROW
BEGIN
	SELECT SEQ_cart_cartseq.nextval
	INTO :new.cartseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_content_cseq BEFORE INSERT ON content
FOR EACH ROW
BEGIN
	SELECT SEQ_content_cseq.nextval
	INTO :new.cseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_locationNum_locationNum BEFORE INSERT ON locationNum
FOR EACH ROW
BEGIN
	SELECT SEQ_locationNum_locationNum.nextval
	INTO :new.locationNum
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_member_mseq BEFORE INSERT ON member
FOR EACH ROW
BEGIN
	SELECT SEQ_member_mseq.nextval
	INTO :new.mseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_orders_oseq BEFORE INSERT ON orders
FOR EACH ROW
BEGIN
	SELECT SEQ_orders_oseq.nextval
	INTO :new.oseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_order_detail_odseq BEFORE INSERT ON order_detail
FOR EACH ROW
BEGIN
	SELECT SEQ_order_detail_odseq.nextval
	INTO :new.odseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_qna_board_qseq BEFORE INSERT ON qna_board
FOR EACH ROW
BEGIN
	SELECT SEQ_qna_board_qseq.nextval
	INTO :new.qseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_qna_board_sucseq BEFORE INSERT ON qna_board
FOR EACH ROW
BEGIN
	SELECT SEQ_qna_board_sucseq.nextval
	INTO :new.sucseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_registerTime_rtseq BEFORE INSERT ON registerTime
FOR EACH ROW
BEGIN
	SELECT SEQ_registerTime_rtseq.nextval
	INTO :new.rtseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_review_board_rseq BEFORE INSERT ON review_board
FOR EACH ROW
BEGIN
	SELECT SEQ_review_board_rseq.nextval
	INTO :new.rseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_review_reply_repseq BEFORE INSERT ON review_reply
FOR EACH ROW
BEGIN
	SELECT SEQ_review_reply_repseq.nextval
	INTO :new.repseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_seat_seatseq BEFORE INSERT ON seat
FOR EACH ROW
BEGIN
	SELECT SEQ_seat_seatseq.nextval
	INTO :new.seatseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_success_board_srseq BEFORE INSERT ON success_board
FOR EACH ROW
BEGIN
	SELECT SEQ_success_board_srseq.nextval
	INTO :new.srseq
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_success_board_sucseq BEFORE INSERT ON success_board
FOR EACH ROW
BEGIN
	SELECT SEQ_success_board_sucseq.nextval
	INTO :new.sucseq
	FROM dual;
END;

/




/* Comments */

COMMENT ON COLUMN address.zip_num IS '우편번호';
COMMENT ON COLUMN order_detail.mseq2 IS '매칭된 대리인 회원번호';
COMMENT ON COLUMN order_detail.result IS '티켓팅 결과-티켓팅 했으면(성공했으면) Y
티켓팅 날짜 전이면 N
티켓팅을 했지만 대리인이 성공하지 못했다면 F(이건 사용자에게 환불해줘야하는 결과)';

----------------------------------------------------------------------------------------------------------------------------------------------
-- 추가 테이블 ---------------------------------------------------------------------------------------------------------------------------------
  CREATE TABLE "SCOTT"."BANNER" 
   (	"BSEQ" NUMBER(5,0), 
	"SUBJECT" VARCHAR2(100 BYTE), 
	"ORDER_SEQ" NUMBER(3,0), 
	"USEYN" CHAR(1 BYTE) DEFAULT 'Y', 
	"INDATE" DATE DEFAULT sysdate, 
	"IMAGE" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
  
    CREATE TABLE "SCOTT"."WORKER" 
   (	"ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"PWD" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"PHONE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
----------------------------------------------------------------------------------------------------------------------------------------------
-- 뷰 -----------------------------------------------------------------------------------------------------------------------------------------
-- cart_total_view
  CREATE OR REPLACE FORCE VIEW "SCOTT"."CART_TOTAL_VIEW" ("MSEQ", "CSEQ", "TITLE", "LOCATIONNUM", "LOCATIONNAME", "AREA", "CNICKNAME", "GPRICE", "PRICE", "MSEQ2", "QUANTITY", "INDATE", "BUYYN") AS 
  SELECT  ca.mseq,ca.cseq, ca.title, ca.locationNum, ca.locationname, ca.area,  m.cnickname, m.gprice,
ca.price, ca.mseq2,ca.quantity, ca.indate, ca.buyyn 
FROM cart_view ca , member_grade_view m
where ca.mseq2=m.cmseq(+);

-- cart_view
  CREATE OR REPLACE FORCE VIEW "SCOTT"."CART_VIEW" ("MSEQ", "CSEQ", "TITLE", "LOCATIONNUM", "LOCATIONNAME", "AREA", "PRICE", "MSEQ2", "QUANTITY", "INDATE", "BUYYN") AS 
  SELECT  ca.mseq,ca.cseq, cv.title, ca.locationNum, cv.locationname, ca.area, 
cv.price, ca.mseq2,ca.quantity, ca.indate, ca.buyyn 
FROM cart ca
INNER JOIN content_loc_seat_view cv
ON ca.cseq=cv.cseq;

-- commissioner_view
  CREATE OR REPLACE FORCE VIEW "SCOTT"."COMMISSIONER_VIEW" ("MSEQ", "CID", "CNICKNAME", "GRADE", "GNAME", "SUCCESS", "REGISTERDATE", "STARTTIME", "ENDTIME", "COM_PRICE") AS 
  select r.mseq, m.cid, m.cnickname, m.grade,m.gname, m.success, r.registerDate ,r.startTime, r.endTime, m.gprice as com_price
from registerTime r
inner join member_grade_view m 
on r.mseq=m.cmseq;

-- content_loc_seat_view
  CREATE OR REPLACE FORCE VIEW "SCOTT"."CONTENT_LOC_SEAT_VIEW" ("CSEQ", "TITLE", "LOCATIONNUM", "LOCATIONNAME", "ARTIST", "AREA", "PRICE", "AREAIMAGE") AS 
  SELECT c.cseq, c.title, c.locationNum, lo.locationName, c.artist, s.area, s.price, lo.areaImage
FROM CONTENT c 
INNER JOIN LOCATIONNUM lo 
on c.locationNum=lo.locationNum
left JOIN seat s 
on c.locationNum=s.locationNum;

-- content_time_view
  CREATE OR REPLACE FORCE VIEW "SCOTT"."CONTENT_TIME_VIEW" ("CSEQ", "TITLE", "CONTENTDATE", "CONTENTTIME") AS 
  SELECT  c.cseq, c.title, t.contentDate, t.contentTime
FROM content c
INNER JOIN contentTime t
ON c.cseq=t.cseq;

-- member_grade_view
  CREATE OR REPLACE FORCE VIEW "SCOTT"."MEMBER_GRADE_VIEW" ("CMSEQ", "CID", "CNICKNAME", "SUCCESS", "GRADE", "GNAME", "GPRICE") AS 
  SELECT  m.mseq as cmseq, m.id as cid, m.nickname as cnickname,m.success, m.grade, g.gname, g.gprice
FROM member m
INNER JOIN grade g
ON m.grade=g.gseq;

-- order_view
  CREATE OR REPLACE FORCE VIEW "SCOTT"."ORDER_VIEW" ("ODSEQ", "OSEQ", "INDATE", "MSEQ", "ID", "BUYER_NAME", "BUYER_NICKNAME", "ZIP_NUM", "ADDRESS1", "ADDRESS2", "PHONE", "CSEQ", "IMAGE", "LOCATIONNUM", "TITLE", "LOCATIONNAME", "ARTIST", "CONTENTDATE", "CONTENTTIME", "AREA", "CONTENT_PRICE", "MSEQ2", "COM_NICKNAME", "COM_GRADE", "COM_PRICE", "QUANTITY", "RESULT") AS 
  SELECT  d.odseq, o.oseq, o.indate, o.mseq, 
m.id, m.name as buyer_name, m.nickname as buyer_nickname, m.zip_num,m.address1, m.address2, m.phone,
d.cseq, c.image, d.locationNum, clv.title,clv.locationName, clv.artist, d.contentDate, d.contentTime, clv.area, clv.price as content_price,
d.mseq2, mgv.cnickname as com_nickname, mgv.gname as com_grade, mgv.gprice as com_price,
d.quantity,
d.result 
FROM orders o, order_detail d, member m, content_loc_seat_view clv, member_grade_view mgv, content c
where o.oseq=d.oseq and o.mseq=m.mseq and d.cseq=clv.cseq and d.area=clv.area and d.mseq2=mgv.cmseq and d.cseq=c.cseq;

-- product_all_content_view
  CREATE OR REPLACE FORCE VIEW "SCOTT"."PRODUCT_ALL_CONTENT_VIEW" ("CSEQ", "TITLE", "LOCATIONNUM", "LOCATIONNAME", "ARTIST", "AREA", "PRICE", "AREAIMAGE", "CATEGORY", "BESTYN") AS 
  select c."CSEQ",c."TITLE",c."LOCATIONNUM",c."LOCATIONNAME",c."ARTIST",c."AREA",c."PRICE",c."AREAIMAGE" , p.category, bestyn from content_loc_seat_view c, content p where c.cseq = p.cseq;
  
-- review_reply_member
  CREATE OR REPLACE FORCE VIEW "SCOTT"."REVIEW_REPLY_MEMBER" ("REPSEQ", "MSEQ", "RSEQ", "REPLYCONTENT", "WRITEDATE", "ID") AS 
  select r."REPSEQ",r."MSEQ",r."RSEQ",r."REPLYCONTENT",r."WRITEDATE", m.id from review_reply r, member m where r.mseq = m.mseq;
  
-- success_reply_member
  CREATE OR REPLACE FORCE VIEW "SCOTT"."SUCCESS_REPLY_MEMBER" ("SRSEQ", "MSEQ", "SUCSEQ", "SUCCESSCONTENT", "WRITEDATE", "ID") AS 
  select s."SRSEQ",s."MSEQ",s."SUCSEQ",s."SUCCESSCONTENT",s."WRITEDATE", m.id from success_reply s, member m where s.mseq = m.mseq;
-----------------------------------------------------------------------------------------------------------------------------------------------
-- 프로시저 -------------------------------------------------------------------------------------------------------------------------------------
-- admingetallcount
create or replace PROCEDURE adminGetAllCount(
    p_tableName NUMBER,
    p_key IN VARCHAR2,
    p_cnt  OUT  NUMBER  
)
IS
    v_cnt NUMBER;
BEGIN
    IF  p_tableName=1  THEN
        SELECT COUNT(*) INTO v_cnt FROM content WHERE title LIKE '%'||p_key||'%';
    ELSIF p_tableName=2 THEN
        SELECT COUNT(*) INTO v_cnt FROM member WHERE name LIKE '%'||p_key||'%';
    END IF;
    p_cnt := v_cnt;
END;

-- applyselectcontent
create or replace procedure applySelectContent(
    p_cat in content.category%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from content where category = p_cat;
end;

-- applyselectcontentall
create or replace procedure applySelectContentAll(
    p_cat in content.category%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from content;
end;

-- buyList
create or replace procedure buyList(
    p_mseq in cart.mseq%type,
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from cart where buyyn= 'Y' and mseq = p_mseq order by cartseq asc;
end;

-- Content_Loc_Seat_View_Buy
create or replace procedure Content_Loc_Seat_View_Buy(
    p_cseq in cart.cseq%type,
    p_area in cart.area%type,
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from content_loc_seat_view where cseq = p_cseq and area = p_area;
end;

-- deleteReply
create or replace PROCEDURE deleteReply(
    p_repseq IN review_reply.repseq%TYPE
)
IS
BEGIN
    DELETE FROM review_reply WHERE repseq=p_repseq;
    COMMIT;
END;

-- getAdmin
create or replace PROCEDURE getAdmin(
    p_id IN   worker.id%TYPE,
    p_rc   OUT     SYS_REFCURSOR )
IS
BEGIN
    OPEN p_rc FOR
        select * from worker where id=p_id;

END;

-- getAllContent
create or replace procedure getAllContent(
    p_rc1 out sys_refcursor,
    p_rc2 out sys_refcursor,
    p_rc3 out sys_refcursor,
    p_rc4 out sys_refcursor,
    p_rc5 out sys_refcursor
)
is
begin
    open p_rc1 for
        select * from content where category = 1;
    open p_rc2 for
        select * from content where category = 2;
    open p_rc3 for
        select * from content where category = 3;
    open p_rc4 for
        select * from content where category = 4;
    open p_rc5 for
        select * from content where category = 5;
end;
-- getAllCount
create or replace PROCEDURE getAllCount(
    p_cnt OUT NUMBER
)
IS
    v_cnt NUMBER;
BEGIN
    SELECT count(*) INTO v_cnt FROM review_board;
    SELECT count(*) INTO v_cnt FROM success_board;
    SELECT count(*) INTO v_cnt FROM qna;
    p_cnt := v_cnt;
END;

-- getAllOrderViewByMySeq
create or replace procedure getAllOrderViewByMyseq(
    p_mseq in orders.mseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from order_view where mseq=p_mseq order by oseq desc;
end;

-- getBannerList
create or replace PROCEDURE getBannerList(
    p_rc   OUT     SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM banner ORDER BY order_seq;
        
END;

-- getBestNewSuccessBannerList
create or replace procedure getBestNewSuccessBannerList(
    p_rc1 out sys_refcursor,
    p_rc2 out sys_refcursor,
    p_rc3 out sys_refcursor,
    p_rc4 out sys_refcursor
)
is
begin
    open p_rc1 for
    select * from banner where order_seq <= 5 order by order_seq asc;
    open p_rc2 for
    select * from content where bestyn = 'Y';
    open p_rc3 for
    select * from(select * from content order by cseq desc) where rownum <=4;
    open p_rc4 for
    select * from(select * from success_board order by sucseq desc) where rownum <=4;
end;

-- getCnickName
create or replace procedure getCnickName(
    mseq2 in cart.mseq2%type,
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from member_grade_view where cmseq = mseq2;
end;

-- getCom1
create or replace procedure getCom1(
    p_date commissioner_view.registerdate%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from commissioner_view where registerDate=p_date;
end;
-- getComFinal
create or replace procedure getComFinal(
    tDate in commissioner_view.registerdate%type,
    starttime in commissioner_view.starttime%type,
    tdatetime in content.tdatetime%type,
    endtime in commissioner_view.endtime%type,
    p_mseq in member.mseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from commissioner_view 
        where registerDate=tDate and starttime<=tdatetime and tdatetime<=endtime and mseq!=p_mseq order by success desc;
end;
-- getConcert
create or replace procedure getConcert(
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from content where category = 1;
end;

-- getContent
create or replace PROCEDURE getContent(
    p_cseq IN content.cseq%TYPE, 
    p_cur OUT SYS_REFCURSOR   )
IS
BEGIN
    OPEN p_cur FOR SELECT * FROM content where cseq=p_cseq;
END;

-- getContentList
create or replace PROCEDURE getContentList(
    p_startNum IN NUMBER,
    p_endNUM IN NUMBER,
    p_key IN CONTENT.TITLE%TYPE,
    p_rc   OUT     SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM (
        SELECT * FROM (
        SELECT rownum as rn, p.* FROM
        ((SELECT * FROM CONTENT WHERE title LIKE '%'||p_key||'%'  ORDER BY CSEQ DESC) p)
        )WHERE rn>=p_startNum
        )WHERE rn<=p_endNum;
    
END;

-- getContentTimeList
create or replace procedure getContentTimeList(
    p_cseq in content.cseq%type,
    p_rc1 out sys_refcursor,
    p_rc2 out sys_refcursor
)
is
begin
    open p_rc1 for
        select * from content where cseq = p_cseq;
    open p_rc2 for
        select * from content_time_view where cseq = p_cseq;
    commit;
end;

-- getExhibition
create or replace procedure getExhibition(
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from content where category = 5;
end;

-- getFestival
create or replace procedure getFestival(
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from content where category = 4;
end;

-- getMember
create or replace PROCEDURE getMember(
    p_id IN member.id%type,
    p_cur OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_cur FOR SELECT * FROM member WHERE id=p_id;
END;

-- getMemberList
create or replace PROCEDURE getMemberList(
    p_startNum IN NUMBER,
    p_endNUM IN NUMBER,
    p_key IN member.NAME%TYPE,
    p_rc   OUT     SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM (
        SELECT * FROM (
        SELECT rownum as rn, p.* FROM
        ((SELECT * FROM member WHERE name LIKE '%'||p_key||'%'  ORDER BY indate DESC) p)
        )WHERE rn>=p_startNum
        )WHERE rn<=p_endNum;
    
END;

-- getMusical
create or replace procedure getMusical(
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from content where category = 2;
end;

-- getMyRegister
create or replace procedure getMyRegister(
    p_mseq in member.mseq%type,
    p_ref out sys_refcursor
)
is
begin
    open p_ref for
        select * from registertime where mseq = p_mseq;
end;

-- getOrderDetail
create or replace procedure getOrderDetail(
    p_oseq in orders.oseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from order_view where oseq = p_oseq;
end;

-- getOrderDetail
create or replace procedure getOrderDetail(
    p_oseq in orders.oseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from order_view where oseq = p_oseq;
end;
-- getOrderList
create or replace PROCEDURE getOrderList(
    p_startNum IN NUMBER,
    p_endNUM IN NUMBER,
    p_key IN member.NAME%TYPE,
    p_rc   OUT     SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM (
        SELECT * FROM (
        SELECT rownum as rn, p.* FROM
        ((SELECT * FROM order_view WHERE BUYER_NAME LIKE '%'||p_key||'%'  ORDER BY result ASC , indate DESC  ) p)
        )WHERE rn>=p_startNum
        )WHERE rn<=p_endNum;
    
END;

-- getOrderViewByMyMseq
create or replace procedure getOrderViewByMyMseq(
    p_mseq in orders.mseq%type,
    p_ref out sys_refcursor
)
is
begin
    open p_ref for
        select distinct oseq from orders where mseq=p_mseq order by oseq desc;
end;

--1대1 문의 게시판
CREATE OR REPLACE PROCEDURE getQna(
    p_qseq IN qna.qseq%type,
    p_cur1 OUT SYS_REFCURSOR,
    p_cur2 OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_cur1 FOR
        SELECT * FROM qna WHERE qseq=p_qseq;
    OPEN p_cur2 FOR
        SELECT * FROM qna WHERE qseq=p_qseq ORDER BY reply DESC;
END;

-- getQnaList
create or replace PROCEDURE getQnaList(
    p_startNum IN NUMBER,
    p_endNUM IN NUMBER,
    p_key IN qna.subject%TYPE,
    p_rc   OUT     SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM (
        SELECT * FROM (
        SELECT rownum as rn, p.* FROM
        ((SELECT * FROM qna WHERE subject LIKE '%'||p_key||'%' OR  content LIKE '%'||p_key||'%'  ORDER BY qseq DESC  ) p)
        )WHERE rn>=p_startNum
        )WHERE rn<=p_endNum;
END;

-- getReplyList
create or replace procedure getReplyList(
    p_seq in success_reply.sucseq%type,
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from success_reply where sucseq = p_seq order by srseq desc;
end;

-- getReplyMember
create or replace procedure getReplyMember(
    p_seq in success_reply.sucseq%type,
    p_rc out sys_refcursor
)
is
begin

    open p_rc for
        select id from success_reply o left join member d on o.mseq = d.mseq where o.sucseq = p_seq;
end;

-- getReview
create or replace PROCEDURE getReview(
    p_rseq IN REVIEW_REPLY_MEMBER.rseq%type,
    p_cur1 OUT SYS_REFCURSOR,
    p_cur2 OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_cur1 FOR
        SELECT * FROM review_board WHERE rseq=p_rseq;
    OPEN p_cur2 FOR
        SELECT * FROM REVIEW_REPLY_MEMBER WHERE rseq=p_rseq ORDER BY repseq DESC;
END;

-- getSports
create or replace procedure getSports(
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from content where category = 3;
end;

-- getSuccess
create or replace PROCEDURE getSuccess(
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

-- getSuccessListBySucSeq
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

-- getSuccessListBySusSeq
create or replace procedure getSuccessListBySusseq(
    p_seq in success_board.sucseq%type,
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from success_board where sucseq = p_seq;
    commit;
end;

-- hoonGetOrderList
create or replace procedure hoonGetOrderList(
    p_oseq in order_view.oseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from order_view where oseq = p_oseq;
end;

-- insertBanner
create or replace PROCEDURE insertBanner(
    p_subject IN banner.subject%TYPE,
    p_order_seq IN banner.order_seq%TYPE, 
    p_useyn IN banner.useyn%TYPE,
    p_image IN banner.image%TYPE  )
IS
BEGIN
    INSERT INTO banner( bseq, subject, order_seq, useyn, image) 
    VALUES( banner_seq.nextVal, p_subject, p_order_seq, p_useyn, p_image );
    COMMIT;
END;

-- insertContent
create or replace PROCEDURE insertContent(
    p_title IN content.title%TYPE,
    p_category IN content.category%TYPE, 
    p_content IN content.content%TYPE, 
     p_age IN content.age%TYPE, 
       p_artist IN content.artist%TYPE, 
    p_image IN content.image%TYPE  )
IS
BEGIN

    INSERT INTO content( cseq, title, category, content, image, artist, age) 
    VALUES( content_cseq.nextVal, p_title, p_category, p_content, p_image, p_artist, p_age );
    COMMIT;
END;

-- insertMember
create or replace PROCEDURE insertMember(
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

-- insertQna
create or replace PROCEDURE insertQna(
    p_id IN qna.id%TYPE,
    p_check IN qna.passcheck%TYPE,
    p_pass IN qna.pass%TYPE,
    p_subject  IN qna.subject%TYPE,
    p_content  IN qna.content%TYPE )
IS
BEGIN
    insert into qna(qseq, id, passcheck, pass, subject, content) 
    values( qna_qseq.nextVal, p_id, p_check, p_pass, p_subject, p_content );
    commit;    
END;

-- insertReply
create or replace PROCEDURE insertReply(
    p_rseq IN review_reply.rseq%TYPE,
    p_mseq IN review_reply.mseq%TYPE,
    p_replycontent IN review_reply.replycontent%TYPE
)
IS
BEGIN
    INSERT INTO review_reply(repseq, rseq, mseq, replycontent)
    VALUES(REVIEW_REPLY_REPSEQ.nextVal, p_rseq, p_mseq, p_replycontent);
    COMMIT;
END;

-- insertReview
create or replace PROCEDURE insertReview(
    p_mseq IN REVIEW_BOARD.mseq%type,
    p_id IN REVIEW_BOARD.id%type,
    p_pwd IN REVIEW_BOARD.pwd%type,
    p_title IN REVIEW_BOARD.title%type,
    p_content IN REVIEW_BOARD.content%type,
    p_imgfilename IN REVIEW_BOARD.imgfilename%type
)
IS
BEGIN
    INSERT INTO REVIEW_BOARD( rseq, mseq, id, pwd, title, content, imgfilename )
    VALUES( REVIEW_BOARD_RSEQ.nextVal, p_mseq, p_id, p_pwd, p_title, p_content, p_imgfilename );
    COMMIT;
END;

-- insertSuccessBoard
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

-- insertSuccessReply
create or replace PROCEDURE insertSuccessReply(
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

-- joinKakao
create or replace PROCEDURE joinKakao(
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

-- listOrderByOseq
create or replace procedure listOrderByOseq(
    p_oseq in orders.oseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from order_view where oseq = p_oseq;
end;

-- listQna
create or replace PROCEDURE listQna(
    p_startNum IN NUMBER,
    p_endNum IN NUMBER,
    p_cursor OUT SYS_REFCURSOR )
IS
    temp_cur SYS_REFCURSOR; --게시물 번호만 조회한 결과를 담을 커서변수
    v_num NUMBER;   --그들의 게시물 번호들을 번갈아가며 저장할 변수
    v_rownum NUMBER;    --그들의 행번호들을 번갈아가며 저장항 변수
BEGIN
    --board 테이블에서 startNum 과 endNum 사이의 게시물을 조회하되, 게시물 번호(num) 값만 취합니다.(rownum 도 같이)
    --num 값으로 reply 테이블에서 boardnum 이 num 인 레코드가 몇개인지 갯수을 구합니다
    --num 값과 댓글 갯수를 이용해서 board 테이브르이 replycnt 필드를 update 합니다
    OPEN temp_cur FOR
        SELECT * FROM (
            SELECT * FROM (
                SELECT rownum as rn, q.qseq FROM((SELECT * FROM qna ORDER BY qseq DESC)q)
            )WHERE rn>=p_startNum
        )WHERE rn<=p_endNum;

    COMMIT;

    --댓글갯수가 채워진 대상 게시물을 조회해서 p_cursor에 담습니다.
    OPEN p_cursor FOR
            SELECT * FROM (
                SELECT * FROM (
                    SELECT rownum as rn, q.* FROM((SELECT * FROM qna ORDER BY qseq DESC)q)
                )WHERE rn>=p_startNum
            )WHERE rn<=p_endNum;
END;

-- memberReinsert
create or replace PROCEDURE memberReinsert(
    p_id IN member.id%TYPE,
    p_useyn IN member.useyn%TYPE )
IS
BEGIN
    UPDATE  member SET useyn=p_useyn WHERE id=p_id;
    COMMIT;
END;

-- notBuyList
create or replace procedure notBuyList(
    p_mseq in cart.mseq%type,
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from cart where buyyn = 'N' and mseq = p_mseq;
end;

-- plusOneReadCount
create or replace PROCEDURE plusOneReadCount(
    p_rseq IN review_board.rseq%type )
IS
BEGIN
    UPDATE review_board SET readcount = readcount + 1 WHERE rseq=p_rseq;
    COMMIT;
END;

-- readCountOne
create or replace procedure readCountOne(
    p_seq in success_board.sucseq%type
)
is
begin
    update success_board set readcount = readcount + 1 where sucseq = p_seq;
end;

-- removeQna
create or replace PROCEDURE removeQna(
    p_qseq IN qna.qseq%TYPE
)
IS
BEGIN
    DELETE FROM qna WHERE qseq=p_qseq;
    COMMIT;
END;

-- removeReview
create or replace PROCEDURE removeReview(
    p_rseq IN REVIEW_BOARD.rseq%TYPE
)
IS
BEGIN
    DELETE FROM REVIEW_BOARD WHERE rseq=p_rseq;
    DELETE FROM review_reply WHERE repseq=p_rseq;
    COMMIT;
END;

-- replyDelete
create or replace procedure replyDelete(
    p_seq success_reply.srseq%type
)
is
begin
    delete from success_reply where srseq = p_seq;
end;

-- sap
create or replace procedure sap(
    p_cseq in content_loc_seat_view.cseq%type,
    p_area in content_loc_seat_view.area%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select cseq, locationName, area,price, areaImage from content_loc_seat_view where cseq=p_cseq and area=p_area;
end;

-- scbc
create or replace procedure scbc(
    p_cseq in content.cseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from content where cseq = p_cseq;
end;

-- scfln
create or replace procedure scfln(
    p_cseq in content.cseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from content where cseq=p_cseq;
end;

-- sclsvb
create or replace procedure sclsvb(
    p_cseq in cart.cseq%type,
    p_area in cart.area%type,
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from content_loc_seat_view where cseq = p_cseq and area = p_area;
end;

-- searchContentByTitle
create or replace procedure searchContentByTitle(
    p_key in varchar2,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from content where title like '%'||p_key||'%';
end;

-- selectContentByTitle
create or replace procedure searchContentByTitle(
    p_key in varchar2,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from content where title like '%'||p_key||'%';
end;

-- select_Content_Loc_Seat_View
create or replace procedure select_Content_Loc_Seat_View(
    p_cseq in cart.cseq%type,
    p_area in cart.area%type,
    p_rc out sys_refcursor
)
is
begin
    open p_rc for
        select * from content_loc_seat_view where cseq = p_cseq and area = p_area;
end;

-- selectBoard
create or replace PROCEDURE selectBoard(
    p_startNum IN NUMBER,
    p_endNum IN NUMBER,
    p_cursor OUT SYS_REFCURSOR )
IS
    temp_cur SYS_REFCURSOR; --게시물 번호만 조회한 결과를 담을 커서변수
    v_num NUMBER;   --그들의 게시물 번호들을 번갈아가며 저장할 변수
    v_rownum NUMBER;    --그들의 행번호들을 번갈아가며 저장항 변수
    v_cnt NUMBER;   --각 게시물 번호로 조회한 댓글갯수를 저장할 변수
BEGIN
    --board 테이블에서 startNum 과 endNum 사이의 게시물을 조회하되, 게시물 번호(num) 값만 취합니다.(rownum 도 같이)
    --num 값으로 reply 테이블에서 boardnum 이 num 인 레코드가 몇개인지 갯수을 구합니다
    --num 값과 댓글 갯수를 이용해서 board 테이브르이 replycnt 필드를 update 합니다
    OPEN temp_cur FOR
        SELECT * FROM (
            SELECT * FROM (
                SELECT rownum as rn, r.rseq FROM((SELECT * FROM review_board ORDER BY rseq DESC)r)
            )WHERE rn>=p_startNum
        )WHERE rn<=p_endNum;
    LOOP
        FETCH temp_cur INTO v_rownum, v_num;
        EXIT WHEN temp_cur%NOTFOUND;
        SELECT count(*) INTO v_cnt FROM review_reply WHERE repseq=v_num;
        UPDATE review_board SET reply = v_cnt WHERE rseq=v_num;
    END LOOP;
    COMMIT;

    --댓글갯수가 채워진 대상 게시물을 조회해서 p_cursor에 담습니다.
    OPEN p_cursor FOR
            SELECT * FROM (
                SELECT * FROM (
                    SELECT rownum as rn, r.* FROM((SELECT * FROM review_board ORDER BY rseq DESC)r)
                )WHERE rn>=p_startNum
            )WHERE rn<=p_endNum;
END;

-- selectReview
create or replace PROCEDURE selectReview(
    p_startNum IN NUMBER,
    p_endNum IN NUMBER,
    p_cursor OUT SYS_REFCURSOR )
IS
    temp_cur SYS_REFCURSOR; --게시물 번호만 조회한 결과를 담을 커서변수
    v_num NUMBER;   --그들의 게시물 번호들을 번갈아가며 저장할 변수
    v_rownum NUMBER;    --그들의 행번호들을 번갈아가며 저장항 변수
    v_cnt NUMBER;   --각 게시물 번호로 조회한 댓글갯수를 저장할 변수
BEGIN
    --board 테이블에서 startNum 과 endNum 사이의 게시물을 조회하되, 게시물 번호(num) 값만 취합니다.(rownum 도 같이)
    --num 값으로 reply 테이블에서 boardnum 이 num 인 레코드가 몇개인지 갯수을 구합니다
    --num 값과 댓글 갯수를 이용해서 board 테이브르이 replycnt 필드를 update 합니다
    OPEN temp_cur FOR
        SELECT * FROM (
            SELECT * FROM (
                SELECT rownum as rn, r.rseq FROM((SELECT * FROM review_board ORDER BY rseq DESC)r)
            )WHERE rn>=p_startNum
        )WHERE rn<=p_endNum;
    LOOP
        FETCH temp_cur INTO v_rownum, v_num;
        EXIT WHEN temp_cur%NOTFOUND;
        SELECT count(*) INTO v_cnt FROM review_reply WHERE repseq=v_num;
        UPDATE review_board SET reply = v_cnt WHERE rseq=v_num;
    END LOOP;
    COMMIT;

    --댓글갯수가 채워진 대상 게시물을 조회해서 p_cursor에 담습니다.
    OPEN p_cursor FOR
            SELECT * FROM (
                SELECT * FROM (
                    SELECT rownum as rn, r.* FROM((SELECT * FROM review_board ORDER BY rseq DESC)r)
                )WHERE rn>=p_startNum
            )WHERE rn<=p_endNum;
END;

-- sfcabt
create or replace procedure sfcabt(
    p_loc in seat.locationNum%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from seat where locationNum=p_loc order by price desc;
end;

-- sfcbt
create or replace procedure sfcbt(
    p_cseq in content.cseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from content where cseq=p_cseq;
end;

-- sfctbt
create or replace procedure sfctbt(
    p_cseq in content.cseq%type,
    p_rf out sys_refcursor,
    p_rf1 out sys_refcursor
)
is
begin
    open p_rf for
        select distinct contentDate, cseq from contentTime where cseq=p_cseq;
    open p_rf1 for
        select locationNum from content where cseq=p_cseq;
end;

--sflvbt
create or replace procedure sflvbt(
    p_cseq in content.cseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from content_loc_seat_view where cseq=p_cseq;
end;

-- so
create or replace procedure so(
    p_mseq in orders.mseq%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select * from orders where mseq=p_mseq and oseq = (SELECT MAX(oseq) FROM orders);
end;

-- stbd
create or replace procedure stbd(
    p_cseq in content.cseq%type,
    p_contentDate in contenttime.contentDate%type,
    p_rf out sys_refcursor
)
is
begin
    open p_rf for
        select distinct contenttime from  content_time_view where cseq= p_cseq and contentdate=p_contentDate order by contenttime;
end;

-- updateContent
create or replace PROCEDURE updateContent(
    p_cseq IN content.cseq%TYPE,
    p_title IN content.title%TYPE,
    p_content IN content.content%TYPE, 
    p_image IN content.image%TYPE  )
IS
BEGIN
    UPDATE  content SET title=p_title, content=p_content, image=p_image WHERE cseq=p_cseq;
    COMMIT;
END;

-- updateMember
create or replace PROCEDURE updateMember(
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

-- updateOna
create or replace PROCEDURE updateOna(
    p_qseq IN qna.qseq%TYPE,
    p_reply IN qna.reply%TYPE)
IS
BEGIN
    UPDATE  qna SET reply=p_reply, rep='2' WHERE qseq=p_qseq;
    COMMIT;
END;

--updateQna
create or replace procedure updateQna(
    p_qseq in qna.qseq%type,
    p_reply in qna.reply%type
)
is
begin
    update qna set reply = p_reply, rep = '2' where qseq = p_qseq;
end;

-- updateReview
create or replace PROCEDURE updateReview(
    p_rseq IN REVIEW_BOARD.rseq%type,
    p_mseq IN REVIEW_BOARD.mseq%type,
    p_id IN REVIEW_BOARD.id%type,
    p_pwd IN REVIEW_BOARD.pwd%type,
    p_title IN REVIEW_BOARD.title%type,
    p_content IN REVIEW_BOARD.content%type,
    p_imgfilename IN REVIEW_BOARD.imgfilename%type
)
IS
BEGIN
    UPDATE REVIEW_BOARD SET pwd=p_pwd, id=p_id, mseq=p_mseq, title=p_title,
    content=p_content, imgfilename=p_imgfilename WHERE rseq=p_rseq;
    COMMIT;
END;

-- updateSeq
create or replace procedure updateSeq(
    p_bseq in banner.bseq%type,
    p_useyn in banner.useyn%type,
    p_chg in banner.order_seq%type
)
is
begin
    update banner set useyn = p_useyn, order_seq = p_chg where bseq=p_bseq;
end;

--qna 페이징
CREATE OR REPLACE PROCEDURE getAllCountQna(
    p_cnt OUT NUMBER
)
IS
    v_cnt NUMBER;
BEGIN
    SELECT count(*) INTO v_cnt FROM qna;
    p_cnt := v_cnt;
END;


-----------------------------------------------------------------------------------------------------------------------------------------------