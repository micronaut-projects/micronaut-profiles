FROM java:openjdk-8u111-alpine
RUN apk --no-cache add curl
CMD java ${JAVA_OPTS} -jar @app.name@-0.1-all.jar
COPY build/libs/@app.name@-0.1-all.jar .