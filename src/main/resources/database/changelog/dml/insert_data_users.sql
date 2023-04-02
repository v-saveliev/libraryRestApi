--liquibase formatted sql
--changeset savelev:insert-users

INSERT INTO users VALUES
    (1, 'default', ''),
    (2, 'king1555', '5551'),
    (3, 'user886', 'qwerty'),
    (4, 'vp_reader', '1q2w3e');