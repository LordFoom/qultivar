# Dockerfile
FROM postgres:15.3

RUN apt update -y && \
    apt upgrade -y && \
    apt-get install procps -y

COPY init.sql /docker-entrypoint-initdb.d/
