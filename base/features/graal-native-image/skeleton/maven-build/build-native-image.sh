./mvnw package
native-image --no-server \
             --class-path target/@app.name@-*.jar \
             -H:Name=@app.name@ \
             -H:Class=@defaultPackage@.Application \
             -H:+AllowVMInspection 
