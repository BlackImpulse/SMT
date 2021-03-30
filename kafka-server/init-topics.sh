#!/usr/bin/env bash

echo Topic initialization started

/entrypoint.sh > /dev/null 2>&1 &

# Wait for zookeeper to start
while [ "$(echo ruok | nc localhost 2181)" != "imok" ]
do
  echo "Zookeeper is down. Wait for 5s"
  sleep 5s
done

echo "Zookeeper is up"

# Create topic "logs" when there's enough brokers are online
while ! bin/kafka-topics.sh --zookeeper localhost:2181 --create --replication-factor 1 --partitions 1 --topic logs
do
  echo "Can't create topic 'logs'. Wait for 5s"
  sleep 5s
done
