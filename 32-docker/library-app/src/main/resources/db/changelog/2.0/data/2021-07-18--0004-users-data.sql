--liquibase formatted sql

--changeset mrmasterz:2021-07-18--0004-users-data
--roles must be separated by spaces
insert into users (username, password, roles)
values ('Sergey', 'pass', 'ROLE_USER'),
       ('test', 'test', 'ROLE_USER'),
       ('admin', 'admin', 'ROLE_USER ROLE_ADMIN');