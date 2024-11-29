#!/bin/bash

docker image pull mysql:latest

docker volume create test

docker container run --name test-mysql -v test:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=pass -d mysql:latest

docker compose up

