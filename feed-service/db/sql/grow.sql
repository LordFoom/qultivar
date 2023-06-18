-- grow.sql
-- reset the sequence to 1
ALTER SEQUENCE grow_id_seq RESTART;

-- remove all rows
delete from grow cascade;

-- no need to include the id as the id is allocated by the sequence
insert into grow (name, startdate, enddate, user_id, createddate, updateddate) values ('Allan, Grow 1', '2022-09-01', '2023-04-20', 1, current_timestamp, current_timestamp);
insert into grow (name, startdate, enddate, user_id, createddate, updateddate) values ('Allan, Grow 2', '2023-01-01', null, 1, current_timestamp, current_timestamp);
insert into grow (name, startdate, enddate, user_id, createddate, updateddate) values ('Tim, Grow 1', '2023-04-01', null, 2, current_timestamp, current_timestamp);
