CREATE TABLE requests
(
    id           SERIAL NOT NULL PRIMARY KEY,
    sender_id    BIGINT NOT NULL,
    recipient_id BIGINT NOT NULL,
    date_at      TIMESTAMP NOT NULL DEFAULT now()
);
