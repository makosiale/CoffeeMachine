CREATE TABLE IF NOT EXISTS drink
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS ingredient
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50) NOT NULL,
    stock    DECIMAL(10,2) DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS recipe
(
    id            SERIAL PRIMARY KEY,
    drink_id      INT NOT NULL REFERENCES drink (id) ON DELETE CASCADE ,
    ingredient_id INT NOT NULL REFERENCES ingredient (id) ON DELETE CASCADE ,
    amount        INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS statistic
(
    id          SERIAL PRIMARY KEY,
    drink_id    INT NOT NULL REFERENCES drink (id) ON DELETE CASCADE ,
    order_count INT NOT NULL,
    last_order  TIMESTAMP
    );


INSERT INTO ingredient (name, stock) VALUES
         ('Coffee Beans', 1000),
         ('Water', 5000),
         ('Milk', 2000);


INSERT INTO drink (name) VALUES
         ('Espresso'),
         ('Americano'),
         ('Cappuccino');


INSERT INTO recipe (drink_id, ingredient_id, amount) VALUES
         (1, 1, 10),  -- Espresso: 10g Coffee Beans
         (1, 2, 30),  -- Espresso: 30ml Water
         (2, 1, 10),  -- Americano: 10g Coffee Beans
         (2, 2, 60),  -- Americano: 60ml Water
         (3, 1, 10),  -- Cappuccino: 10g Coffee Beans
         (3, 2, 30),  -- Cappuccino: 30ml Water
         (3, 3, 100); -- Cappuccino: 100ml Milk