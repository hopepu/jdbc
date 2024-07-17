drop table board;
-- before board delete

create table board(
	bno number(15) primary key,
	btitle nvarchar2(30) not null,
	bcontent nvarchar2(1000) not null,
	bwriter nvarchar2(10) not null,
	bdate date not null);
-- board table create

drop sequence board_seq;
-- sequence board_seq delete
create sequence board_seq increment by 1 start with 1 nocycle nocache;
-- create sequecne start 1++

insert into board(bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '비오네요~', '비오는데 등교하시느라고 고생 하셨습니다.', '김기원', sysdate);

insert into board(bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '안녕하세요~', '비오는데 등교하시느라고 고생 하셨습니다.', '김기원', sysdate);

insert into board(bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '감사합니다~', '비오는데 등교하시느라고 고생 하셨습니다.', '김기원', sysdate);

insert into board(bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '수고하셨네요~', '비오는데 등교하시느라고 고생 하셨습니다.', '김기원', sysdate);
	
insert into board(bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '화이팅하세요~', '비오는데 등교하시느라고 고생 하셨습니다.', '김기원', sysdate);
	
insert into board(bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '반갑습니다~', '비오는데 등교하시느라고 고생 하셨습니다.', '김기원', sysdate);
	
select * from board;

update board set btitle = '비가오네요~' where bno=2 ;

alter table board rename to tableTBL;

--member table 
create table memberTBL(
	mno number(5) primary key,
	mid nvarchar2(10) unique not null,
	mpw	nvarchar2(10) not null,
	mdate date not null
);

drop table memberTBL;

insert into memberTBL(mno, mid, mpw, mdate)
	values(board_seq.nextval, '이영훈', '1234', sysdate);
	
insert into memberTBL(mno, mid, mpw, mdate)
	values(board_seq.nextval, '문지현', '1234', sysdate);
	
insert into memberTBL(mno, mid, mpw, mdate)
	values(board_seq.nextval, '조용재', '1234', sysdate);
	
insert into memberTBL(mno, mid, mpw, mdate)
	values(board_seq.nextval, '김지선', '1234', sysdate);
	
insert into memberTBL(mno, mid, mpw, mdate)
	values(board_seq.nextval, '함시은', '1234', sysdate);

select * from memberTBL;

select * from memberTBL where mid= '조용재';

alter table memberTBL modify(mid unique);
select * from ;

create table DmemberTBL(
	did nvarchar2(10), 
	dpw	nvarchar2(10),
	ddate date
);
drop table DmemberTBL;

insert into memberTBL(mno, mid, mpw, mdate)
	values(board_seq.nextval, '1', '1234', sysdate);


 create trigger trigger_member
 	after delete on memberTBL
 	for each row
  begin
	insert into DmemberTBL(did, dpw, ddate)
 		values(:old.mid, :old.mpw, sysdate());
 end;		
 
 	
 drop trigger trigger_member;
 	
 	select * from memberTBL;
 	
 	delete from memberTBL where mid = '1';
 	
 	select * from DmemberTBL;
 	
 	select * from user_triggers;
