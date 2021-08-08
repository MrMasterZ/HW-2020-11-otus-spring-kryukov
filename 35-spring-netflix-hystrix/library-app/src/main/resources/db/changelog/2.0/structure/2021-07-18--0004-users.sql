--liquibase formatted sql

--changeset mrmasterz:2021-07-18-004-users
CREATE TABLE USERS(
    USER_ID BIGSERIAL PRIMARY KEY,
    USERNAME VARCHAR(255),
    PASSWORD VARCHAR(255),
    ROLES VARCHAR(255)
    );