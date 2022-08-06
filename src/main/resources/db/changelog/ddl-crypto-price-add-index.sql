--liquibase formatted sql
--changeset oleksandr_sukhomlynov@epam.com:4

create index crypto_price_timestamp_idx  on crypto_price("timestamp", crypto_id);