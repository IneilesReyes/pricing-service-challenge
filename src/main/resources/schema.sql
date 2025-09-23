CREATE TABLE IF NOT EXISTS brand (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              name VARCHAR(200) NOT NULL,
                                              category VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS price (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            price DECIMAL(10, 2) NOT NULL,
                                            start_date TIMESTAMP NOT NULL,
                                            end_date TIMESTAMP NOT NULL,
                                            priority INT NOT NULL,
                                            product_id BIGINT NOT NULL,
                                            brand_id BIGINT NOT NULL,
                                            currency_code VARCHAR(3) NOT NULL,
                                            FOREIGN KEY (product_id) REFERENCES product(id),
                                            FOREIGN KEY (brand_id) REFERENCES brand(id)
);

CREATE INDEX IF NOT EXISTS idx_price_product_brand_dates
    ON price (product_id, brand_id, start_date, end_date);