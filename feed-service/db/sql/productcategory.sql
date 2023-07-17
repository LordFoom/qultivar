-- productcategory.sql
ALTER SEQUENCE productcategory_id_seq RESTART;

delete from productcategory;

insert into productcategory (id, name, description) values (1, 'General', 'General products');
insert into productcategory (id, name, description) values (2, 'Nutrient', 'Nutrients');
insert into productcategory (id, name, description) values (3, 'Pest Control', 'Pesticides and Insecticides');
