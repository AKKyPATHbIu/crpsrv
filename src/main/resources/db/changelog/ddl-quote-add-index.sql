--liquibase formatted sql
--changeset oleksandr_sukhomlynov@epam.com:4

create index quote_timestamp_idx  on quote("timestamp", crypto_id);