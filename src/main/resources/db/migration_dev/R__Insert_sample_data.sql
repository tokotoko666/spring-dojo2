DELETE FROM articles;

ALTER TABLE articles AUTO_INCREMENT = 1;

INSERT INTO articles (title, body)
 VALUES('タイトルです1', '1本文です。')
     , ('タイトルです2', '2本文です。')
     , ('タイトルです3', '3本文です。')
 ;

DELETE FROM users;

-- password is "password" for all users
INSERT INTO users(username, password, enabled)
 VALUES('user1', '$2a$10$F5JP83Nje/nst4yLgRTRVeseBIYitiDS71TDNIK5CZ6wEm6byWYTa', true)
     , ('user2', '$2a$10$JctG3h1.BTvVLvU26r0mzeYENPNYcMts/y5U.1I028hwmqZsO18si', true)
     , ('user3', '$2a$10$R5roJSqLKSEPGXFsYqoAUuBq/8xe0KpVUzbMWWjN4Th54/K9I8lfC', true)
;