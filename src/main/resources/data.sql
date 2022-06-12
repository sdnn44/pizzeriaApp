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
    VALUES ('Margherita',2,25);

INSERT INTO products_ingredients(product_id, ingredient_id)
    VALUES (1,1);
INSERT INTO products_ingredients(product_id, ingredient_id)
    VALUES (1,2);