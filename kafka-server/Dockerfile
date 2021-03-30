FROM openjdk:8-jre-alpine

# Add necessary packages
RUN apk update && apk upgrade && apk add bash gnupg tar

ENV KAFKA_VERSION=2.6.1

ENV APACHE_MIRROR_URL=https://ftp.byfly.by/pub/apache.org/kafka
ENV KAFKA_PATH=kafka_2.13-$KAFKA_VERSION

# Import apache official GPG keys
ADD https://downloads.apache.org/kafka/KEYS /
ADD https://downloads.apache.org/kafka/$KAFKA_VERSION/$KAFKA_PATH.tgz.asc /
RUN gpg --import KEYS

# Download and verify kafka distribution. If signature isn't match, remove downloaded files
ADD $APACHE_MIRROR_URL/$KAFKA_VERSION/$KAFKA_PATH.tgz /
RUN gpg --verify /$KAFKA_PATH.tgz.asc || rm -rf /$KAFKA_PATH.tgz

RUN tar zxvf $KAFKA_PATH.tgz || printf "Can't download kafka distribution or verify GPG signature.\n\
Try to change mirror URL by providing environment variable like APACHE_MIRROR_URL: 'https://ftp.byfly.by/pub/apache.org/kafka' before building image.\n\
You can do this in 'docker-compose.yml' file in 'environments' section"
WORKDIR /$KAFKA_PATH

EXPOSE 2181 9092

RUN mkdir /logs && touch /logs/zookeeper.log && touch /logs/kafka.log
ADD entrypoint.sh /

ENV KAFKA_OPTS="-Dzookeeper.4lw.commands.whitelist=*"

ENTRYPOINT ["/entrypoint.sh"]
