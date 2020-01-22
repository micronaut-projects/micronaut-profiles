FROM oracle/graalvm-ce:19.3.1-java8 as graalvm
#FROM oracle/graalvm-ce:19.3.1-java11 as graalvm # For JDK 11
RUN gu install native-image
RUN native-image --no-server --static -cp @jarPath@
COPY . /home/app/@app.name@
WORKDIR /home/app/@app.name@

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@/@app.name@ /app/@app.name@
ENTRYPOINT ["/app/@app.name@", "-Djava.library.path=/app"]
