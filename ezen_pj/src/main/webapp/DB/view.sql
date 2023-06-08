--view들 만드는 파일
select * from member_grade_view
--1. content+locationNum+seat 합친 뷰
CREATE OR REPLACE VIEW content_loc_seat_view 
AS 
SELECT c.cseq, c.title, c.locationNum, lo.locationName, c.artist, s.area, s.price, lo.areaImage
FROM CONTENT c 
INNER JOIN LOCATIONNUM lo 
on c.locationNum=lo.locationNum
left JOIN seat s 
on c.locationNum=s.locationNum;

select* from content_loc_seat_view order by locationNum;
select* from content_loc_seat_view order by cseq;

select*from content
select*from CONTENT order by cseq;
select*from locationNum;
select*from seat;
select*from contentTime;
select distinct to_date('contentTime','yyyy-mm-dd') from  content_time_view where contentDate='2023-05-24' order by contentTime;

--2. content+contentTime 합친 뷰

CREATE OR REPLACE VIEW content_time_view
AS 
SELECT  c.cseq, c.title, t.contentDate, t.contentTime
FROM content c
INNER JOIN contentTime t
ON c.cseq=t.cseq;

select*from content_time_view order by cseq;


-- 3. member+ grade 합친 뷰
select * from member;
select * from grade;
select * from member_grade_view;

CREATE OR REPLACE VIEW member_grade_view
AS 
SELECT  m.mseq as cmseq, m.id as cid, m.nickname as cnickname,m.success, m.grade, g.gname, g.gprice
FROM member m
INNER JOIN grade g
ON m.grade=g.gseq;

--4. cart+member+content 합친 뷰 만들기

select * from CART;


CREATE OR REPLACE VIEW cart_view
AS 
SELECT  ca.mseq,ca.cseq, cv.title, ca.locationNum, cv.locationname, ca.area, 
cv.price, ca.mseq2,ca.quantity, ca.indate, ca.buyyn 
FROM cart ca
INNER JOIN content_loc_seat_view cv
ON ca.cseq=cv.cseq;

select * from cart_view;
---여기까지는 대리인 관련 정보 없음

CREATE OR REPLACE VIEW cart_total_view
AS 
SELECT  ca.mseq,ca.cseq, ca.title, ca.locationNum, ca.locationname, ca.area,  m.cnickname, m.gprice,
ca.price, ca.mseq2,ca.quantity, ca.indate, ca.buyyn 
FROM cart_view ca , member_grade_view m
where ca.mseq2=m.cmseq(+);

select * from cart_total_view;

---------------------------
--orders+member+content_time_view+content_loc_seat_view+order_detail 합친 뷰
select * from orders;
select * from order_detail;
select * from member;
select * from content_time_view;
select * from content_loc_seat_view;
select *from member_grade_view;


CREATE OR REPLACE VIEW order_view
AS 
SELECT  d.odseq, o.oseq, o.indate, o.mseq, 
m.id, m.name as buyer_name, m.nickname as buyer_nickname, m.zip_num,m.address1, m.address2, m.phone,
d.cseq, c.image, d.locationNum, clv.title,clv.locationName, clv.artist, d.contentDate, d.contentTime, clv.area, clv.price as content_price,
d.mseq2, mgv.cnickname as com_nickname, mgv.gname as com_grade, mgv.gprice as com_price,
d.quantity,
d.result 
FROM orders o, order_detail d, member m, content_loc_seat_view clv, member_grade_view mgv, content c
where o.oseq=d.oseq and o.mseq=m.mseq and d.cseq=clv.cseq and d.area=clv.area and d.mseq2=mgv.cmseq and d.cseq=c.cseq;


select*from order_view order by oseq;


---------------
--member_grade_view+registerTime 합친 view

select*from registerTime;
select*from member_grade_view;
select*from commissioner_view;

create or replace view commissioner_view 
as
select r.mseq, m.cid, m.cnickname, m.grade,m.gname, m.success, r.registerDate ,r.startTime, r.endTime, m.gprice as com_price
from registerTime r
inner join member_grade_view m 
on r.mseq=m.cmseq;


select * from order_view where mseq = 2 and result = 'N'
select * from locationnum
select * from cart
select distinct(mseq), cnickname from commissioner_view order by mseq asc
select * from commissioner_view


-- review_reply , member view
-- member테이블과 review_reply 테이블에서 mseq 가 일치하면 
-- review_reply 테이블에서는 모든 내용을, member 테이블에서는 id 를 가져오겠다
create or replace view review_reply_member as select r.*, m.id from review_reply r, member m where r.mseq = m.mseq;

commit
select * from review_reply_member;
select * from success_reply;
select * from success_reply_member;

-- success_reply , member view
-- member테이블과 success_reply 테이블에서 mseq 가 일치하면 
-- success_reply 테이블에서는 모든 내용을, member 테이블에서는 id 를 가져오겠다
create or replace view success_reply_member as select s.*, m.id from success_reply s, member m where s.mseq = m.mseq;

commit
select * from success_reply_member;

-- content 와 content_loc_seat_view 를 합쳐서 뷰 만들고 productlist 에 뿌릴 내용을 한번에 가져옴
create or replace view product_all_content_view as select c.* , p.category, bestyn from content_loc_seat_view c, content p where c.cseq = p.cseq;


select * from product_all_content_view;






