create tablespace board datafile 'c:\board.dbf' size 20m
-- 10-3 db생성
create user boardtest idendified by boardtest;
-- id boardtest / pw boardtest

grant connect, resource to boardtest;
-- boardtest 계정에게 접속 권한, 일반사용자 권한 부여

alter user boardtest default tablespace board;
-- boardtest 계정에 기본 데이터베이스를  board로 지정

alter user boardtest temporary tablespace temp;
-- boardtest 계정에 임시 데이터베이스 배정



 