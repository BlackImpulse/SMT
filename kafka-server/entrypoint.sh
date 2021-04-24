#!/usr/bin/env bash

echo "BROKER_ID=$BROKER_ID"
echo "ADVERTISED_HOSTS=$ADVERTISED_HOSTS"
echo "RUN_ZOOKEEPER=$RUN_ZOOKEEPER"

if [ "$RUN_ZOOKEEPER" = 1 ]
then
  bin/zookeeper-server-start.sh config/zookeeper.properties
else
  echo "zookeeper won't be started because of RUN_ZOOKEEPER = '$RUN_ZOOKEEPER'"
fi

if [ "$RUN_KAFKA_SERVER" = 0 ]
then
  echo "kafka broker won't be started because of RUN_KAFKA_SERVER = '$RUN_KAFKA_SERVER'"
else
  bin/kafka-server-start.sh config/server.properties \
    `if [ ! -z $ADVERTISED_HOSTS ]; then echo "--override advertised.listeners=$ADVERTISED_HOSTS"; fi` \
    `if [ ! -z $BROKER_ID ]; then echo "--override broker.id=$BROKER_ID"; fi` \
    `if [ ! -z $ZOOKEEPER_HOST ]; then echo "--override zookeeper.connect=$ZOOKEEPER_HOST"; fi` \
    `if [ ! -z $TOPIC_AUTOCREATE ]; then echo "--override auto.create.topics.enable=$TOPIC_AUTOCREATE"; fi`
fi
