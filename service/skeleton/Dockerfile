FROM openjdk:14-alpine
COPY @jarPath@ @app.name@.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "@app.name@.jar"]
