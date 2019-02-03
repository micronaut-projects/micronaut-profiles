FROM maven:3.6.0-jdk-8-alpine as builder
COPY  . /home/app/
WORKDIR /home/app
RUN mvn install

FROM oracle/graalvm-ce:1.0.0-rc11 as graalvm
COPY --from=builder /home/app/ /home/app/
WORKDIR /home/app
RUN ./build-native-image.sh


FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@ .
ENTRYPOINT ["./@app.name@"]
