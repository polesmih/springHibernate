CREATE TABLE shop_user(

    id INT PRIMARY KEY AUTO_INCREMENT,
    login    VARCHAR(40) UNIQUE NOT NULL,
    password VARCHAR(80) NOT NULL,
    email    VARCHAR(80) UNIQUE,
    enabled boolean
);

CREATE TABLE roles(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE users_roles(
    shop_user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (shop_user_id, role_id),
    FOREIGN KEY (shop_user_id) REFERENCES shop_user (id),
    FOREIGN KEY (role_id) references roles (id)
);

INSERT INTO roles (name)
VALUES ('USER'),
       ('ADMIN'),
       ('MANAGER');

INSERT INTO shop_user (login, password, email, enabled)
VALUES
    ('admin', 'a123456', 'admin@gmail.com', true),
    ('manager', 's654321', 'manager@gmail.com', true),
    ('user', 'd147852', 'user@gmail.com', true);