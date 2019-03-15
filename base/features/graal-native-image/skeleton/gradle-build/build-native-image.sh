./gradlew assemble
native-image --no-server \
             --class-path build/libs/@app.name@-*.jar \
             -H:+AllowVMInspection 
