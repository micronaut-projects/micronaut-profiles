./mvnw package
native-image --no-server \
             --class-path target/@app.name@-*.jar \
             -H:+AllowVMInspection 
