drop table budget;
drop TABLE POE;
drop TABLE comments;
drop table Project;
--drop table partners;
drop table dell;
drop table userAthentication;
drop table userInfo;

create table budget(
quarter integer,
qyear integer,
budget integer );

create table userInfo (
userID integer primary key ,
firstname varchar(20),
lastname varchar(20),
country  varchar(4),
urole varchar(20));

create table userAthentication (
userID integer primary key references userInfo(userID),
uname     varchar(30),
password     varchar(32),
email     varchar(30));

create table POE (
projectID integer,
filrPath varchar(50)
);

create table project (
projectID integer primary key references POE(projectID),
companyName varchar(30),
title varchar(40),
description varchar(1200),
stage integer,
sdate date,
fdate date,
projectBudget integer);

create table partner (
companyName varchar(30) primary key references project(companyName),
companyID integer);

create table dell (
companyName varchar(30) primary key references project(companyName),
companyID integer);



create table comments(
commentID integer,
projectID integer,
userID integer,
comment varchar (max)
);

commit;