
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
	srseq number(5,0) NOT NULL,
	sucseq number(5) NOT NULL,
	mseq number(5,0) NOT NULL,
	successContent varchar2(3000) NOT NULL,
	writeDate date DEFAULT sysdate,
	PRIMARY KEY (srseq)
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



