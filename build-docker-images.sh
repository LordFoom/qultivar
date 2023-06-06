#!/bin/bash

for service in db-service api-service auth-service feed-service media-service user-service qultivar-gui api-gateway;
do
    echo "Creating the container image for $service"
    dockerfile="./src/main/docker/Dockerfile.jvm"
    if [[ "$service" == "db-service" || "$service" == "qultivar-gui" || "$service" == "api-gateway" ]]; then
        dockerfile="./Dockerfile"
    fi

    cd $service
    docker image build -t therudeway.com/qultivar/$service:latest -f ${dockerfile} .
    cd ..
done
