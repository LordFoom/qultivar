# Media file upload/download testing

This guide outlines the steps and tests the media file upload, download, list and delete functionality of the `media-service` microservice.

#
## Using the `api-service`

At the time of writing this document, there is an error uploading and downloading images through the api-service.  To test the functionality, the images are first copied into the `media-service` container and managed from inside the container

***TODO:***

#
## Process from within the media-service container

### *Copy the test files to the media-service container*

```bash
extensions="txt jpg gif png tif mp4 wmv"
for ext in ${extensions}
do
    docker cp ./media-service/media_files/test.${ext} qultivar-media-service:/tmp/test.${ext}
done
```

### *Connect to the media service container*
```bash
docker exec -it -e extensions="${extensions}" qultivar-media-service bash
```

### *Upload the files*

The media service checks the file headers for valid image and video files.  You will notice that the test.txt file is not uploaded.  The upload process will upload the files into the `/qultivar/media/{userId}/` folder and will create it if it does not exist.

```bash
# check the file system for existing files
ls -al /qultivar/media/

total 12
drwxr-x--- 1 jboss jboss 4096 Jun 14 13:52 .
drwxr-xr-x 1 root  root  4096 Jun  9 10:45 ..

# upload the files
for ext in ${extensions}
do
    curl -k -X POST -F "file=@/tmp/test.${ext}" "https://localhost:5003/media/1/upload/test.${ext}"
    echo ""
done

# check the file system for the uploaded files
ls -al /qultivar/media/1/

total 2144
drwxr-xr-x 2 jboss root     4096 Jun 14 13:54 .
drwxr-x--- 1 jboss jboss    4096 Jun 14 13:54 ..
-rw-r--r-- 1 jboss root    11116 Jun 14 13:54 test.gif
-rw-r--r-- 1 jboss root     5919 Jun 14 13:54 test.jpg
-rw-r--r-- 1 jboss root  1055736 Jun 14 13:54 test.mp4
-rw-r--r-- 1 jboss root    53348 Jun 14 13:54 test.png
-rw-r--r-- 1 jboss root    20978 Jun 14 13:54 test.tif
-rw-r--r-- 1 jboss root  1027043 Jun 14 13:54 test.wmv
```

### *List the users files*
```bash
curl -s -k -X GET "https://localhost:5003/media/1/files"
```

### *Download the files*
```bash
# remove the downloaded files if they exist
rm -f /tmp/test_download.*

# download the files
for ext in ${extensions}
do
    curl -s -X GET "http://localhost:8080/media/1/download/test.${ext}" --output "/tmp/test_download.${ext}"
done

# list the downloaded files
ls -al /tmp/test_download.*

-rw-r--r-- 1 jboss root   11116 Jun 14 14:03 /tmp/test_download.gif
-rw-r--r-- 1 jboss root    5919 Jun 14 14:03 /tmp/test_download.jpg
-rw-r--r-- 1 jboss root 1055736 Jun 14 14:03 /tmp/test_download.mp4
-rw-r--r-- 1 jboss root   53348 Jun 14 14:03 /tmp/test_download.png
-rw-r--r-- 1 jboss root   20978 Jun 14 14:03 /tmp/test_download.tif
-rw-r--r-- 1 jboss root      24 Jun 14 14:03 /tmp/test_download.txt
-rw-r--r-- 1 jboss root 1027043 Jun 14 14:03 /tmp/test_download.wmv


# verify the checksum of the original vs. the downloaded file
echo -e "test.mp4:\n   original   -> $(cksum /tmp/test.mp4)\n   downloaded -> $(cksum /tmp/test_download.mp4)"

test.mp4:
   original   -> 1667252257 1055736 /tmp/test.mp4
   downloaded -> 1667252257 1055736 /tmp/test_download.mp4
```

### *Delete the files*
```bash
for ext in ${extensions}
do
    curl -X DELETE "http://localhost:8080/media/1/delete/test.$ext"
    echo ""
done

# verify the files have been deleted from the file system
ls -al /qultivar/media/1/

total 8
drwxr-xr-x 2 jboss root  4096 Jun 14 14:07 .
drwxr-x--- 1 jboss jboss 4096 Jun 14 13:54 ..

# list the users files
curl -s -k -X GET "https://localhost:5003/media/1/files"

[]
```
