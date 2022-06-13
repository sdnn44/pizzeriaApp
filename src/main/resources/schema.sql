CREATE TABLE IF NOT EXISTS sizes
(
    size_id            serial PRIMARY KEY,
    name          varchar,
    diameter      int
);

CREATE TABLE IF NOT EXISTS products
(
    product_id    serial PRIMARY KEY ,
    name          varchar,
    size_id       int,
    price         float,
    CONSTRAINT fk_product_size
        FOREIGN KEY (size_id) REFERENCES sizes (size_id)
);



CREATE TABLE IF NOT EXISTS ingredients
(
    ingredient_id serial PRIMARY KEY,
    name          varchar,
    is_vegetarian boolean,
    allergens     varchar
);

CREATE TABLE IF NOT EXISTS products_ingredients
(
    product_id int,
    ingredient_id int,
    CONSTRAINT pk_products_ingredients PRIMARY KEY (product_id, ingredient_id),
    CONSTRAINT fk_products FOREIGN KEY (product_id) REFERENCES products (product_id),
    CONSTRAINT fk_ingredients FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)
);

CREATE TABLE IF NOT EXISTS addresses
(
    address_id            serial PRIMARY KEY,
    street_name          varchar,
    house_name          varchar,
    postal_code          varchar,
    city          varchar
);

CREATE TABLE IF NOT EXISTS users
(
    user_id            serial PRIMARY KEY,
    first_name          varchar,
    last_name          varchar,
    login          varchar,
    email          varchar,
    password          varchar,
    password_matching          varchar,
    address_id int,
    CONSTRAINT fk_user_address
        FOREIGN KEY (address_id) REFERENCES addresses (address_id)
);

CREATE TABLE IF NOT EXISTS orders
(
    order_id serial PRIMARY KEY,
    is_finalized boolean,
    total         float,
    user_id int,

    CONSTRAINT fk_order_user
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS orders_products
(
  order_id int,
  product_id int,
    CONSTRAINT pk_orders_products PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_orders FOREIGN KEY (order_id) REFERENCES orders (order_id),
    CONSTRAINT fk_products FOREIGN KEY (product_id) REFERENCES products (product_id)
)
