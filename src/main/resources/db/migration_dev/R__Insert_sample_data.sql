DELETE FROM users;
ALTER TABLE users AUTO_INCREMENT = 1;
-- password is "password00" for all users
INSERT INTO users(id, username, password, enabled)
VALUES(1, 'user1', '$2a$10$X8HIgdQhvXCIPgjs9d30.uIcSl.SFcQG4NyS2T16hHObwrVqNwvM.', true)
    , (2, 'user2', '$2a$10$50hpqa9j2C4w9WCEqLqtIuAYINisV17QJw8l48ptdrdYOSTKZBoTm', true)
    , (3, 'user3', '$2a$10$6mgrLLqxkaoGgttg7JRxk.Gc2E8o5.aY2bRjCTy1dzMNUOAy0gVze', true)
;

DELETE FROM articles;
ALTER TABLE articles AUTO_INCREMENT = 1;

INSERT INTO articles (title, body, user_id)
VALUES('タイトルです1', '1本文です。', 1)
    , ('タイトルです2', '2本文です。', 1)
    , ('タイトルです3', '3本文です。', 2)
;