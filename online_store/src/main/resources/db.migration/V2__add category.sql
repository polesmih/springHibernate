CREATE TABLE category(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(45)
);


ALTER TABLE product ADD COLUMN category_id INT;
ALTER TABLE product ADD CONSTRAINT product_category FOREIGN KEY (category_id) REFERENCES category (id);
