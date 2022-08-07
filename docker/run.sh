#!/bin/bash

cd ..
cp -fr target/crpsrv-1.0.jar docker/crpsrv-1.0.jar

cd docker
docker compose down
docker compose build app
docker compose up -d

rm crpsrv-1.0.jar