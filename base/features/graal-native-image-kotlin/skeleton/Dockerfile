FROM oracle/graalvm-ce:19.3.0-java8 as graalvm
#FROM oracle/graalvm-ce:19.3.0-java11 as graalvm # For JDK 11
COPY . /home/app/@app.name@
WORKDIR /home/app/@app.name@
RUN gu install native-image
RUN native-image --no-server --static -cp @jarPath@

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@/@app.name@ /app/@app.name@
ENTRYPOINT ["/app/@app.name@", "-Djava.library.path=/app"]
