#!/bin/sh
docker build . -t @app.name@
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8080:8080 @app.name@"
