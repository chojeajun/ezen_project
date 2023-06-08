--장바구니

select * from cart;
select * from member
select * from cart where mseq = 2 and buyyn = 'N'
delete from CART;
alter table cart modify mseq2 number(5,0) default 0;
select * from member_grade_view where cmseq=0;
select * from order_view

alter table cart drop primary key;
alter table cart add PRIMARY KEY (cartseq, mseq, cseq, contentDate, contentTime, locationNum, area, mseq2);

insert into cart(cartseq, mseq, cseq, CONTENTDATE,contentTime, locationNum, area) 
values(cart_cartseq.nextVal,7,11, to_date('20230528','yyyy-mm-dd'),'14:00',12,'테이블석');
insert into cart(cartseq, mseq, cseq, CONTENTDATE,contentTime, locationNum, area) 
values(cart_cartseq.nextVal,10,6, to_date('20230612','yyyy-mm-dd'),'19:00',4,'G1석');
insert into cart(cartseq, mseq, cseq, CONTENTDATE,contentTime, locationNum, area, buyyn) 
values(cart_cartseq.nextVal,2,3, to_date('20230610','yyyy-mm-dd'),'17:00',1,'R석', 'Y');
UPDATE cart SET mseq2=0 WHERE mseq=2 and cseq=6;
UPDATE cart SET mseq2=1 WHERE mseq=2 and cseq=6
--orders
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,1,5);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,6);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,3,10);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,4,7);

insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);
insert into orders(oseq,mseq,cseq) values(orders_oseq.nextVal,2,11);

delete from orders;
alter table orders add cseq number(5,0) not null;

select * from order_view;
select * from orders;
select * from member;
select * from content;
select * from locationNum;
select * from contentTime;
select * from seat;
select * from order_detail;


--주문 상세 내역
insert into order_detail(odseq,oseq,mseq,cseq,locationNum,contentDate,contentTime,area,mseq2,quantity) 
values(order_detail_odseq.nextVal,1,1,5,4,to_date('20230617','yyyy-mm-dd'),'20:00','G1석',3,2);

insert into order_detail(odseq,oseq,mseq,cseq,locationNum,contentDate,contentTime,area,mseq2) 
values(order_detail_odseq.nextVal,2,2,6,4,to_date('20230611','yyyy-mm-dd'),'19:00','G1석',3);
insert into order_detail(odseq,oseq,mseq,cseq,locationNum,contentDate,contentTime,area,mseq2) 
values(order_detail_odseq.nextVal,3,3,10,9,to_date('20230523','yyyy-mm-dd'),'20:00','R석',4);
insert into order_detail(odseq,oseq,mseq,cseq,locationNum,contentDate,contentTime,area,mseq2) 
values(order_detail_odseq.nextVal,3,3,10,9,to_date('20230524','yyyy-mm-dd'),'20:00','vip석',3);


insert into order_detail(odseq,oseq,mseq,cseq,locationNum,contentDate,contentTime,area,mseq2) 
values(order_detail_odseq.nextVal,4,4,7,12,to_date('20230701','yyyy-mm-dd'),'19:00','내야',9);
insert into order_detail(odseq,oseq,mseq,cseq,locationNum,contentDate,contentTime,area,mseq2,quantity) 
values(order_detail_odseq.nextVal,4,4,1,8,to_date('20230709','yyyy-mm-dd'),'17:00','전석',10,3);
insert into order_detail(odseq,oseq,mseq,cseq,locationNum,contentDate,contentTime,area,mseq2) 
values(order_detail_odseq.nextVal,4,4,1,8,to_date('20230709','yyyy-mm-dd'),'17:00','전석',10);

