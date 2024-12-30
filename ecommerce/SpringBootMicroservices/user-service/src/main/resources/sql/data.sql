CREATE TABLE IF NOT EXISTS user_data (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phone_number VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    address_line3 VARCHAR(255),
    pin VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS role (
    role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    role_names ENUM('ADMIN', 'CUSTOMER'),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user_data(user_id) ON DELETE CASCADE
);
