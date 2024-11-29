#!/bin/bash

docker run -itd \
 -e MONGO_INITDB_ROOT_USERNAME=root \
 -e MONGO_INITDB_ROOT_PASSWORD=pass \
 -e MONGO_INITDB_DATABASE=documents \
 -p 27017:27017 \
 --name mongo \
 mongo