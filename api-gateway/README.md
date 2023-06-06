### Generate the certficates
```bash
export DOMAIN_NAME=qultivar.therudeway.com

openssl genrsa -out certs/${DOMAIN_NAME}.key 2048
openssl req -new -config ssl.conf -key certs/${DOMAIN_NAME}.key -out certs/${DOMAIN_NAME}.csr
openssl x509 -req -days 365 -in certs/${DOMAIN_NAME}.csr -signkey certs/${DOMAIN_NAME}.key -out certs/${DOMAIN_NAME}.crt
openssl x509 -in certs/${DOMAIN_NAME}.crt -noout -text
```

### Copy the certificate and the key in to the secrets folder
```bash
cp certs/qultivar.therudeway.com.crt ../secrets/qultivar-cert
cp certs/qultivar.therudeway.com.key ../secrets/qultivar-cert-key
```
