-- manufacturer.sql
ALTER SEQUENCE manufacturer_id_seq RESTART;

delete from manufacturer;

insert into manufacturer (name, createddate, updateddate) values ('General', current_timestamp, current_timestamp);
insert into manufacturer (name, createddate, updateddate) values ('BioBizz', current_timestamp, current_timestamp);
insert into manufacturer (name, createddate, updateddate) values ('Nature''s Choice', current_timestamp, current_timestamp);
