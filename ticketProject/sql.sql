select * from member;


select * from banner;

select * from content;
select * from admin;
select * from worker;

GRANT SELECT, DELETE, INSERT, UPDATE ON admin TO scott;

create or replace procedure getMember(
    p_id 
)

is
begin

end;

CREATE TABLE worker
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	phone varchar2(20) NOT NULL,
	PRIMARY KEY (id)
);

insert into worker values('admin', 'admin', '관리자', '010-7777-7777');
insert into worker values('scott', 'tiger', '홍길동', '010-6400-6068');