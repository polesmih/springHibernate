ALTER TABLE category ADD COLUMN parent_category_id INT;
ALTER TABLE category ADD CONSTRAINT category_parent_fk FOREIGN KEY (parent_category_id) REFERENCES category (id);
