FROM oracle/graalvm-ce:1.0.0-rc13 as graalvm
COPY . /home/app/@app.name@
WORKDIR /home/app/@app.name@
RUN ./build-native-image.sh

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@ .
ENTRYPOINT ["./@app.name@"]
