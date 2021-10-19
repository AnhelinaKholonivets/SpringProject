drop database if exists test_db;
create database test_db;
use test_db;

create table tariff
(
    id    bigint auto_increment primary key,
    tariff  varchar(255) null,
    price decimal      null
);

create table users
(
    id         bigint auto_increment primary key,
    first_name varchar(50)  null,
    last_name  varchar(50)  null,
    email      varchar(255) not null,
    password   varchar(255) not null,
    balance    decimal      null,
    blocked    boolean      not null,
    role    varchar(10)       not null
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

create index user_id
    on orders (user_id);

INSERT INTO test_db.users (first_name, last_name, email, password, balance, blocked, role)
VALUES ('admin', 'admin', 'admin@mail.com', 'adminp', 0, 0, 'ADMIN');

INSERT INTO test_db.users (first_name, last_name, email, password, balance, blocked, role)
VALUES ('user', 'user', 'user@mail.com', 'userp', 0, 0, 'USER');


