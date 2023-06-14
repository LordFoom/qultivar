# Navigating the Database

For the purposes of this document the postgres container is the container started by the `./qstart.sh` script and is named `qultivar-db-service`.

## Database credentials

For development purposes the database credentials are:

| Username | Password | Description |
| -------- | -------- | ----------- |
| pufftime_420_blazor | secret_as_the_secret_day | PostgreSQL admin user credentials |
| qultivar | Qult1v@r# | Microservice database user credentials |


## Microservice databases

Each microservice has it's own database created in the postgres container.  This will ensure that in the future, each service could run in it's own postgres container.  For development purposes, and convenience, a single database container is started and a database per service is created.  To see which databases are created, you can look at the [initialisation script](../db-service/init.sql) for the postgres instance.

The database naming standard is `qultivar_{name}_service`

For Example:
| Project | Database Name |
| -------- | ----------- |
| user-service | qultivar_user_service |
| feed-service | qultivar_feed_service |

### *Listing the microservice databases*

To list the databases created in the postgres instance, connect as the admin user.

***Example 1: Connect to `psql` and execute a query*** 
```bash
docker exec -it qultivar-db-service bash -c 'psql -U pufftime_420_blazor'

pufftime_420_blazor=# SELECT datname FROM pg_database WHERE datname LIKE 'qultivar%';

        datname
-----------------------
 qultivar_feed_service
 qultivar_user_service
(2 rows)

pufftime_420_blazor=# \q
```

***Example 2: Execute a query in a single command*** 
```bash

docker exec -it qultivar-db-service bash -c "psql -U pufftime_420_blazor -c \"SELECT datname FROM pg_database WHERE datname LIKE 'qultivar%';\""

        datname
-----------------------
 qultivar_feed_service
 qultivar_user_service
(2 rows)
```

## General queries

### *Connect to the `qultivar_feed_service` database and list the tables*

Option 1: From with psql 
```bash
docker exec -it qultivar-db-service bash -c 'psql -U qultivar qultivar_feed_service'

qultivar_feed_service=> \dt

           List of relations
 Schema |   Name    | Type  |  Owner
--------+-----------+-------+----------
 public | feedevent | table | qultivar
 public | grow      | table | qultivar
 public | growstage | table | qultivar
(3 rows)

qultivar_feed_service=> \q
```

Option 2: Using a single command
```bash
docker exec -it qultivar-db-service bash -c 'psql -U qultivar qultivar_feed_service -c "\dt"'

           List of relations
 Schema |   Name    | Type  |  Owner
--------+-----------+-------+----------
 public | feedevent | table | qultivar
 public | grow      | table | qultivar
 public | growstage | table | qultivar
(3 rows)
```

### *Describe the `user0` table in the `qultivar_user_service` database*
```bash
docker exec -it qultivar-db-service bash -c 'psql -U qultivar qultivar_user_service -c "\d user0"'

                                     Table "public.user0"
  Column  |          Type          | Collation | Nullable |              Default
----------+------------------------+-----------+----------+-----------------------------------
 id       | bigint                 |           | not null | nextval('user0_id_seq'::regclass)
 email    | character varying(255) |           |          |
 name     | character varying(255) |           |          |
 password | character varying(255) |           |          |
Indexes:
    "user0_pkey" PRIMARY KEY, btree (id)
```
