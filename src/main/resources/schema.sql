CREATE SCHEMA IF NOT EXISTS MEDICAL_DATA;

CREATE TABLE IF NOT EXISTS MEDICAL_DATA.doctor (
    id INTEGER IDENTITY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    zip VARCHAR(100) NOT NULL,
    profession VARCHAR(100) NOT NULL
);
