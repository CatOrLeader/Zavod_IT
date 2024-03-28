--liquibase formatted sql

--changeset catorleader:2
ALTER TABLE tender
ALTER COLUMN title TYPE text;
--rollback ALTER TABLE tender ALTER COLUMN title TYPE VARCHAR(128);

--changeset catorleader:3
ALTER TABLE tender
ALTER COLUMN type TYPE text;
--rollback ALTER TABLE tender ALTER COLUMN type TYPE VARCHAR(128);

--changeset catorleader:4
ALTER TABLE tender
ALTER COLUMN delivery_address TYPE text;
--rollback ALTER TABLE tender ALTER COLUMN delivery_address TYPE VARCHAR(128);
