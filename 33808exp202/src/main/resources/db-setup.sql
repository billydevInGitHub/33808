--The script here is for HSQLDB or In Memeory database
--For JDBC database, need manually run the following script

DROP TABLE IF EXISTS `class`;
create table class (
id int(11) not null auto_increment,
name varchar(50) default null ,
primary key (id)
);

select * from class;


DROP TABLE IF EXISTS `student`;
create table student (
id int(11) not null auto_increment,
name varchar(50) not null ,
class_id int(11) not null ,
age int(11) not null ,
number varchar(50) not null ,
primary key (id)
);

insert into class (id, name) values (1, 'class1');

insert into student (name, class_id, age, number) values('student1',1,10,'s1');

insert into student (name, class_id, age, number) values('student2',1,12,'s2');


drop table if exists users;

create table  users(
username varchar(255),
password varchar(255),
enabled int(10));

drop table if exists authorities;

create table authorities(
username varchar(255),
authority varchar(255));