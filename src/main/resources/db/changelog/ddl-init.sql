--liquibase formatted sql
--changeset oleksandr_sukhomlynov@epam.com:1

create table crypto (
  id       uuid primary key default gen_random_uuid(),
  symbol   varchar(10)
);

create table crypto_price
(
    id                 uuid primary key default gen_random_uuid(),
    "timestamp"        date not null,
    crypto_id          uuid references crypto,
    price              numeric(14,2)
);