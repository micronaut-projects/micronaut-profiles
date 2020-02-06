Micronaut Profiles
=========================

This repository contains the profiles able to be used when creating Micronaut applications

A profile is a set of application templates and commands that enable distinct development environments for the Micronaut framework.

Testing locally
===

1. Note the version currently defined in this project, and publish it to Maven local:

    ```bash
    ./gradlew pTML
    ```

2. Clone [Micronaut Core](https://github.com/micronaut-projects/micronaut-core), and set the profile versions in 
   `bom/profiles.properties` to the version you noted above.
   
3. Install locally:

    ```bash
    ./gradlew bom:pTML cliZip
    ```
   
4. Point SDKMan to your local version:

    ```bash
    sdk install micronaut dev /path/to/micronaut-core/cli/build
    sdk use micronaut dev
    ```
   
5. Done!

Generating test applications
===

```bash
rm -rf apps
mkdir apps
cd apps

mn create-app micronaut.java-maven-junit -l java -b maven -f junit
mn create-app micronaut.java-maven-spock -l java -b maven -f spock
mn create-app micronaut.java-gradle-junit -l java -b gradle -f junit
mn create-app micronaut.java-gradle-spock -l java -b gradle -f spock
mn create-app micronaut.groovy-gradle-spock -l groovy -b gradle -f spock
mn create-app micronaut.kotlin-maven-junit -l kotlin -b maven -f junit
mn create-app micronaut.kotlin-maven-spock -l kotlin -b maven -f spock
mn create-app micronaut.kotlin-maven-kotlintest -l kotlin -b maven -f kotlintest
mn create-app micronaut.kotlin-gradle-junit -l kotlin -b gradle -f junit
mn create-app micronaut.kotlin-gradle-spock -l kotlin -b gradle -f spock
mn create-app micronaut.kotlin-gradle-kotlintest -l kotlin -b gradle -f kotlintest

for app in `ls`; do cd $app ; mn create-test mytest ; cd .. ; done

for app in `find . -name "*gradle*" -type d -maxdepth 1` ; do cd $app ; ./gradlew test ; cd .. ; done
for app in `find . -name "*maven*" -type d -maxdepth 1` ; do cd $app ; ./mvnw test ; cd .. ; done
```
