#
## Docker environment (from within the container)
```bash
extensions="txt jpg gif png tif mp4 wmv"

# copy the test file to the media-service container
for ext in ${extensions}
do
    docker cp ./media-service/media_files/test.${ext} qultivar-media-service:/tmp/test.${ext}
done
```

```bash
# connect to the media service container
docker exec -it -e extensions="${extensions}" qultivar-media-service bash

# upload the files
for ext in ${extensions}
do
    curl -k -X POST -F "file=@/tmp/test.${ext}" "https://localhost:5003/media/1/upload/test.${ext}"
    echo ""
done
ls -al /qultivar/media/1/

# list the users files
curl -s -k -X GET "https://localhost:5003/media/1/files"

# download the files
rm -f /tmp/test_get.*
for ext in ${extensions}
do
    curl -s -X GET "http://localhost:8080/media/1/get/test.${ext}" --output "/tmp/test_get.${ext}"
done
ls -al /tmp/test_get.*

# delete the files
for ext in ${extensions}
do
    curl -X DELETE "http://localhost:8080/media/1/delete/test.$ext"
    echo ""
done
ls -al /qultivar/media/1/

# list the users files
curl -s -k -X GET "https://localhost:5003/media/1/files"
```

#
## quarkusDev
```bash
# startup the service
./gradlew :media-service:quarkusDev
```

```bash
extensions="txt jpg gif png tif mp4 wmv"

for ext in ${extensions}
do
    curl -k -X POST -F "file=@./media-service/media_files/test.${ext}" "https://localhost:5003/media/1/upload/test.${ext}"
    echo ""
done

# list the files uploaded
curl -s -k -X GET "https://localhost:5003/media/1/files"

# download the files
rm -f /tmp/test_get.*
for ext in ${extensions}
do
    curl -s -k -X GET "https://localhost:5003/media/1/download/test.${ext}" --output "/tmp/test_get.${ext}"
done
ls -al /tmp/test_get.*
```


#
## Using the API gateway

############## NOT WORKING -> TODO ##############
```bash
TOKEN=$(curl --insecure --silent --location --request POST 'https://qultivar.therudeway.com/login' \
        --header 'Content-Type: application/json' \
        --data-raw '{"email": "staffyman@gmail.com", "password": "123456"}' | jq -r '.token')

curl --insecure \
    --location 'https://qultivar.therudeway.com/api/v1/user' \
    --header "Authorization: Bearer $TOKEN"

curl --insecure \
    -F "file=@./media-service/media_files/test.jpg" \
    --location "https://qultivar.therudeway.com/api/v1/media/1/upload/test.jpg" \
    --header "Authorization: Bearer $TOKEN"

curl --insecure \
    --location "https://qultivar.therudeway.com/api/v1/media/1/files" \
    --header "Authorization: Bearer $TOKEN"


extensions="txt jpg gif png tif mp4 wmv"

for ext in ${extensions}
do
    curl -k -X POST \
        -F "file=@./media-service/media_files/test.${ext}" \
        --header "Authorization: Bearer $TOKEN" \
        --location "https://qultivar.therudeway.com/api/v1/media/1/upload/test.${ext}"
done
```

