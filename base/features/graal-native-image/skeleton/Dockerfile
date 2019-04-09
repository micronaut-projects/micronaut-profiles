FROM oracle/graalvm-ce:1.0.0-rc15 as graalvm
COPY . /home/app/@app.name@
WORKDIR /home/app/@app.name@
RUN native-image --no-server -cp @jarPath@

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@ .
ENTRYPOINT ["./@app.name@"]
