drop database if exists test_db;
create database test_db;
use test_db;

create table product
(
    id      bigint auto_increment primary key,
    product varchar(255) null
);

create table tariff
(
    id         bigint auto_increment primary key,
    tariff     varchar(255) not null,
    product_id bigint       not null,
    price      decimal      null,
    constraint tariffs_ibfk_1
        foreign key (product_id) references product (id)
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
    role       varchar(10)  not null
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

create index service_id
    on tariff (product_id);

create index tariff_id
    on orders (tariff_id);

create index user_id
    on orders (user_id);

INSERT INTO test_db.users (first_name, last_name, email, password, balance, blocked, role)
VALUES ('admin', 'admin', 'admin@mail.com', '$2a$12$NXMOVsS3DoxdLfu7uKX.NeGFdYSPJfnwGQ2F5A17kBwH1Ff8izGoC', 0, 0, 'ROLE_ADMIN');

INSERT INTO test_db.users (first_name, last_name, email, password, balance, blocked, role)
VALUES ('user', 'user', 'user@mail.com', '$2a$12$M7TfQ/SYZ9m8R8GrM58qDe92jXB2GN/SBumcjc92OndLu1ret/sMq', 0, 0, 'ROLE_USER');

INSERT INTO test_db.product (product)
VALUES ('PHONE');
INSERT INTO test_db.product (product)
VALUES ('INTERNET');
INSERT INTO test_db.product (product)
VALUES ('TV');
INSERT INTO test_db.product (product)
VALUES ('IPTV');

