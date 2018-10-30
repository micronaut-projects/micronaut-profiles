./mvnw package
docker build . -t @app.name@
docker run --network host @app.name@
