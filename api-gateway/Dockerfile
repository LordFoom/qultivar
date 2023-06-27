FROM nginx:1.23

RUN apt update -y && \
    apt upgrade -y && \
    apt install -y apt-utils iputils-ping net-tools && \
    apt clean 

COPY default.conf.template /etc/nginx/templates/default.conf.template
