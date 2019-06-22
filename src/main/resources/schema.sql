create table roles (
id number(1000) primary key, 
name varchar(60)
);


create table users (
id number(1000) primary key, 
name varchar(40),
username varchar(15),
email varchar(40),
password varchar(100),
createdAt timestamp,
updatedAt timestamp
);


create table user_roles (
user_id number(1000), 
role_id number(1000)
);


insert into roles (id, name) values (1, 'ROLE_USER');
insert into roles (id, name) values (2, 'ROLE_ADMIN');

insert into users (id, name, username, email, password, createdAt, updatedAt)
values
(1, 'admin', 'admin', 'admin@abc.com', '$2a$10$Z3ol96L5tvorSuPRLRYdpuyhkGsoHKYYhX27aZtPKuC7MvJMBtA2a', '2019-01-01', '2019-01-01');

insert into users (id, name, username, email, password, createdAt, updatedAt)
values
(2, 'admin2', 'admin2', 'admin2@abc.com', '$2a$10$Z3ol96L5tvorSuPRLRYdpuyhkGsoHKYYhX27aZtPKuC7MvJMBtA2a', '2019-01-01', '2019-01-01');

insert into user_roles (user_id, role_id)
values
(1, 1);

insert into user_roles (user_id, role_id)
values
(2, 2);
