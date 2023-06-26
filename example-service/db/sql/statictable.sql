-- statictable.sql

-- remove all rows
delete from statictable cascade;

-- no need to include the id as the id is allocated by the sequence
insert into statictable (id, name, description) values (1, 'Row1', 'Row 1 description');
insert into statictable (id, name, description) values (2, 'Row2', 'Row 2 description');
insert into statictable (id, name, description) values (3, 'Row3', 'Row 3 description');
