FROM maven:3.6.0-jdk-8-alpine as builder
COPY  . /home/app/
WORKDIR /home/app
RUN mvn install

FROM oracle/graalvm-ce:1.0.0-rc11 as graalvm
COPY --from=builder /home/app/ /home/app/
WORKDIR /home/app
RUN java -cp target/@app.name@-*.jar \
            io.micronaut.graal.reflect.GraalClassLoadingAnalyzer \
            reflect.json
RUN native-image --no-server \
                 --class-path target/@app.name@-*.jar \
                 -H:ReflectionConfigurationFiles=/home/app/reflect.json \
                 -H:EnableURLProtocols=http \
                 -H:IncludeResources='logback.xml|application.yml|META-INF/services/*.*' \
                 -H:+ReportUnsupportedElementsAtRuntime \
                 -H:+AllowVMInspection \
                 --rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder',javax.net.ssl.SSLContext \
                 --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom \
                 -H:-UseServiceLoaderFeature \
                 --allow-incomplete-classpath \
                 -H:Name=@app.name@ \
                 -H:Class=@defaultPackage@.Application


FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/@app.name@ .
ENTRYPOINT ["./@app.name@"]
