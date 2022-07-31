create database HangmanDB;
use Hangmandb;

CREATE TABLE `passwords`(
    `id`       INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `password` VARCHAR(250) NOT NULL);

insert into `passwords` (`password`)
values ('deloitte'),
       ('slowo');