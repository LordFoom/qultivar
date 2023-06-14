#!/bin/bash

PROJECTS="qultivar-common api-gateway db-service auth-service feed-service media-service user-service api-service qultivar-gui"
CODE_EXCLUDE="api-gateway db-service"
IMAGE_EXCLUDE="qultivar-common"
CERT_INCLUDE="api-service auth-service"
CERT_NAME=qultivar.therudeway.com.crt

projectExists() {
    local project=$1

    if [[ "$PROJECTS" != *"$project"* ]]; then
        echo "the '$1' project does not exist"
        return 1
    fi
    return 0
}

excludeFromCodeBuild() {
    local project=$1

    if [[ "$CODE_EXCLUDE" != *"$project"* ]]; then
        return 1
    fi
    return 0
}

excludeFromImageBuild() {
    local project=$1

    if [[ "$IMAGE_EXCLUDE" != *"$project"* ]]; then
        return 1
    fi
    return 0
}

includeCertificateWithImage() {
    local project=$1

    if [[ "$CERT_INCLUDE" != *"$project"* ]]; then
        return 1
    fi
    return 0
}

databaseIsRunning() {
    if docker ps --format '{{.Ports}}' | grep -q ":5432->"; then
        echo "A docker container is already running on port 5432"
        return 0
    fi
    return 1
}

buildProjectCode() {
    local project=$1
    if excludeFromCodeBuild $project; then
        echo "excluding code build for project '$project'"
        return
    fi

    echo "building code for project '$project'"
    ./gradlew :$project:build
    if [ "$?" -ne "0" ]; then
        echo "code build failed for project [$project], exiting"
        exit 1
    fi
}

buildProjectImage() {
    local project=$1
    if excludeFromImageBuild $project; then
        echo "excluding container image build for project '$project'"
        return
    fi

    echo "building container image for project '$project'"

    # copy the certificate if needed for the image
    if includeCertificateWithImage $project; then
        cp certificates/$CERT_NAME $project
        if [ "$?" -ne "0" ]; then
            echo "certficate copy failed for project [$project], exiting"
            exit 1
        fi
    fi

    cd $project
    docker image build -t therudeway.com/qultivar/$project:latest .
    if [ "$?" -ne "0" ]; then
        echo "image build failed for project [$project], exiting"
        exit 1
    fi
    cd ..

    # remove the copied certificate if it exists
    rm -f $project/$CERT_NAME
}

BUILDALL=Y
PROJECT=ALL
if [ ! -z $1 ]; then
    BUILDALL=N
    PROJECT=$1
    if ! projectExists "$PROJECT"; then
        exit 1
    fi
fi

if [ "$BUILDALL" == "Y" ]; then
    echo "Building Qultivar solution"
else
    echo "Building the [$PROJECT] project"
fi

STOPDB=N
if ! databaseIsRunning; then
    STOPDB=Y
    echo "starting the build database container"
    docker run --rm --name qultivar-build-db \
        -e POSTGRES_USER=pufftime_420_blazor \
        -e POSTGRES_PASSWORD=secret_as_the_secret_day \
        -p 5432:5432 \
        -d \
        therudeway.com/qultivar/db-service:latest
fi

# build the code
if [ "$BUILDALL" == "Y" ]; then
    for PROJECT in $PROJECTS; do
        buildProjectCode $PROJECT
    done 
else
    buildProjectCode $PROJECT
fi

if [ "$STOPDB" == "Y" ]; then
    echo "stopping the build database container"
    docker stop qultivar-build-db
fi

# build the contaner images
if [ "$BUILDALL" == "Y" ]; then
    for PROJECT in $PROJECTS; do
        buildProjectImage $PROJECT
    done 
else
    buildProjectImage $PROJECT
fi

# exit the script
echo "build complete"
exit 0
