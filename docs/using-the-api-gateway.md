# Using the api-gateway

#
## Pre-requisites

### `jq` installation

`jq` is a command line based JSON processor that allows to transform, filter, slice, map, or perform other operations on JSON data.

```bash
# Ubuntu installation
sudo apt install -y jq
```

### `/etc/hosts`

create an entry in the /etc/hosts file to point to qultivar.therudeway.com

```bash
cat /etc/hosts

...
127.0.0.1       qultivar.therudeway.com
...
```

#
## Get a token

Get a token using the api-gateway.  The token is required for all the endoints defined in the api-service

```bash
curl --insecure --location --request POST 'https://qultivar.therudeway.com:443/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{"email": "staffyman@gmail.com", "password": "123456"}'
```

this will extract the token into an environment variable TOKEN

```bash
TOKEN=$(curl --insecure --silent --location --request POST 'https://qultivar.therudeway.com:443/login' \
        --header 'Content-Type: application/json' \
        --data-raw '{"email": "staffyman@gmail.com", "password": "123456"}' | jq -r '.token')

curl --insecure https://qultivar.therudeway.com/api/v1/user \
    --header "Authorization: Bearer $TOKEN"
```

