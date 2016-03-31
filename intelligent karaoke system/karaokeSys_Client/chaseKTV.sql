drop database if exists `chaseKTV`;
create database `chaseKTV`;
use `chaseKTV`;

create table `Singers`(
  `name` varchar(20) NOT NULL,
  `nationality` varchar(20) NOT NULL,
  `gender` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
);

create table `Songs`(
	`title` varchar(50) not null,
    `language` varchar(20) not null,
    `path` varchar(200) not null,
    `Singer` varchar(20) not null,
    primary key (`title`)
);

create table `User`(
	`username` varchar(20) not null,
    `password` varchar(20) not null,
    primary key (`username`)
)