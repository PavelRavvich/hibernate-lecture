-- CREATE DATABASE hibernate_lecture ENCODING 'utf8';

CREATE TABLE IF NOT EXISTS customer
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id          BIGINT PRIMARY KEY,
    time        TIMESTAMP           NOT NULL DEFAULT now(),
    amount      NUMERIC(10, 2) UNIQUE NOT NULL,
    customer_id BIGINT REFERENCES customer (id)
);
