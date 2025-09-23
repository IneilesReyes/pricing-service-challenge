-- Insert data into Brand table (no id values provided, they will auto-generate)
INSERT INTO brand (name) VALUES
                             ('ZARA'),
                             ('BERSHKA'),
                             ('PULL&BEAR');

INSERT INTO product(id, name, category) VALUES (35455, 'Jeans A', 'Clothing');
ALTER TABLE product ALTER COLUMN id RESTART WITH 35456;
INSERT INTO product (name, category) VALUES
                                         ('Jeans B', 'Clothing'),
                                         ('Jeans C', 'Clothing'),
                                         ('Shirt A', 'Clothing'),
                                         ('Shirt B', 'Clothing'),
                                         ('Shirt C', 'Clothing'),
                                         ('Sneakers A', 'Footwear'),
                                         ('Sneakers B', 'Footwear'),
                                         ('Sneakers C', 'Footwear'),
                                         ('Bag A', 'Accessories'),
                                         ('Bag B', 'Accessories'),
                                         ('Bag C', 'Accessories');

-- Insert data into Price table (foreign key relations are based on the order of products and brands)


INSERT INTO price (price, start_date, end_date, priority, product_id, brand_id, currency_code) VALUES
                                                                                                   (35.50, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35455, 1, 'EUR'),
                                                                                                   (25.45, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 35455, 1, 'EUR'),
                                                                                                   (30.50, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 35455, 1, 'EUR'),
                                                                                                   (38.95, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 35455, 1, 'EUR');
