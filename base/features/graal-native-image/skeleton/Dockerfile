FROM oracle/graalvm-ce:19.1.1 as graalvm
COPY . /home/app/@app.name@
WORKDIR /home/app/@app.name@
RUN gu install native-image

# Using --static to statically link libc and enable execution in a scratch docker container
RUN native-image --no-server --static -jar @jarPath@ @app.name@

FROM scratch
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@ .

# Setting young generation size to 24 Mb via -Xmn24m.
# Smaller values decrease memory footprint and throughput.
# Higher values increase memory footprint and throughput. 
ENTRYPOINT ["./@app.name@ -Xmn24m"]