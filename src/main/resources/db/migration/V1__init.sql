drop table if exists products;
create table products
(
    id          serial primary key,
    title       varchar(255) UNIQUE NOT NULL,
    description TEXT,
    price       BIGINT

);



insert into products(title, description, price)
values ('MacBook', 'Retina display. 13.3" LED backlit display with IPS technology', 100000),
       ('iPhone', 'Incredibly durable Ceramic Shield front panel.', 50000),
       ('iPad', ' 2D lighting system', 30000),
       ('iMac', ' Convenient and functional mono-block computer', 80000);

-- тестовая таблица

drop table if exists items;
create table items
(
    id    serial,
    title varchar(40)

);
insert into items (title)
values ('stone'),
       ('knife'),
       ('spoon');