CREATE TABLE shop_user(
    id       INT,
    login    VARCHAR(40) UNIQUE NOT NULL,
    password VARCHAR(80) NOT NULL,
    email    VARCHAR(80) UNIQUE,
    enabled boolean,
    PRIMARY KEY (id)
);

CREATE TABLE roles(
    id   serial,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles(
    shop_user_id bigint NOT NULL,
    role_id INT    NOT NULL,
    PRIMARY KEY (shop_user_id, role_id),
    FOREIGN KEY (shop_user_id) REFERENCES shop_user (id),
    FOREIGN KEY (role_id) references roles (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_MANAGER');

INSERT INTO shop_user (login, password, email, enabled)
VALUES
    ('admin', '$2a$10$FjepnPniBgHYSTdMCXEFWuHq3oXM3hZuCQCsmgERzEnc/eBC88sGe', 'admin@gmail.com', true),
    ('manager', '$2a$10$FjepnPniBgHYSTdMCXEFWuHq3oXM3hZuCQCsmgERzEnc/eBC88sGe', 'manager@gmail.com', true),
    ('user', '$2a$10$FjepnPniBgHYSTdMCXEFWuHq3oXM3hZuCQCsmgERzEnc/eBC88sGe', 'user@gmail.com', true);