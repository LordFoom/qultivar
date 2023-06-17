-- user0.sql
delete from user0 cascade;

-- include the id even though the table sequence
insert into user0 (id, name, password, email, createddate, updateddate) values(1, 'allan', '$2a$10$pDaaJGbvDePY2JObpe0H/.pmv560AoOCmI3EQm.l0lEsgGKvnFMsK', 'staffyman@gmail.com', current_timestamp, current_timestamp);
insert into user0 (id, name, password, email, createddate, updateddate) values(2, 'tim', '$2a$10$gCGqcOOSZLhYvN.tJDouiOso8EKqWd/4hSwEpN6uD14SoMjO6b7Nq', 'LordFoom@gmail.com', current_timestamp, current_timestamp);

-- start the sequence from 10
-- id's 1-9 are allocated specifically for developers when interacting with the environment (e.g. get the TOKEN from the auth-server) 
ALTER SEQUENCE user0_id_seq RESTART 10;


