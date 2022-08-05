--liquibase formatted sql
--changeset oleksandr_sukhomlynov@epam.com:2

alter table quote alter column "timestamp" type timestamptz;