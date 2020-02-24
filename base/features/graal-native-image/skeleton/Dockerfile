FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm # For JDK 11
RUN gu install native-image

COPY . /home/app/@app.name@
WORKDIR /home/app/@app.name@

RUN native-image --no-server --static -cp @jarPath@

FROM scratch
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@/@app.name@ /app/@app.name@
ENTRYPOINT ["/app/@app.name@", "-Djava.library.path=/app"]
