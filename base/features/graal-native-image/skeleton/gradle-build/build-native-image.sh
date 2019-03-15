./gradlew assemble
native-image --no-server \
             --class-path build/libs/@app.name@-*.jar \
             -H:IncludeResources="logback.xml|application.yml" \
             -H:Name=@app.name@ \
             -H:Class=@defaultPackage@.Application \
             -H:+AllowVMInspection 
