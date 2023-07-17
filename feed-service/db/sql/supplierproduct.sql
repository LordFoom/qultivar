-- supplierproduct.sql
ALTER SEQUENCE supplierproduct_id_seq RESTART;

delete from supplierproduct;

-- 'General' Supplier 
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (1, 1, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (1, 2, current_timestamp, current_timestamp);

-- 'Hortishop' Supplier 
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 2, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 3, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 4, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 5, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 6, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 7, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 8, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 9, current_timestamp, current_timestamp);
insert into supplierproduct (supplier_id, product_id, createddate, updateddate) values (2, 10, current_timestamp, current_timestamp);
