FROM oracle/graalvm-ce:19.2.0.1 as graalvm
COPY . /home/app/@app.name@
WORKDIR /home/app/@app.name@
RUN gu install native-image
RUN native-image --no-server -cp @jarPath@

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@ .
ENTRYPOINT ["./@app.name@"]
