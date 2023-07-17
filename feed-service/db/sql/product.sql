-- product.sql
ALTER SEQUENCE product_id_seq RESTART;

delete from product;

-- 'General' ProductCategory 
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('Molasses (Black Strap)', 'Molasses (Black Strap)', 1, 1, current_timestamp, current_timestamp);
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('Hydrogen Peroxide', 'Hydrogen Peroxide', 1, 1, current_timestamp, current_timestamp);

-- 'Nutrient' ProductCategory 
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('Bio-Grow', 'Bio-Grow', 2, 2, current_timestamp, current_timestamp);
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('Bio-Bloom', 'Bio-Bloom', 2, 2, current_timestamp, current_timestamp);
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('Bio-Heaven', 'Bio-Heaven', 2, 2, current_timestamp, current_timestamp);
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('Top-Max', 'Top-Max', 2, 2, current_timestamp, current_timestamp);
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('Fish-Mix', 'Fish-Mix', 2, 2, current_timestamp, current_timestamp);
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('Aloe Vera Juice', 'Aloe Vera Juice', 2, 3, current_timestamp, current_timestamp);

-- 'Pest Control' ProductCategory
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('BioGrow Bioneem', 'BioGrow Bioneem', 3, 2, current_timestamp, current_timestamp);
insert into product (name, description, category_id, manufacturer_id, createddate, updateddate) values ('BioGrow Pyrol', 'BioGrow Pyrol', 3, 2, current_timestamp, current_timestamp);
