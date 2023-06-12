-- clear the table
DELETE FROM feedevent;

-- User ID 1
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (1, 'Allan, Grow 1, Feed 1', 1, '2022-09-01T10:00:00Z');
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (2, 'Allan, Grow 1, Feed 1', 1, '2022-12-12T12:00:00Z');
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (3, 'Allan, Grow 1, Feed 1', 1, '2023-04-14T15:00:00Z');
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (4, 'Allan, Grow 1, Feed 2', 2, '2023-06-04T09:30:00Z');
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (5, 'Allan, Grow 1, Feed 2', 2, '2023-06-05T11:30:00Z');

-- User ID 2
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (6, 'Tim, Grow 1, Feed 1', 3, '2023-06-01T14:00:00Z');
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (7, 'Tim, Grow 1, Feed 1', 3, '2023-06-04T16:00:00Z');
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (8, 'Tim, Grow 1, Feed 1', 3, '2023-06-07T13:30:00Z');
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (9, 'Tim, Grow 1, Feed 1', 3, '2023-06-10T10:00:00Z');
INSERT INTO feedevent (id, name, grow_id, feedDate) VALUES (10, 'Tim, Grow 1, Feed 1', 3, '2023-06-13T12:30:00Z');
