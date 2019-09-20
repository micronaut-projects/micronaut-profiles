FROM gradle:5.1.1-jdk-alpine as builder
COPY --chown=gradle:gradle . /home/application
WORKDIR /home/application
RUN gradle build --no-daemon

FROM amazonlinux:2017.03.1.20170812 as graalvm
# install graal to amazon linux.
ENV LANG=en_US.UTF-8


RUN yum install -y gcc gcc-c++ libc6-dev  zlib1g-dev curl bash zlib zlib-devel zip  \
#    && yum install -y libcxx libcxx-devel llvm-toolset-7 \
    && rm -rf /var/cache/yum


ENV GRAAL_VERSION 19.2.0.1
ENV GRAAL_FILENAME graalvm-ce-linux-amd64-${GRAAL_VERSION}.tar.gz

RUN curl -4 -L https://github.com/oracle/graal/releases/download/vm-${GRAAL_VERSION}/graalvm-ce-linux-amd64-${GRAAL_VERSION}.tar.gz -o /tmp/${GRAAL_FILENAME}

RUN tar -zxvf /tmp/${GRAAL_FILENAME} -C /tmp \
    && mv /tmp/graalvm-ce-${GRAAL_VERSION} /usr/lib/graalvm

RUN rm -rf /tmp/*
CMD ["/usr/lib/graalvm/bin/native-image"]

FROM graalvm
COPY --from=builder /home/application/ /home/application/
WORKDIR /home/application
RUN /usr/lib/graalvm/bin/gu install native-image
RUN /usr/lib/graalvm/bin/native-image --no-server -cp @jarPath@ 
RUN chmod 755 bootstrap
RUN chmod 755 server
RUN zip -j function.zip bootstrap server 
EXPOSE 8080
ENTRYPOINT ["/home/application/server"]
