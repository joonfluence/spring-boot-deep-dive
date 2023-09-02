drop table member if exists cascade;
 create table member (
     id varchar(10),
     money integer not null default 0,
     primary key (id)
 );

 insert into member(id, money) values ('hi1',10000);
 insert into member(id, money) values ('hi2',20000);