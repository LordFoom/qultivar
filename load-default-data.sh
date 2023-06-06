#!/bin/bash

DB_CONTAINER=${1:-qultivar-db-service}

# user-service default data
SERVICE_NAME=user-service
DB_NAME=qultivar_user_service
SQL_FILES="user0.sql"
for SQL_FILE in $SQL_FILES; do
    docker cp ./$SERVICE_NAME/db/sql/$SQL_FILE $DB_CONTAINER:/tmp/
    docker exec $DB_CONTAINER bash -c "psql -U qultivar $DB_NAME -f /tmp/$SQL_FILE"
done

# feed-service default data
SERVICE_NAME=feed-service
DB_NAME=qultivar_feed_service
SQL_FILES="growstage.sql"
for SQL_FILE in $SQL_FILES; do
    docker cp ./$SERVICE_NAME/db/sql/$SQL_FILE $DB_CONTAINER:/tmp/
    docker exec $DB_CONTAINER bash -c "psql -U qultivar $DB_NAME -f /tmp/$SQL_FILE"
done
