#!/bin/sh
if [[ -z "${GRAALVM_HOME}" ]]; then
  echo "Set the GRAALVM_HOME environment variable to the home directory of your GraalVM installation."
  exit -1;
fi

DISABLE_COMPRESSED=""

if [[ -z "native-image -H:PrintFlags= | grep 'UseCompressedReferences'" ]]; then
  DISABLE_COMPRESSED="-H:-UseCompressedReferences"
fi

$GRAALVM_HOME/bin/native-image $DISABLE_COMPRESSED -jar @jarPath@ @app.name@

echo
echo
echo "To run the resulting image with a young generation size of 24Mb and a maximum of 128Mb of memory:"
echo "    $ ./@app.name@ -Xmn24m -Xmx128m"