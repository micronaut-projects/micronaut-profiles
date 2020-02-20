FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
COPY @jarPath@ @app.name@.jar
EXPOSE 8080
CMD java -Dcom.sun.management.jmxremote ${JAVA_OPTS} -jar @app.name@.jar
