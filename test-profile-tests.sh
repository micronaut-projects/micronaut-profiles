#!/bin/sh
set -e

rm -rf ~/.m2/repository/io/micronaut/profiles
./gradlew clean pTML
cd /tmp
rm -rf test-*
mn create-app test-service-junit5 --features=junit
mn create-app test-service-spock -l groovy --features=spock
mn create-app test-service-spek -l kotlin --features=spek
mn create-cli-app test-cli-junit5
mn create-app test-function-aws-junit5 --profile=function-aws
mn create-app test-function-aws-spock -l groovy --profile=function-aws

cd test-service-junit5
mn create-test simple
mn create-controller foo
./gradlew build

cd ../test-service-spock
mn create-test simple
mn create-controller foo
./gradlew build

cd ../test-service-spek
mn create-test simple
mn create-controller foo
./gradlew build

cd ../test-cli-junit5
./gradlew build

cd ../test-function-aws-junit5
./gradlew build

cd ../test-function-aws-spock
./gradlew build

cd ~/Micronaut/micronaut-profiles