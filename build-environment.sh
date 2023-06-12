#!/bin/bash

docker run --rm --name qultivar-build-db \
    -e POSTGRES_USER=pufftime_420_blazor \
    -e POSTGRES_PASSWORD=secret_as_the_secret_day \
    -p 5432:5432 \
    -d \
    therudeway.com/qultivar/db-service:latest


./gradlew build

docker stop qultivar-build-db
pause 3

docker rm qultivar-build-db

./build-docker-images.sh
