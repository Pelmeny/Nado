CREATE TABLE users
(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

INSERT INTO users (name, password) VALUES ('John', '123456');
INSERT INTO users (name, password) VALUES ('Alex', '123456');
