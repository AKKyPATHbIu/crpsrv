--liquibase formatted sql
--changeset oleksandr_sukhomlynov@epam.com:5

create unique index crypto_price_timestamp_crypto_uidx on crypto_price ("timestamp", crypto_id);