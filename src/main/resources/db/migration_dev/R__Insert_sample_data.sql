DELETE FROM articles;

ALTER TABLE articles AUTO_INCREMENT = 1;

INSERT INTO articles (title, body)
 VALUES('タイトルです1', '1本文です。')
     , ('タイトルです2', '2本文です。')
     , ('タイトルです3', '3本文です。')
 ;

DELETE FROM users;
ALTER TABLE users AUTO_INCREMENT = 1;

-- password is "password00" for all users
INSERT INTO users(id, username, password, enabled)
 VALUES(1, 'user1', '$2a$10$DwLD6kILvgc39Fn/kLQwJusCvM6c8ahzWQ/A/vlFevT1J/DgFHt.m', true)
     , (2, 'user2', '$2a$10$X6u0gPNLz4NFSW88HMa2yuaT/EELyAmKMJwYXfSmXmvG.gsmkNOmq', true)
     , (3, 'user3', '$2a$10$s10PI3QktrlCAv/axoEJbuYkE1bLlAEZr53MkEU0nxPdiBL7JywzK', true)
;