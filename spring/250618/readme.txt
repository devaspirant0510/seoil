##기능에 대한 url
입력폼    /phonebook/insertform
입력처리 /phonebook/insert   
전체출력 /phonebook/list
선택출력 /phonebook/view
수정폼    /phonebook/updateform
수정처리 /phonebook/update
삭제처리 /phonebook/delete

##db설정
drop table phonebook;
create table phonebook(
id number(3), 
name varchar2(50),
hp varchar2(13),
email varchar2(50),
memo varchar2(100)
);
drop sequence pb_id_seq;
create sequence pb_id_seq;
create sequence pb_id_seq start with 1 increment by 1;

***sql문에서 직접입력하여 확인
입력 : insert into phonebook values(pb_id_seq.nextval,'홍길동','010-1111-1111','hong@gmail.com','친구');
전체출력 select * from phonebook;
선택출력 select * from phonebook where id=1;
수정 update phonebook set name='u홍길동', hp='010-1111-2222', email='uhong@gmail.com', memo='u친구' where id=1;
삭제 delete from phonebook where id=1;

##DAO 객체를 작성 후에 JUNIT을 이용하여 테스트 진행










