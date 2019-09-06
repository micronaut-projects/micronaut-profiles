#!/bin/sh

if [[ -z "${GRAALVM_HOME}" ]]; then
  echo "Set the GRAALVM_HOME environment variable to the home directory of your GraalVM installation."
  exit -1;
fi

if [[ -z "native-image --help | grep 'pgo-instrument'" ]]; then
  echo "Profile-guided optimizations require the GraalVM Enterprise Edition (EE)."
  exit -1;
fi

echo "Building instrumented native image."
$GRAALVM_HOME/bin/native-image -H:-UseCompressedReferences --pgo-instrument -jar @jarPath@ @app.name@

echo "Exercising test workload to create profiles."
./@app.name@ &

echo "Sending 10000 requests to localhost:8080. Modify this test workload according to your application."
curl -s http://localhost:8080/?[1-10000] -o /dev/null
MY_PID=$!
kill $MY_PID

if [ ! -f ./default.iprof ]; then
    echo "Error: File default.iprof with profile information could not be found."
    exit -1;
fi

echo "Building final image taking the collected profile into account."
$GRAALVM_HOME/bin/native-image -H:-UseCompressedReferences --pgo=default.iprof -jar @jarPath@ @app.name@

echo
echo
echo "To run the resulting image with a young generation size of 24Mb and a maximum of 128Mb of memory:"
echo "    $ ./@app.name@ -Xmn24m -Xmx128m"