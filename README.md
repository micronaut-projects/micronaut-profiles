Micronaut Profiles
=========================

This repository contains the profiles able to be used when creating Micronaut applications

A profile is a set of application templates and commands that enable distinct development environments for the Micronaut framework.

Build profiles
===

First, build micronaut CLI from source https://docs.micronaut.io/latest/guide/index.html#buildSource

After that, clone https://github.com/micronaut-projects/micronaut-profiles side by side and execute:

```bash
./gradlew pTML
```

to build profiles. Then you should be able to do `mn create-app my-app --features your-features` to create an app.

Every time you make a change to `micronaut-profiles` you need to do `./gradlew clean pTML` again
