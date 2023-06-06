CREATE ROLE qultivar WITH LOGIN PASSWORD 'Qult1v@r#';

-- ##########################################################################################
-- create the feed-service database
-- ##########################################################################################
CREATE DATABASE qultivar_feed_service;

-- connect to the database as superuser to grant the privileges to the qultivar role
\c qultivar_feed_service pufftime_420_blazor
GRANT ALL ON SCHEMA public TO qultivar;


-- ##########################################################################################
-- create the user-service database
-- ##########################################################################################
CREATE DATABASE qultivar_user_service;

-- connect to the database as superuser to grant the privileges to the qultivar role
\c qultivar_user_service pufftime_420_blazor
GRANT ALL ON SCHEMA public TO qultivar;

