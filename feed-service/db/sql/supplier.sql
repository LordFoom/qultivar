-- supplier.sql
ALTER SEQUENCE supplier_id_seq RESTART;

delete from supplier;

insert into supplier (name, createddate, updateddate) values ('General', current_timestamp, current_timestamp);
insert into supplier (name, createddate, updateddate) values ('Hortishop', current_timestamp, current_timestamp);
