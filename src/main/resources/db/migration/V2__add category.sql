CREATE TABLE if NOT EXISTS category(
    id serial primary key,
    title varchar(45)
);


INSERT INTO category  (id, title) VALUES
    (1, 'овощи'),
    (2, 'фрукты'),
    (3, 'ягоды'),
    (4, 'зелень');

ALTER TABLE product ADD COLUMN category_id INT;
ALTER TABLE product ADD CONSTRAINT product_category FOREIGN KEY (category_id) REFERENCES category (id);
