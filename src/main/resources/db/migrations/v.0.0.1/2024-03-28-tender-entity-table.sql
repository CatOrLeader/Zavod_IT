--liquibase formatted sql

-- changeset catorleader:1
CREATE TABLE IF NOT EXISTS tender (
    tender_id VARCHAR(32) PRIMARY KEY NOT NULL,
    title varchar(128) NOT NULL,
    type varchar(128) NOT NULL,
    open_date DATE NOT NULL,
    close_date DATE NOT NULL,
    delivery_address VARCHAR(256),
    currency_name VARCHAR(16)
);
-- rollback DROP TABLE tender;
