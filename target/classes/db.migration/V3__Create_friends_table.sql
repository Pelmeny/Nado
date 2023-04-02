CREATE TABLE friends
(
    id                SERIAL NOT NULL PRIMARY KEY,
    first_friend_id   BIGINT NOT NULL,
    second_friend_id  BIGINT NOT NULL,
    date_at           TIMESTAMP NOT NULL DEFAULT now()
);
