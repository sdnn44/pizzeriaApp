CREATE TABLE IF NOT EXISTS sizes
(
    id            serial PRIMARY KEY,
    name          varchar,
    diameter      int
);

CREATE TABLE IF NOT EXISTS products
(
    id            serial PRIMARY KEY ,
    name          varchar,
    size_id       int,
    price         float,
    CONSTRAINT fk_product_size
        FOREIGN KEY (size_id) REFERENCES sizes (id)
);



CREATE TABLE IF NOT EXISTS ingredients
(
    id            serial PRIMARY KEY,
    name          varchar,
    is_vegetarian boolean,
    allergens     varchar
);

CREATE TABLE IF NOT EXISTS products_ingredients
(
    product_id int,
    ingredient_id int,
    CONSTRAINT pk_products_ingredients PRIMARY KEY (product_id, ingredient_id),
    CONSTRAINT fk_products FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT fk_ingredients FOREIGN KEY (ingredient_id) REFERENCES ingredients (id)
)

