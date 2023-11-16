
create database shopee;
use shopee;

create table role
(
    id int auto_increment primary key,
    name varchar(100) not null
);

create table user
(
    id int auto_increment primary key,
    name varchar(50) not null,
    email varchar(50) unique not null,
    phone_number varchar(50) unique  not null,
    address varchar(100),
    username varchar(50) unique not null,
    password varchar(100) not null
);


create table user_role
(
    id int auto_increment primary key,
    user_id int,
    role_id int,
    foreign key (user_id) references user(id),
    foreign key (role_id) references role(id)
);

insert into user(id, name, email, phone_number, address, username, password)
values (1, 'Lê Văn Sỹ', 'levansy25012003@gmail.com', '0942896469', 'Ái tử, Quảng Trị', 'levansyadmin', '$2a$12$oahKdMcmi7nGJyNAVMDWW.LsraHzNA4SH.thQjAtBoMd4BO4N6v8a'),
       (2, 'Nguyễn Ngọc Quang', 'ngocquang@gmail.com', '0945362767', 'Ái tử, Quảng Trị', 'ngocquanguser', '$2a$12$4TmqhTKWp56oIplhRzCc3eh/Hly2d04VMMLZwPrQJ0rrIx1sznQ8G'),
       (3, 'Trần Phi Long', 'philong@gmail.com', '0942896467', 'Ái tử, Quảng Trị', 'philonguser', '$2a$12$/ttMeSdMjC9pNYNdHC/C0.acNy8/UcA0CavjFPNqLLH95WObpJul6');

insert into role(id, name)
values  (1, 'admin'),
        (2, 'user');
insert into user_role(user_id, role_id)
values  (1, 1),
        (2, 2),
        (3, 2);

create table category
(
    id int auto_increment primary key,
    name varchar(100) not null
);

create table product
(
    id int auto_increment primary key,
    title varchar(100),
    price int,
    discount int,
    quantity int,
    image longtext,
    description text,
    category_id int,
    foreign key (category_id) references category(id)
);

create table card_product
(
    id int auto_increment primary key,
    product_id int,
    user_id int,
    foreign key (user_id) references user(id),
    foreign key (product_id) references product(id)
);

alter table card_product add quantity int;

create table oder
(
    id int auto_increment primary key,
    note varchar(100),
    oder_date date,
    user_id int,
    foreign key (user_id) references user(id)
);

create table oder_detail
(
    id int auto_increment primary key,
    price int,
    quantity int,
    oder_id int,
    product_id int,

    foreign key (oder_id) references oder(id),
    foreign key (product_id) references product(id)
);

insert into category(name) values ('Điện thoại');
insert into category(name) values ('Máy tính');
insert into category(name) values ('Đồ ăn');
insert into category(name) values ('Vé máy bay');


insert into product(title, price, discount, quantity, image, description, category_id)
    value (?, ?, ?, ?, ?, ?, ?);

update product set title = ?, price = ?, discount = ?, quantity = ?, image = ?, description = ?, category_id = ?
where  product.id = ?;

delete from product where product.id = ?;

select * from product;

select * from card_product;
insert into card_product(product_id, user_id, quantity) values (?, ?, ?);

select p.id, p.title, p.price, p.discount, p.quantity, p.image, p.description, p.category_id, c.name as 'category_name'
from product p inner join category c on p.category_id = c.id;

select  r.name as role
from user u inner join user_role ur on u.id = ur.user_id
            inner join role r on ur.role_id = r.id
where u.username = ?;

select id, name, email, phone_number, address, username, password
from user where  username = ?;

select p.id, p.title, p.price, p.discount, p.quantity, p.image, p.description, p.category_id, c.name
from product p inner join category c on p.category_id = c.id where p.id = ?;

delete from card_product where id = 1;

select
    p.id, p.title, p.price, p.discount, p.quantity, p.image, p.description, p.category_id
from
    product p
        inner join
    category c
    on p.category_id = c.id
where p.title like concat('%', ?, '%')
  and p.discount >= 5000000
  and c.id = ?;



select
    p.id, p.title, p.price, p.discount, p.quantity, p.image, p.description, p.category_id, c.name
from
    product p
        inner join
    category c
    on p.category_id = c.id
where c.name like concat('%', ? , '%');

select count(*) from product;

select
    p.id, p.title, p.price, p.discount, p.quantity, p.image, p.description, p.category_id
from
    product p
        inner join
    category c
    on p.category_id = c.id
where p.title like concat('%', ?, '%') and c.id = ?


# SELECT * FROM Product p WHERE p.title LIKE CONCAT('%', :ten, '%')
#              and (:categoryIds is null or p.category_id IN (:categoryIds))