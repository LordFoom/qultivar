-- Grow transactional data
delete from grow cascade;
insert into grow (id, name, startdate, enddate, user_id) values (1, 'Allan, Grow 1', '2022-09-01T10:00:00Z', '2023-04-20T10:00:00Z', 1);
insert into grow (id, name, startdate, enddate, user_id) values (2, 'Allan, Grow 2', '2023-01-01T10:00:00Z', null, 1);
insert into grow (id, name, startdate, enddate, user_id) values (3, 'Tim, Grow 1', '2023-04-01T10:00:00Z', null, 2);
