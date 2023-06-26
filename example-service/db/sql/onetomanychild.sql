-- onetomanychild.sql
-- reset the sequence to 1
ALTER SEQUENCE onetomanychild_id_seq RESTART;

-- clear the table
DELETE FROM onetomanychild cascade;

-- parent id 1
INSERT INTO onetomanychild (onetomanyparent_id, randomdate, notes, createddate, updateddate) VALUES (1, '2022-09-01T10:00:00Z', 'some notes', current_timestamp, current_timestamp);
INSERT INTO onetomanychild (onetomanyparent_id, randomdate, notes, createddate, updateddate) VALUES (1, '2022-12-12T12:00:00Z', 'some more notes', current_timestamp, current_timestamp);
INSERT INTO onetomanychild (onetomanyparent_id, randomdate, notes, createddate, updateddate) VALUES (1, '2023-04-14T15:00:00Z', 'even more notes', current_timestamp, current_timestamp);

-- parent id 2
INSERT INTO onetomanychild (onetomanyparent_id, randomdate, notes, createddate, updateddate) VALUES (2, '2023-06-01T14:00:00Z', 'parent note 1', current_timestamp, current_timestamp);
INSERT INTO onetomanychild (onetomanyparent_id, randomdate, notes, createddate, updateddate) VALUES (2, '2023-06-04T16:00:00Z', 'parent note 2', current_timestamp, current_timestamp);
INSERT INTO onetomanychild (onetomanyparent_id, randomdate, notes, createddate, updateddate) VALUES (2, '2023-06-07T13:30:00Z', 'parent note 3', current_timestamp, current_timestamp);
