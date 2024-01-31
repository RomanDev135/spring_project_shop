drop table if exists users_products;
create table users_products
(
    id         serial primary key,
    user_id    bigint,
    product_id bigint
);

insert into users_products(user_id, product_id)
values (1, 2),
       (2, 1),
       (2, 2),
       (3, 5);