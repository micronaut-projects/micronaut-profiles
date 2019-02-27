#!/bin/sh
GRAALVM_HOME=${GRAALVM_HOME:-/usr/lib/graalvm}
${GRAALVM_HOME}/bin/java -cp build/libs/@app.name@-*.jar io.micronaut.graal.reflect.GraalClassLoadingAnalyzer
${GRAALVM_HOME}/bin/native-image --no-server \
             --class-path build/libs/@app.name@-*.jar \
             -H:ReflectionConfigurationFiles=src/main/resources/reflect.json,src/main/resources/netty-reflect.json,build/reflect.json \
             -H:EnableURLProtocols=http \
             -H:IncludeResources="logback.xml|application.yml" \
             -H:Name=server \
             -H:Class=io.micronaut.function.aws.runtime.MicronautLambdaRuntime \
             -H:+ReportUnsupportedElementsAtRuntime \
             -H:-AllowVMInspection \
             --allow-incomplete-classpath \
             --rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder,javax.net.ssl.SSLContext' \
             --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom,com.sun.jndi.dns.DnsClient,io.micronaut.function.aws.proxy.MicronautLambdaContainerHandler,com.amazonaws.serverless.proxy.internal.LambdaContainerHandler,io.netty.handler.ssl.JdkNpnApplicationProtocolNegotiator,io.netty.handler.ssl.ReferenceCountedOpenSslEngine

