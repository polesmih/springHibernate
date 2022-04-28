CREATE TABLE if NOT EXISTS customers(
    id bigint primary key,
    name varchar(255)
);

INSERT INTO customers (id, name) VALUES
    (1, 'Иванов Иван'),
    (2, 'Примакова Елена'),
    (3, 'Гусаров Степан');
