-- Grow transactional data

-- reset the sequence to 1
ALTER SEQUENCE grow_id_seq RESTART;

-- remove all rows
delete from grow cascade;

-- no need to include the id as the id is allocated by the sequence
insert into grow (name, startdate, enddate, user_id) values ('Allan, Grow 1', '2022-09-01T10:00:00Z', '2023-04-20T10:00:00Z', 1);
insert into grow (name, startdate, enddate, user_id) values ('Allan, Grow 2', '2023-01-01T10:00:00Z', null, 1);
insert into grow (name, startdate, enddate, user_id) values ('Tim, Grow 1', '2023-04-01T10:00:00Z', null, 2);

select * from grow;