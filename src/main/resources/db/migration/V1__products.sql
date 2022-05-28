CREATE TABLE if NOT EXISTS products(
    id serial primary key,
    title varchar(45),
    price int,
    category_id integer REFERENCES categories(id),
    customer_id integer REFERENCES customers(id)
);

INSERT INTO products (id, title, price) VALUES
    (1, 'арбуз', 100),
    (2, 'клюква', 503),
    (3, 'персик', 75),
    (4, 'лук', 10),
    (5, 'укроп', 5),
    (6, 'баклажан', 45),
    (7, 'хурма', 83),
    (8, 'черника', 65),
    (9, 'малина', 35),
    (10, 'клубника', 24),
    (11, 'виноград', 68),
    (12, 'чеснок', 3),
    (13, 'петрушка', 3),
    (14, 'огурцы', 7),
    (15, 'помидоры', 8),
    (16, 'кинза', 3),
    (17, 'бананы', 30),
    (18, 'яблоки', 25),
    (19, 'груши', 35),
    (20, 'картофель', 17);



