--liquibase formatted sql
--changeset oleksandr_sukhomlynov@epam.com:3

create unique index crypto_lower_symbol on crypto (lower(symbol));