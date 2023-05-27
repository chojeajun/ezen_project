select * from locationNum;


insert into locationNum values(locationNum_seq.nextVal,'KBS부산홀');
insert into locationNum values(locationNum_seq.nextVal,'잠실 실내체육관');
insert into locationNum values(locationNum_seq.nextVal,'올림픽공원 올림픽홀');
insert into locationNum values(locationNum_seq.nextVal,'서울 잠실종합운동장 올림픽주경기장');
insert into locationNum values(locationNum_seq.nextVal,'블루스퀘어 마스터카드홀');
insert into locationNum values(locationNum_seq.nextVal,'수원종합운동장 실내체육관');
insert into locationNum values(locationNum_seq.nextVal,'백암아트홀');
insert into locationNum values(locationNum_seq.nextVal,'대구 엑스코 오디토리움');
insert into locationNum values(locationNum_seq.nextVal,'예술의전당 오페라극장');
insert into locationNum values(locationNum_seq.nextVal,'잠실야구장');
insert into locationNum values(locationNum_seq.nextVal,'수원월드컵경기장');
insert into locationNum values(locationNum_seq.nextVal,'고척스카이돔');
insert into locationNum values(locationNum_seq.nextVal,'삼성라이온즈파크');
insert into locationNum values(locationNum_seq.nextVal,'샤롯데씨어터');
insert into locationNum values(locationNum_seq.nextVal,'블루스퀘어 신한카드홀');
insert into locationNum values(locationNum_seq.nextVal,'서울 올림픽 공원 88잔디마당');
insert into locationNum values(locationNum_seq.nextVal,'더현대서울 6층');
insert into locationNum values(locationNum_seq.nextVal,'인사센트럴뮤지엄');

--seat
insert into seat (locationNum, area, price)
values(1, 'R석', 150000);
insert into seat (locationNum, area, price)
values(1, 'S석', 100000);

insert into seat (locationNum, area, price)
values(2,  'vip standing', 200000);
insert into seat (locationNum, area, price)
values(2, 'vip seated', 200000);
insert into seat (locationNum, area, price)
values(2, 'seated', 10000);

insert into seat (locationNum, area, price)
values(3,  'R석', 150000);
insert into seat (locationNum, area, price)
values(3, 'S석', 100000);

insert into seat (locationNum, area, price)
values(4,  'G1석', 250000);
insert into seat (locationNum, area, price)
values(4, 'G2석', 200000);
insert into seat (locationNum, area, price)
values(4, 'G3석', 150000);
insert into seat (locationNum, area, price)
values(4,'P석', 150000);
insert into seat (locationNum, area, price)
values(4,'R석', 100000);
insert into seat (locationNum, area, price)
values(4, 'S석', 70000);

insert into seat (locationNum, area, price)
values(5,  '전석', 110000);

insert into seat (locationNum, area, price)
values(6, 'SR석', 200000);
insert into seat (locationNum, area, price)
values(6, 'R석', 150000);
insert into seat (locationNum, area, price)
values(6, 'S석', 100000);
insert into seat (locationNum, area, price)
values(6, 'A석', 50000);

insert into seat (locationNum, area, price)
values(7, 'vip석', 200000);
insert into seat (locationNum, area, price)
values(7, 'op석', 150000);
insert into seat (locationNum, area, price)
values(7, 'R석', 100000);
insert into seat (locationNum, area, price)
values(7, 'S석', 80000);
insert into seat (locationNum, area, price)
values(7, 'A석', 50000);

insert into seat (locationNum, area, price)
values(8, '전석', 5000);

insert into seat (locationNum, area, price)
values(9,  'vip석', 200000);
insert into seat (locationNum, area, price)
values(9, 'R석', 150000);

insert into seat (locationNum, area, price)
values(10, 'R석', 150000);
insert into seat (locationNum, area, price)
values(10, 'S석', 100000);
insert into seat (locationNum, area, price)
values(10, 'A석', 80000);
insert into seat (locationNum, area, price)
values(10, 'B석', 50000);
insert into seat (locationNum, area, price)
values(10, 'C석', 30000);

insert into seat (locationNum, area, price)
values(11,'블루석', 30000);
insert into seat (locationNum, area, price)
values(11,'오렌지석', 50000);
insert into seat (locationNum, area, price)
values(11,'레드석', 2000);
insert into seat (locationNum, area, price)
values(11,'네이비석', 10000);
insert into seat (locationNum, area, price)
values(11,'외야석', 0);



insert into seat (locationNum, area, price)
values(12,'테이블석', 50000);
insert into seat (locationNum, area, price)
values(12,'내야', 30000);
insert into seat (locationNum, area, price)
values(12,'외야', 0);

insert into seat (locationNum, area, price)
values(13,'VIP석', 50000);
insert into seat (locationNum, area, price)
values(13,'익사이팅석', 30000);
insert into seat (locationNum, area, price)
values(13,'블루존', 20000);
insert into seat (locationNum, area, price)
values(13,'3루내야지정석', 30000);
insert into seat (locationNum, area, price)
values(13,'1루내야지정석',10000);
insert into seat (locationNum, area, price)
values(13,'잔디석',10000);

insert into seat (locationNum, area, price)
values(14,'프리미엄석', 50000);
insert into seat (locationNum, area, price)
values(14,'벤치석', 30000);
insert into seat (locationNum, area, price)
values(14,'W석', 20000);
insert into seat (locationNum, area, price)
values(14,'E석', 10000);
insert into seat (locationNum, area, price)
values(14,'N석', 10000);
insert into seat (locationNum, area, price)
values(14,'원정석', 0);

insert into seat (locationNum, area, price)
values(15,'3일권', 100000);
insert into seat (locationNum, area, price)
values(15,'1일권', 100000);


insert into seat (locationNum, area, price)
values(16, '공식티켓', 50000);

insert into seat (locationNum, area, price)
values(17,'성인', 10000);
insert into seat (locationNum, area, price)
values(17,'청소년', 5000);
insert into seat (locationNum, area, price)
values(17,'어린이', 0);

insert into seat (locationNum, area, price)
values(18, '성인', 10000);
insert into seat (locationNum, area, price)
values(18, '청소년 및 어린이', 0);


select * from locationNum;