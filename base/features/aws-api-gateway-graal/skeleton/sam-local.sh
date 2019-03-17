#!/bin/sh
docker build . -t @app.name@
mkdir -p build
docker run --rm --entrypoint cat @app.name@  /home/application/function.zip > build/function.zip

sam local start-api -t sam.yaml -p 3000

