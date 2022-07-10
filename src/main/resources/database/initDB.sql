CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    username varchar(150) NOT NULL,
    password varchar(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS books(
    id SERIAL PRIMARY KEY,
    title varchar(350) NOT NULL,
    pages integer NOT NULL,
    user_id integer
);

CREATE TABLE IF NOT EXISTS authors(
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS book_authors(
    book_id SERIAL PRIMARY KEY,
    author_id SERIAL PRIMARY KEY
);

