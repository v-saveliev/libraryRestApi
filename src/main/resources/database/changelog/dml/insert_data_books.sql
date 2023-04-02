--liquibase formatted sql
--changeset savelev:insert-books

INSERT INTO books VALUES
(1, 'Don Quixote', 940, 2),
(2, 'The Great Gatsby', 216, 2),
(3, 'War and peace', 1424, 3),
(4, 'Crime and Punishment', 800, 4),
(5, 'The Oydessey', 1000, 3),
(6, 'Software Architecture in Practice', 1500, 3);