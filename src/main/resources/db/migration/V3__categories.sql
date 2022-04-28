CREATE TABLE if NOT EXISTS categories(
    id bigint primary key,
    title varchar(45)
);

INSERT INTO categories (id, title) VALUES
    (1, 'овощи'),
    (2, 'фрукты'),
    (3, 'ягоды'),
    (4, 'зелень');