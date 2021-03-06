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

create table userInfo (
userID integer primary key ,
firstname varchar(20),
lastname varchar(20),
country  varchar(4),
companyName varchar(30) references partner(companyName) ,
urole varchar(20));

create table userAthentication (
uname   varchar(30) primary key,
userID integer references userInfo(userID),
password     varchar(32),
email     varchar(50));


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

create table POE (
POEID integer primary key,
projectID integer references Project(projectID),
prefixs varchar(18),
fileName varchar(18),
fileBin BLOB );

create table comments (
commentID integer primary key,
projectID integer references project(projectID),
userID integer references userInfo(userID),
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