FROM openjdk:8-jdk-alpine 
RUN apk --no-cache add curl
COPY build/libs/*all.jar @app.name@.jar
CMD java ${JAVA_OPTS} -jar @app.name@.jar
