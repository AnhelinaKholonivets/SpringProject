create database testBD;
use testBD;

create table tariff
(
    id    bigint auto_increment primary key,
    name  varchar(50) null,
    price decimal     null
);


create table role
(
    id   bigint      auto_increment primary key,
    name varchar(10) not null
);


create table users
(
    id         bigint auto_increment primary key,
    first_name varchar(50)  null,
    last_name  varchar(50)  null,
    email      varchar(100) not null,
    balance    decimal      null,
    role_id    bigint       not null,
    constraint users_ibfk_1
        foreign key (role_id) references role (id)
);

create table orders
(
    id        bigint auto_increment primary key,
    user_id   bigint   not null,
    tariff_id bigint   not null,
    date_time datetime null,
    constraint orders_ibfk_1
        foreign key (user_id) references users (id),
    constraint orders_ibfk_2
        foreign key (tariff_id) references tariff (id)
);


create index tariff_id
    on orders (tariff_id);

create index role_id
    on users (role_id);

create index user_id
    on orders (user_id);

