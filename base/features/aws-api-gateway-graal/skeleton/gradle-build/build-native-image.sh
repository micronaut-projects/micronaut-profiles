#!/bin/sh
GRAALVM_HOME=${GRAALVM_HOME:-/usr/lib/graalvm}
${GRAALVM_HOME}/bin/native-image --no-server \
             --class-path build/libs/@app.name@-*.jar \
             -H:-AllowVMInspection 

