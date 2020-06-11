CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, login VARCHAR(20) NOT NULL UNIQUE, password VARCHAR(255));
CREATE TABLE IF NOT EXISTS orders (id BIGINT AUTO_INCREMENT PRIMARY KEY, user_id BIGINT NOT NULL, total_price DECIMAL(18,2));
CREATE TABLE IF NOT EXISTS goods (id BIGINT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), price DECIMAL(18,2));
CREATE TABLE IF NOT EXISTS orders_goods (order_id BIGINT NOT NULL, good_id BIGINT NOT NULL);

ALTER TABLE orders ADD FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE orders_goods ADD FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE orders_goods ADD FOREIGN KEY (good_id) REFERENCES goods(id);


INSERT INTO goods(id, title, price) VALUES (1,'Pen', 1.0);
INSERT INTO goods(id, title, price) VALUES (2,'Table', 99.99);
INSERT INTO goods(id, title, price) VALUES (3,'Mobile Phone', 1499.99);
INSERT INTO goods(id, title, price) VALUES (4,'Doll', 5.2);
INSERT INTO goods(id, title, price) VALUES (5, 'Chair', 20.15);
INSERT INTO goods(id, title, price) VALUES (6,'Sweet', 0.50);