--liquibase formatted sql
--changeset oleksandr_sukhomlynov@epam.com:5

create unique index quote_timespamp_crypto_uidx on quote ("timestamp", crypto_id);