create database if not exists finalprojectpwtj;

create table if not exists addresses(
    id bigint not null auto_increment primary key,
    country varchar(50) not null,
    district varchar(50) not null,
    city varchar(50) not null,
    street varchar(50) not null,
    nr int not null,
    building varchar(10) null,
    entrance varchar(10) null,
    floor int null,
    apartment int null,
    interphone varchar(10) null,
    postal_code varchar(10) not null
);

create table if not exists users(
    id bigint not null auto_increment primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    username varchar(50) not null,
    userpass varchar(50) not null,
    email varchar(255) not null,
    phone_nr varchar(10) not null,
    address_id bigint null,
    unique (username),
    unique (email),
    unique (phone_nr),
    foreign key (address_id) references addresses (id)
    on update cascade
    on delete cascade
);

create table if not exists products(
    id bigint not null auto_increment primary key,
    product_name varchar(50) not null,
    price decimal(10, 2) not null,
    quantity int not null,
    product_desc varchar(1000) null,
    product_status varchar(20) not null,
    unique (product_name)
);

create table if not exists consoles(
    id bigint not null auto_increment primary key,
    product_id bigint not null,
    edition varchar(20) not null,
    spec_gpu varchar(150) not null,
    spec_cpu varchar(110) not null,
    spec_memory varchar(110) not null,
    spec_storage varchar(110) not null,
    spec_sound varchar(110) not null,
    spec_os varchar(110) not null,
    spec_media varchar(110) not null,
    color varchar(50) not null,
    foreign key (product_id) references products (id)
    on update cascade
    on delete cascade
);

create table if not exists games(
    id bigint not null auto_increment primary key,
    product_id bigint not null,
    platform varchar(20) not null,
    edition varchar(20) not null,
    genre varchar(30) not null,
    game_mode varchar(100) not null,
    publisher varchar(100) not null,
    developer varchar(100) not null,
    foreign key (product_id) references products (id)
    on update cascade
    on delete cascade
);

create table if not exists accessories(
    id bigint not null auto_increment primary key,
    product_id bigint not null,
    acc_type varchar(50) not null,
    brand varchar(50) not null,
    compatibility varchar(50) not null,
    color varchar(50) not null,
    foreign key (product_id) references products (id)
    on update cascade
    on delete cascade
);

insert into users (first_name, last_name, username, userpass, email, phone_nr)
select 'George-Vlad', 'Ene', 'adminPSM', 'adminPSM', 'admin@psm.com', '0123456789' from dual
where not exists (select * from users);

insert into addresses (country, district, city, street, nr, postal_code)
select 'Romania', 'Prahova', 'Ploiesti', 'Strada Mare', 1, '100420' from dual
where not exists (select * from addresses);

update users
set address_id = if (address_id is null, 1, address_id)
where username = 'admin';
