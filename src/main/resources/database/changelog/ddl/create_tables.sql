--liquibase formatted sql
--changeset savelev:create-tables

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username varchar(150) NOT NULL,
    password varchar(255) NOT NULL
);

CREATE TABLE books(
    id SERIAL PRIMARY KEY,
    title varchar(350) NOT NULL,
    pages integer,
    user_id integer
);

CREATE TABLE authors(
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    info jsonb
);

CREATE TABLE book_authors(
   book_id integer REFERENCES books (id) ON UPDATE CASCADE,
   author_id int REFERENCES authors (id) ON UPDATE CASCADE
);

