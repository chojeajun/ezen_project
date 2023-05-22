select * from ADDRESS;
select * from admin;
select * from cart;
select * from contentTime;
select * from ORDER_DETAIL;
select * from CONTENT;
select * from GRADE;
select * from ORDERS;
select * from QNA_BOARD;
select * from REVIEW_BOARD;
select * from success_board;
select * from SEAT;
select * from MEMBER;


select * from user_sequences;







insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextval,'dsan','1234','김두산',2,'최강두산',to_date('19980125','yyyy-mm-dd'),'dsan82@gmail.com','010-8282-8282',
'133-110', '서울시 성동구 성수동1가' , '1번지21호',1,1);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'bts2','1234','김민정',1,'뷔이이',to_date('20061226','yyyy-mm-dd'),'vtan@gmail.com','010-1111-1111',
'135-927', '서울 강남구 역삼2동','1번지21호',0,0);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'gangji','1234','박창완',1,'강쥐',to_date('19890605','yyyy-mm-dd'),'doggie@gmail.com','010-2222-2222',
'135-928', '서울 강남구 역삼2동','1번지21호',2,10);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'jrong','1234','이재롱',2,'재롱이',to_date('20031130','yyyy-mm-dd'),'jrong@gmail.com','010-3333-3333',
'135-949', '서울 강남구 청담2동','1번지21호',3,18);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'kinder','1234','정해솔',1,'킨더',to_date('19950729','yyyy-mm-dd'),'kinder@gmail.com','010-4444-4444',
'402-831', '인천 남구 용현3동','1번지21호',0,0);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'babo','1234','김두기',1,'밥',to_date('19920819','yyyy-mm-dd'),'babo@gmail.com','010-5555-5555',
'402-807', '인천 남구 도화3동','1번지21호',1,3);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'gildong','1234','홍길동',1,'길도이',to_date('19991128','yyyy-mm-dd'),'gildong@gmail.com','010-6666-6666',
'614-852', '부산 진구 양정2동','1번지21호',0,0);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'work','1234','박휘민',2,'직장인',to_date('19960402','yyyy-mm-dd'),'worker@gmail.com','010-7777-7777',
'431-710', '경기 안양시 동안구 귀인동', '꿈마을한신아파트',1,2);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'sleep','1234','김진원',1,'졸림',to_date('19710313','yyyy-mm-dd'),'sleeeepy@gmail.com','010-7777-7777',
'431-802', '경기 안양시 동안구 귀인동','1번지21호',1,2);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'java','1234','정철원',1,'javababo',to_date('19901206','yyyy-mm-dd'),'javababo@gmail.com','010-7777-7777',
'472-865', '경기 남양주시 진접읍 장현3리','1번지21호',1,2);
insert into MEMBER(mseq,id,pwd, name,gender,nickname,birth,email,phone,zip_num, address1, address2,grade, success) 
values (member_mseq.nextVal,'nomoney','1234','정수빈',2,'gimmemoney',to_date('19950515','yyyy-mm-dd'),'money@gmail.com','010-7777-7777',
'269-823', '강원 철원군 철원읍 화지8리','1번지21호',1,2);


select * from MEMBER;