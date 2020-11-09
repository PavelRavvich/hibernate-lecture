-- CREATE DATABASE hibernate_lecture ENCODING 'utf8';

CREATE TABLE IF NOT EXISTS customer
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age  INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id          BIGINT PRIMARY KEY,
    price       NUMERIC(10, 2) NOT NULL,
    customer_id BIGINT REFERENCES customer (id)
);
