#!/bin/bash

DB_CONTAINER=${1:-qultivar-db-service}

# feed-service transactional data
SERVICE_NAME=feed-service
DB_NAME=qultivar_feed_service
SQL_FILES="grow.sql feedevent.sql"
for SQL_FILE in $SQL_FILES; do
    echo "running sql file $SQL_FILE against the $DB_NAME database"
    docker cp ./$SERVICE_NAME/db/sql/$SQL_FILE $DB_CONTAINER:/tmp/
    docker exec $DB_CONTAINER bash -c "psql -U qultivar $DB_NAME -f /tmp/$SQL_FILE"
done
