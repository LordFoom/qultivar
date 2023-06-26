-- onetomanyparent.sql
-- reset the sequence to 1
ALTER SEQUENCE onetomanyparent_id_seq RESTART;

-- clear the table
DELETE FROM onetomanyparent cascade;

-- this is static data, no sequence is created and the id must be specified in the insert statement 
insert into onetomanyparent (name, description, firstdate, createddate, updateddate) values ('Parent 1', 'this is one to many parent one.', '2022-11-01T10:00:00Z', current_timestamp, current_timestamp);
insert into onetomanyparent (name, description, firstdate, createddate, updateddate) values ('Parent Two', 'this is one to many parent 2.', '2022-12-01T13:01:45Z', current_timestamp, current_timestamp);
