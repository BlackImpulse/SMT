#!/usr/bin/env sh

java $JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5009 -jar smt-backend-${VERSION}.jar