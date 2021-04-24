FROM openjdk:8-jre-alpine

ADD target /usr/local/share/smt-backend

WORKDIR /usr/local/share/smt-backend

ADD entrypoint.sh ./

ENV VERSION=0.0.1

ENTRYPOINT ["/usr/local/share/smt-backend/entrypoint.sh"]