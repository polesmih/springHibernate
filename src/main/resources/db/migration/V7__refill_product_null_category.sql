INSERT INTO category(title) VALUES ('default');

UPDATE product
    SET category_id = (SELECT id FROM category WHERE title = 'default')
WHERE category_id IS NULL;