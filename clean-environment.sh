#!/bin/bash

./gradlew clean

docker-compose down -v
docker system prune -f
docker volume prune -f
