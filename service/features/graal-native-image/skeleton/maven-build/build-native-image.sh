native-image --class-path target/@app.name@-0.1.jar \
			 -H:ReflectionConfigurationFiles=target/reflect.json \
			 -H:EnableURLProtocols=http \
			 -H:IncludeResources="logback.xml|application.yml|META-INF/services/*.*" \
			 -H:Name=@app.name@ \
			 -H:Class=@defaultPackage@.Application \
			 -H:+ReportUnsupportedElementsAtRuntime \
			 -H:+AllowVMInspection \
			 --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder