--liquibase formatted sql
--changeset oleksandr_sukhomlynov@epam.com:2

alter table crypto_price alter column "timestamp" type timestamptz;