#!/bin/bash

KCHOST=http://localhost:8081
REALM=fastify
CLIENT_ID=fastify-service
UNAME=otus-test
PASSWORD=otus

ACCESS_TOKEN=`curl \
  -d "client_id=$CLIENT_ID" \
  -d "username=$UNAME" \
  -d "password=$PASSWORD" \
  -d "grant_type=password" \
  "$KCHOST/realms/$REALM/protocol/openid-connect/token"  | jq -r '.access_token'`
echo "$ACCESS_TOKEN"
