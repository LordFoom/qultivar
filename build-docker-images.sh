#!/bin/bash

for service in db-service api-service auth-service feed-service media-service user-service qultivar-gui api-gateway;
do
    echo "Creating the container image for $service"
    dockerfile="./src/main/docker/Dockerfile.jvm"
    if [[ "$service" == "db-service" || "$service" == "qultivar-gui" || "$service" == "api-gateway" ]]; then
        dockerfile="./Dockerfile"
    fi

    if [[ "$service" == "api-service" || "$service" == "auth-service" ]]; then
        cp certificates/qultivar.therudeway.com.crt $service/qultivar.therudeway.com.crt
    fi

    cd $service
    docker image build -t therudeway.com/qultivar/$service:latest -f ${dockerfile} .
    cd ..

    if [[ "$service" == "api-service" || "$service" == "auth-service" ]]; then
        rm -f $service/qultivar.therudeway.com.crt
    fi
done
