-- statictable.sql

-- remove all rows
delete from statictable cascade;

-- no need to include the id as the id is allocated by the sequence
insert into statictable (id, name, description, rowsequence) values (1, 'Row1', 'Row 01 description', 1);
insert into statictable (id, name, description, rowsequence) values (2, 'Row2', 'Row 02 description', 2);
insert into statictable (id, name, description, rowsequence) values (3, 'Row3', 'Row 03 description', 3);
insert into statictable (id, name, description, rowsequence) values (4, 'Row4', 'Row 04 description', 4);
insert into statictable (id, name, description, rowsequence) values (5, 'Row5', 'Row 05 description', 5);
insert into statictable (id, name, description, rowsequence) values (6, 'Row6', 'Row 06 description', 6);
insert into statictable (id, name, description, rowsequence) values (7, 'Row7', 'Row 07 description', 7);
insert into statictable (id, name, description, rowsequence) values (8, 'Row8', 'Row 08 description', 8);
insert into statictable (id, name, description, rowsequence) values (9, 'Row9', 'Row 09 description', 9);
insert into statictable (id, name, description, rowsequence) values (10, 'Row10', 'Row 10 description', 10);
insert into statictable (id, name, description, rowsequence) values (11, 'Row11', 'Row 11 description', 11);
insert into statictable (id, name, description, rowsequence) values (12, 'Row12', 'Row 12 description', 12);
insert into statictable (id, name, description, rowsequence) values (13, 'Row13', 'Row 13 description', 13);
insert into statictable (id, name, description, rowsequence) values (14, 'Row14', 'Row 14 description', 14);
