CREATE TABLE pending_user
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    confirmation_code VARCHAR(6) NOT NULL,
    date_expiration TIMESTAMP WITH TIME ZONE NOT NULL
);