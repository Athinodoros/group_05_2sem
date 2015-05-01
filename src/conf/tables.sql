alter session set nls_date_format = 'yyyy-mm-dd';


drop table budget;
drop table POE;
drop table comments;
drop table Project;
drop table userAthentication;
drop table userInfo;
drop table partner;
drop table dell;

DROP SEQUENCE POESequence; 
DROP SEQUENCE commentSequence; 
DROP SEQUENCE projectSequence; 
DROP SEQUENCE UserInfoSequence;

create table budget(
quarter integer,
qyear integer,
qbudget integer,
primary key (quarter, qyear));

create table partner (
companyName varchar(30) primary key,
companyID integer);

INSERT INTO PARTNER VALUES ('Athinodoros CORP', '1') ;

create table dell (
companyName varchar(30) primary key ,
companyID integer);

create table userInfo (
userID integer primary key ,
firstname varchar(20),
lastname varchar(20),
country  varchar(4),
-- companyName varchar(30) references partner(companyName) references dell(companyName) ,
companyName varchar(30) references partner(companyName) ,
urole varchar(20));

INSERT INTO USERINFO VALUES ( 1,'Athinodoros','Sgouromallis','DK','Athinodoros CORP','admin');
INSERT INTO USERINFO VALUES ( 83,'admin','admin','DK','Athinodoros CORP','admin');

create table userAthentication (
--userID integer primary key references userInfo(userID),
--uname     varchar(30),
uname   varchar(30) primary key,
userID integer references userInfo(userID),
password     varchar(32),
email     varchar(50));

INSERT INTO USERATHENTICATION VALUES ( 'admin',83,'admin','athinodoros.sgouromallis@hotmail.com');
INSERT INTO USERATHENTICATION VALUES ( 'Athinodoros',1,'Athinodoros','athinodoros.sgouromallis@hotmail.com');

create table project (
projectID integer primary key,
-- companyName varchar(30) references Partner(companyName) references dell(companyName),
companyName varchar(30) references Partner(companyName),
title varchar(40),
description varchar(1200),
stage varchar(40),
sdate date,
fdate date,
projectBudget integer);

INSERT INTO PROJECT VALUES ( 1,'Athinodoros CORP','Title','This is a description','preaprooved',null,null,13333);

create table POE (
POEID integer primary key,
projectID integer references Project(projectID),
prefixs varchar(18),
fileName varchar(18),
fileBin BLOB );

create table comments (
commentID integer primary key,
projectID integer references project(projectID),
userID integer,
comments varchar(2000));

CREATE SEQUENCE projectSequence
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1000
  INCREMENT BY 1
  CACHE 20;

CREATE SEQUENCE UserInfoSequence
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1000
  INCREMENT BY 1
  CACHE 20;

CREATE SEQUENCE commentSequence
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1000
  INCREMENT BY 1
  CACHE 20;

CREATE SEQUENCE POESequence
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1000
  INCREMENT BY 1
  CACHE 20;

commit;