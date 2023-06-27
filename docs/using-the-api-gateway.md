# Using the api-gateway

## Pre-requisites

### *Install `jq` utility*

Some of the examples below use `jq` to parse the output from the curl command.  `jq` is a command line based JSON processor that allows to transform, filter, slice, map, or perform other operations on JSON data.

```bash
# Ubuntu installation
sudo apt install -y jq
```

### *Edit the `/etc/hosts` file*

Create an entry in the /etc/hosts file to point the hostname qultivar.therudeway.com to the localhost (127.0.0.1)

```bash
cat /etc/hosts

...
127.0.0.1       qultivar.therudeway.com
...
```

#
## Fetch a valid token

The `api-service` requires a valid token that is used to authenticate against the `auth-service` when running the curl commands.

***NOTE:*** you will need to load the default users to be able to fetch a token

### *Fetch a token to the screen*
```bash
curl --silent --insecure --location --request POST 'https://qultivar.therudeway.com:443/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{"email": "staffyman@gmail.com", "password": "123456"}' | jq

{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGFmZnltYW5AZ21haWwuY29tIiwiaWF0IjoxNjg2NzQ1NjYzLCJleHAiOjE2ODczNTA0NjN9.eZn4bE3Ae9XAvLkH6VaOPo9ESbfXE5bGYTQLIqH87cM"
}
```

#
## Example curl commands (tested directly after the loading of the test data)

### *Reset the environment*
```bash
./qdestroy.sh
./qstart.sh
./qloaddata.sh
./qloadtestdata.sh
```

### *Fetch a valid token into an environment variable*

```bash
TOKEN=$(curl --silent --insecure --silent --location --request POST 'https://qultivar.therudeway.com:443/login' \
        --header 'Content-Type: application/json' \
        --data-raw '{"email": "staffyman@gmail.com", "password": "123456"}' | jq -r '.token')

echo $TOKEN

eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGFmZnltYW5AZ21haWwuY29tIiwiaWF0IjoxNjg2NzQ1ODkyLCJleHAiOjE2ODczNTA2OTJ9.NyH2E28K6Wc8ylwPBx2f7GdOIJAEFkJiQG_lJ1QYM9o
```

### *Fetch the application users*
```bash
curl --silent --insecure https://qultivar.therudeway.com/api/v1/user --header "Authorization: Bearer $TOKEN" | jq

[
  {
    "email": "staffyman@gmail.com",
    "id": 1,
    "name": "allan",
    "password": "$2a$10$pDaaJGbvDePY2JObpe0H/.pmv560AoOCmI3EQm.l0lEsgGKvnFMsK"
  },
  {
    "email": "LordFoom@gmail.com",
    "id": 2,
    "name": "tim",
    "password": "$2a$10$gCGqcOOSZLhYvN.tJDouiOso8EKqWd/4hSwEpN6uD14SoMjO6b7Nq"
  }
]
```

### *Fetch the list of `Grows`*
```bash
curl --silent --insecure https://qultivar.therudeway.com/api/v1/feed/grow \
    --header "Authorization: Bearer $TOKEN"

[
  {
    "endDate": "2023-04-20T10:00:00",
    "id": 1,
    "name": "Allan, Grow 1",
    "startDate": "2022-09-01T10:00:00",
    "userId": 1
  },
  {
    "id": 2,
    "name": "Allan, Grow 2",
    "startDate": "2023-01-01T10:00:00",
    "userId": 1
  },
  {
    "id": 3,
    "name": "Tim, Grow 1",
    "startDate": "2023-04-01T10:00:00",
    "userId": 2
  }
]
```

### *Add a new `Grow`*
```bash
curl --silent --insecure -X POST \
    --header "Authorization: Bearer $TOKEN" \
    --header "Content-Type: application/json" \
    --data '{
         "name": "My Grow",
         "startDate": "2023-06-13T10:00:00",
         "endDate": "2023-06-20T10:00:00",
         "userId": 1
     }' \
     https://qultivar.therudeway.com/api/v1/feed/grow | jq

{
  "endDate": "2023-06-20T10:00:00",
  "id": 4,
  "name": "My Grow",
  "startDate": "2023-06-13T10:00:00",
  "userId": 1
}
```

Take note of the `"id": 4` from the output.  If the `id` is not 4, you will need to update the id in the `--data` object and the `url` in the following commands.

### *Update the `Grow` added in the previous step*
```bash
curl --silent --insecure -X PUT \
    --header "Authorization: Bearer $TOKEN" \
    --header "Content-Type: application/json" \
    --data '{
        "id": 4,
        "name": "Updated Name",
        "startDate": "2023-06-13T10:00:00",
        "endDate": "2023-06-14T18:00:00",
        "userId": 1
    }' \
    https://qultivar.therudeway.com/api/v1/feed/grow/4 | jq

{
  "endDate": "2023-06-14T18:00:00",
  "id": 4,
  "name": "Updated Name",
  "startDate": "2023-06-13T10:00:00",
  "userId": 1
}
```

### *Fetch the updated `Grow`*
```bash
curl --silent --insecure \
    --header "Authorization: Bearer $TOKEN" \
    https://qultivar.therudeway.com/api/v1/feed/grow/4 | jq

{
  "endDate": "2023-06-14T18:00:00",
  "id": 4,
  "name": "Updated Name",
  "startDate": "2023-06-13T10:00:00",
  "userId": 1
}
```

### *Delete the `Grow`*
```bash
curl --silent --insecure -X DELETE \
    --header "Authorization: Bearer $TOKEN" \
    --header "Content-Type: application/json" \
    https://qultivar.therudeway.com/api/v1/feed/grow/4
```

### *TRY: Fetch the deleted `Grow`*
```bash
curl --silent --insecure \
    --header "Authorization: Bearer $TOKEN" \
    https://qultivar.therudeway.com/api/v1/feed/grow/4

Received: 'Not Found, status code 404' when invoking: Rest Client method: 'com.therudeway.qultivar.api.FeedServiceClient#getGrowById'
```

### *TRY: Fetch a valid `Grow`*
```bash
curl --silent --insecure \
    --header "Authorization: Bearer $TOKEN" \
    https://qultivar.therudeway.com/api/v1/feed/grow/3

{
  "id": 3,
  "name": "Tim, Grow 1",
  "startDate": "2023-04-01T10:00:00",
  "userId": 2
}
```
