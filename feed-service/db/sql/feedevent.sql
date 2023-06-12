-- User ID 1
DELETE FROM feedevent WHERE userId = 1;
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (1, 'Event 1 for User 1', 1, '2023-06-01T10:00:00Z');
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (2, 'Event 2 for User 1', 1, '2023-06-02T12:00:00Z');
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (3, 'Event 3 for User 1', 1, '2023-06-03T15:00:00Z');
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (4, 'Event 4 for User 1', 1, '2023-06-04T09:30:00Z');
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (5, 'Event 5 for User 1', 1, '2023-06-05T11:30:00Z');

-- User ID 2
DELETE FROM feedevent WHERE userId = 2;
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (6, 'Event 1 for User 2', 2, '2023-06-01T14:00:00Z');
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (7, 'Event 2 for User 2', 2, '2023-06-02T16:00:00Z');
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (8, 'Event 3 for User 2', 2, '2023-06-03T13:30:00Z');
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (9, 'Event 4 for User 2', 2, '2023-06-04T10:00:00Z');
INSERT INTO feedevent (id, title, userId, feedDate) VALUES (10, 'Event 5 for User 2', 2, '2023-06-05T12:30:00Z');
