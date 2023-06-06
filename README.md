# create the root folder
```bash
mkdir qultivar
cd qultivar
```

# create the projects

## Resteasy-Reactive micro service application
```bash
# api-service
APP_REST_PORT=5000
APP_GROUP=com.therudeway.qultivar.api
APP_NAME=api-service
APP_VERSION=1.0.0-SNAPSHOT
```
# auth-service
APP_REST_PORT=5001
APP_GROUP=com.therudeway.qultivar.auth
APP_NAME=auth-service
APP_VERSION=1.0.0-SNAPSHOT
```
```bash
# feed-service
APP_REST_PORT=5002
APP_GROUP=com.therudeway.qultivar.feed
APP_NAME=feed-service
APP_VERSION=1.0.0-SNAPSHOT
```
```bash
# media-service
APP_REST_PORT=5003
APP_GROUP=com.therudeway.qultivar.media
APP_NAME=media-service
APP_VERSION=1.0.0-SNAPSHOT
```
```bash
# user-service
APP_REST_PORT=5004
APP_GROUP=com.therudeway.qultivar.user
APP_NAME=user-service
APP_VERSION=1.0.0-SNAPSHOT
```

```bash
quarkus create app \
    --gradle-kotlin-dsl \
    --java=17 \
    --code \
    --extensions=quarkus-kotlin \
    --app-config="quarkus.http.port=${APP_REST_PORT}" \
    --platform-bom=io.quarkus.platform:quarkus-bom:3.1.0.Final \
    ${APP_GROUP}:${APP_NAME}:${APP_VERSION}
```

# create the common project
```bash
# feed-service
APP_GROUP=com.therudeway.qultivar.common
APP_NAME=qultivar-common
APP_VERSION=1.0.0-SNAPSHOT
```
```bash
quarkus create app \
    --gradle-kotlin-dsl \
    --no-code \
    --java=17 \
    --extensions="quarkus-kotlin" \
    --platform-bom=io.quarkus.platform:quarkus-bom:3.1.0.Final \
    ${APP_GROUP}:${APP_NAME}:${APP_VERSION}
```

# run the containerised environment
Navigate to the root folder of the git source repository

## build the code
```bash
./gradlew build
```

## build the container images
```bash
./gradlew build
```

## start the environment
```bash
docker-compose up
```
