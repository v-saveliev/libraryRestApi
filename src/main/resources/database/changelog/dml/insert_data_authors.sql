--liquibase formatted sql
--changeset savelev:insert-authors

INSERT INTO authors VALUES
(1, 'F. Scott Fitzgerald'),
(2, 'Miguel de Cervantes'),
(3, 'Homer'),
(4, 'Leo Tolstoy'),
(5, 'Len Bass'),
(6, 'Paul Clements'),
(7, 'Ken Bass'),
(8, 'Fedor Dostoyevsky');