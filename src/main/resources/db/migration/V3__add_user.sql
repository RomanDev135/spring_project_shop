drop table if exists users;
create table users
(
    id       serial primary key,
    login    varchar(255) unique NOT NULL,
    password varchar(255) unique NOT NULL

);

insert into users(login, password)
values ('login1', 'pass1'),
       ('login2', 'pass2'),
       ('login3', 'pass3');
