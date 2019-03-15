#!/bin/sh
GRAALVM_HOME=${GRAALVM_HOME:-/usr/lib/graalvm}
${GRAALVM_HOME}/bin/native-image --no-server \
             --class-path target/@app.name@-*.jar \
             -H:IncludeResources="logback.xml|application.yml" \
             -H:Name=server \
             -H:Class=io.micronaut.function.aws.runtime.MicronautLambdaRuntime 
             -H:-AllowVMInspection 

