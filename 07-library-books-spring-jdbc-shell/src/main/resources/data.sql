insert into authors (author) values ('Isaac Asimov');
insert into authors (author) values ('James White');
insert into authors (author) values ('Connie Willis');
insert into authors (author) values ('Alexander Belov');
insert into authors (author) values ('Evgeny Kukarkin');
insert into authors (author) values ('Alexander Pushkin');
insert into authors (author) values ('Terentyev Mikhail');

insert into genres (genre) values ('fantasy');
insert into genres (genre) values ('detective story');
insert into genres (genre) values ('poem');
insert into genres (genre) values ('fairy tale');
insert into genres (genre) values ('prose');
insert into genres (genre) values ('drama');
insert into genres (genre) values ('journalism');
insert into genres (genre) values ('history');

insert into books (title, author_id, genre_id) values ('Prelude to the foundation', 1, 1);
insert into books (title, author_id, genre_id) values ('Martian path', 1, 1);
insert into books (title, author_id, genre_id) values ('Space hospital', 2, 1);
insert into books (title, author_id, genre_id) values ('Seeded moon', 3, 1);
insert into books (title, author_id, genre_id) values ('Battle for suit', 4, 2);
insert into books (title, author_id, genre_id) values ('Breath of crisis', 5, 2);
insert into books (title, author_id, genre_id) values ('Ruslan and Lyudmila', 6, 3);
insert into books (title, author_id, genre_id) values ('Poltava', 6, 3);
insert into books (title, author_id, genre_id) values ('The tale of tsar Saltan', 6, 4);
insert into books (title, author_id, genre_id) values ('A tale of a fisherman and a fish', 6, 4);
insert into books (title, author_id, genre_id) values ('Dubrovsky', 6, 5);
insert into books (title, author_id, genre_id) values ('Boris Godunov', 6, 6);
insert into books (title, author_id, genre_id) values ('fiddle while Rome burns', 6, 6);
insert into books (title, author_id, genre_id) values ('About eternal peace', 6, 7);
insert into books (title, author_id, genre_id) values ('Russia and England in Central Asia', 7, 8);