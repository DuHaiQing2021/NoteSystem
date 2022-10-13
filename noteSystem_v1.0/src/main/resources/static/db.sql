-- 编写建库建表的sql
create database if not exists notesystem;

use notesystem;

-- 创建一个博客表
drop table if exists notes;
create table notes(
    noteId int primary key auto_increment,
    title varchar(1024),
    content mediumtext,
    userId int,
    postTime datetime,
    rcount int default 1,
    `state` int default 1
);
-- 给博客表插入数据方便测试
insert into notes(noteId,title,content,userId,postTime) value(null,"这是第一篇笔记","从今天起,开始认真学Java",1,now());
insert into notes(noteId,title,content,userId,postTime)  value(null,"这是第二篇笔记","从昨天起,开始认真学Java",1,now());
insert into notes(noteId,title,content,userId,postTime)  value(null,"这是第三篇笔记","从前天起,开始认真学Java",1,now());
insert into notes(noteId,title,content,userId,postTime)  value(null,"这是第一篇笔记","从今天起,开始认真学Java",4,now());
insert into notes(noteId,title,content,userId,postTime)  value(null,'这是第二篇笔记','#一级标题\n###三级标题\n>这是引用内容',2,now());


-- 创建一个用户表
drop table if exists user;
create table user(
    userId int primary key auto_increment,
    username varchar(128) unique,
    password varchar(128),
    `state` int default 1
);
insert into user value(null,"张三","123456",1);
insert into user value(null,"李四","123456",1);
insert into user value(null,"王五","123456",1);
insert into user value(null,"杜海庆","123456",1);
