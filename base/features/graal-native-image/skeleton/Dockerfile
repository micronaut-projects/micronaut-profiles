FROM oracle/graalvm-ce:19.2.1 as graalvm
COPY . /home/app/@app.name@
WORKDIR /home/app/@app.name@
RUN gu install native-image
RUN native-image --no-server --static -cp @jarPath@

FROM scratch
EXPOSE 8080
COPY --from=graalvm /opt/graalvm-ce-19.2.1/jre/lib/amd64/libsunec.so /app/libsunec.so
COPY --from=graalvm /home/app/@app.name@/@app.name@ /app/@app.name@
ENTRYPOINT ["/app/@app.name@", "-Djava.library.path=/app"]
