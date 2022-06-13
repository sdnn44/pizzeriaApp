INSERT INTO sizes(name,diameter) VALUES ('Mała',32);
INSERT INTO sizes(name,diameter) VALUES ('Duża',42);
INSERT INTO sizes(name,diameter) VALUES ('Średnia',52);

INSERT INTO ingredients(name,is_vegetarian,allergens)
    VALUES ('Sos pomidorowy',true,null);
INSERT INTO ingredients(name,is_vegetarian,allergens)
    VALUES ('Mozzarella di bufala',true,'laktoza');

INSERT INTO products(name,size_id,price)
    VALUES ('Margherita',1,20);
INSERT INTO products(name,size_id,price)
    VALUES ('Hawajska',2,25);

INSERT INTO products_ingredients(product_id, ingredient_id)
    VALUES (1,1);
INSERT INTO products_ingredients(product_id, ingredient_id)
    VALUES (1,2);

INSERT INTO addresses(street_name, house_name, postal_code, city)
    VALUES ('Sasankowa', '16', '24-320', 'Lublin');

INSERT INTO users(first_name, last_name, login, email, password, password_matching, address_id)
    VALUES('Artur', 'Holland', 'thom', 'thom@wp.com', 'thom123', 'thom123', 1);

INSERT INTO orders(is_finalized, total, user_id)
    VALUES(false, 55, 1);

INSERT INTO orders_products(order_id, product_id)
    VALUES (1,1);
INSERT INTO orders_products(order_id, product_id)
    VALUES (1,2);