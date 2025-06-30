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
VALUES('user1', '$2a$10$erfkGASk39pvoROpqRQpYOYRZPTUmrzH2kwOd87qOsybHc6z/qTOO', true)
    , ('user2', '$2a$10$UJwukoNt9bL1qrgWMAhtxu3uOvPREcqErA1i9XW0L3Hap.EsjGtmK', true)
    , ('user3', '$2a$10$JVCYg66IqWhLlQ.WtrB.FeBaPL6CorE4Dsgp3/FaQ9iOJilfAxwEO', true)
;