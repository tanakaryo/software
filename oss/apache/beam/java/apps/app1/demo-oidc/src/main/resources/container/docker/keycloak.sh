#!/bin/bash

docker run -p 18080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=pass quay.io/keycloak/keycloak:20.0.1 start-dev
