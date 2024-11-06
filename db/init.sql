CREATE TABLE IF NOT EXISTS public.users
(
    id       SERIAL      NOT NULL PRIMARY KEY,
    username VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL NOT NULL,
    token    VARCHAR(60) NOT NULL NOT NULL,
    roles    VARCHAR(100)
);

CREATE TABLE address
(
    id     SERIAL PRIMARY KEY,
    street VARCHAR(100),
    city   VARCHAR(100),
    state  VARCHAR(100)
);

CREATE TABLE clients
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100)        NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    address_id BIGINT,
    createdBy  VARCHAR(100),
    modifiedBy VARCHAR(100),
    createdAt  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (address_id) REFERENCES address (id)
);
