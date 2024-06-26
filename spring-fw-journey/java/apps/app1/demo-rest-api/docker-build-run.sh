#!/bin/bash

# build Dockerfile
docker build -t hello-app-java .

# run container
docker run -it --rm --publish 8080:8080  --name my-running-app hello-app-java