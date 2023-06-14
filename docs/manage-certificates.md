# Certificate create process

This document outlines the process to create the certificates to enable SSL communication between the microservices

***NOTE:*** This process has already been run and the files exist in the repository. There is no need to run this process unless the certificates have expired.

### *Change to the certificate folder*
```bash
cd certificates
```

### *Export the environment variables*
```bash
export DOMAIN_NAME=qultivar.therudeway.com
export TRUSTSTORE_ALIAS=qultivar
export TRUSTSTORE_PASSWORD=Qult1v@r#
```

### *Generate the certificate / keys*
```bash
rm -f $DOMAIN_NAME.crt
rm -f $DOMAIN_NAME.private.key

openssl req -new -x509 -nodes -days 365 \
    -keyout $DOMAIN_NAME.private.key \
    -out $DOMAIN_NAME.crt \
    -config ssl.conf \
    -extensions v3_req
```

### *Validate the certificate and key*
```bash
CRT_CKSUM=$(openssl x509 -noout -modulus -in $DOMAIN_NAME.crt | openssl md5)
KEY_CKSUM=$(openssl rsa -noout -modulus -in $DOMAIN_NAME.private.key | openssl md5)

if [[ $CRT_CKSUM == $KEY_CKSUM ]]; then
    echo "SUCCESS: Certificate and key are valid"
else
    echo "FAILURE: Certificate and key are invalid"
fi
```

### *List the allowed host names*
```bash
openssl x509 -in $DOMAIN_NAME.crt -noout -text | grep DNS
```

### *Generate the truststore*
```bash
rm -f $DOMAIN_NAME.truststore.jks
keytool -import -noprompt -trustcacerts \
    -alias $TRUSTSTORE_ALIAS -file $DOMAIN_NAME.crt \
    -storepass $TRUSTSTORE_PASSWORD \
    -keystore $DOMAIN_NAME.truststore.jks
```

### *Validate the truststore*
```bash
keytool -list -keystore $DOMAIN_NAME.truststore.jks -storepass $TRUSTSTORE_PASSWORD -alias $TRUSTSTORE_ALIAS
```

### *Copy the certificate, key and truststore in to the secrets folder*
```bash
cp $DOMAIN_NAME.crt ../secrets/qultivar-certificate
cp $DOMAIN_NAME.private.key ../secrets/qultivar-certificate-key
cp $DOMAIN_NAME.truststore.jks ../secrets/qultivar-truststore
echo $TRUSTSTORE_PASSWORD > ../secrets/qultivar-truststore-password
```
