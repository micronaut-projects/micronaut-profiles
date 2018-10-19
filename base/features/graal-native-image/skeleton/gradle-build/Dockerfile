FROM oracle/graalvm-ce:1.0.0-rc7
EXPOSE 8080
COPY build/libs/*-all.jar @app.name@.jar
ADD . build
RUN java -cp target/@app.name@.jar io.micronaut.graal.reflect.GraalClassLoadingAnalyzer 
RUN native-image --no-server \
             --class-path target/@app.name@.jar \
			 -H:ReflectionConfigurationFiles=target/reflect.json \
			 -H:EnableURLProtocols=http \
			 -H:IncludeResources="logback.xml|application.yml|META-INF/services/*.*" \
			 -H:Name=@app.name@ \
			 -H:Class=@defaultPackage@.Application \
			 -H:+ReportUnsupportedElementsAtRuntime \
			 -H:+AllowVMInspection \
			 --rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder,javax.net.ssl.SSLContext' \
			 --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom
CMD ./@app-name@
