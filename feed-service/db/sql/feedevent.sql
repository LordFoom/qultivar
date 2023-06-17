-- feedevent.sql
-- reset the sequence to 1
ALTER SEQUENCE feedevent_id_seq RESTART;

-- clear the table
DELETE FROM feedevent cascade;

-- User ID 1
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Allan, Grow 1, Feed 1', 1, '2022-09-01T10:00:00Z', current_timestamp, current_timestamp);
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Allan, Grow 1, Feed 2', 1, '2022-12-12T12:00:00Z', current_timestamp, current_timestamp);
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Allan, Grow 1, Feed 3', 1, '2023-04-14T15:00:00Z', current_timestamp, current_timestamp);
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Allan, Grow 2, Feed 1', 2, '2023-06-04T09:30:00Z', current_timestamp, current_timestamp);
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Allan, Grow 2, Feed 2', 2, '2023-06-05T11:30:00Z', current_timestamp, current_timestamp);

-- User ID 2
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Tim, Grow 1, Feed 1', 3, '2023-06-01T14:00:00Z', current_timestamp, current_timestamp);
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Tim, Grow 1, Feed 2', 3, '2023-06-04T16:00:00Z', current_timestamp, current_timestamp);
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Tim, Grow 1, Feed 3', 3, '2023-06-07T13:30:00Z', current_timestamp, current_timestamp);
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Tim, Grow 1, Feed 4', 3, '2023-06-10T10:00:00Z', current_timestamp, current_timestamp);
INSERT INTO feedevent (name, grow_id, feeddate, createddate, updateddate) VALUES ('Tim, Grow 1, Feed 5', 3, '2023-06-13T12:30:00Z', current_timestamp, current_timestamp);
