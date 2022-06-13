INSERT INTO sizes(name,diameter) VALUES ('Mała',32);
INSERT INTO sizes(name,diameter) VALUES ('Średnia',37);
INSERT INTO sizes(name,diameter) VALUES ('Duża',42);

INSERT INTO ingredients(name,is_vegetarian,allergens)
    VALUES ('Sos pomidorowy',true,null);
INSERT INTO ingredients(name,is_vegetarian,allergens)
    VALUES ('Mozzarella di bufala',true,'laktoza');

INSERT INTO products(name,size_id,price)
    VALUES ('Margherita',1,20.00);
INSERT INTO products(name,size_id,price)
    VALUES ('Parma',1,20.00);
INSERT INTO products(name,size_id,price)
    VALUES ('Margherita',2,25.00);
INSERT INTO products(name, size_id, price)
    VALUES ('Zuccini', 3, 19.00);
INSERT INTO products(name, size_id, price)
    VALUES ('Capriciosa', 3, 19.00);

INSERT INTO products_ingredients(product_id, ingredient_id)
    VALUES (1,1);
INSERT INTO products_ingredients(product_id, ingredient_id)
    VALUES (1,2);
INSERT INTO products_ingredients(product_id, ingredient_id)
    VALUES (2,1);
INSERT INTO products_ingredients(product_id, ingredient_id)
    VALUES (2,2);
INSERT INTO products_ingredients(product_id, ingredient_id)
VALUES (3,1);
INSERT INTO products_ingredients(product_id, ingredient_id)
VALUES (3,2);

INSERT INTO products_ingredients(product_id, ingredient_id)
VALUES (4,1);
INSERT INTO products_ingredients(product_id, ingredient_id)
VALUES (4,2);
INSERT INTO products_ingredients(product_id, ingredient_id)
VALUES (5,1);
INSERT INTO products_ingredients(product_id, ingredient_id)
VALUES (5,2);