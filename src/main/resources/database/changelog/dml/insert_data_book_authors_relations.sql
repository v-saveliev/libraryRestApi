--liquibase formatted sql
--changeset savelev:insert-book-authors-relations

INSERT INTO book_authors VALUES
(1, 2),
(2, 1),
(3, 4),
(4, 8),
(5, 3),
(6, 5),
(6, 6),
(6, 7);