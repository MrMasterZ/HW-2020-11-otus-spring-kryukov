--liquibase formatted sql

--changeset mrmasterz:2021-01-16--0003-books-data
insert into books (title, author_id, genre_id)
values ('Prelude to the foundation', 1, 1),
       ('Martian path', 1, 1),
       ('Space hospital', 2, 1),
       ('Seeded moon', 3, 1),
       ('Battle for suit', 4, 2),
       ('Breath of crisis', 5, 2),
       ('Ruslan and Lyudmila', 6, 3),
       ('Poltava', 6, 3),
       ('The tale of tsar Saltan', 6, 4),
       ('A tale of a fisherman and a fish', 6, 4),
       ('Dubrovsky', 6, 5),
       ('Boris Godunov', 6, 6),
       ('fiddle while Rome burns', 6, 6),
       ('About eternal peace', 6, 7),
       ('Russia and England in Central Asia', 7, 8)