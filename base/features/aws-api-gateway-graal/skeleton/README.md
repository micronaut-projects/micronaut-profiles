# Micronaut + GraalVM Native + AWS Lambda Custom Runtime 

This example demonstrates how to use Micronaut AWS API Gateway Proxy support and GraalVM to construct a custom runtime that runs native images or Lambda.

The `Dockerfile` contains the build to build the native image and it can be built with:

```bash
$ docker build . -t @app.name@
$ mkdir build
$ docker run --rm --entrypoint cat @app.name@  /home/application/function.zip > build/function.zip
```

Which will add the function deployment ZIP file to `build/function.zip`. You can then deploy via the AWS console or CLI:

```bash
aws lambda create-function --function-name @app.name@ \
--zip-file fileb://build/function.zip --handler function.handler --runtime provided \
--role arn:aws:iam::881337894647:role/lambda_basic_execution
```

The function can be invoked by sending an API Gateway Proxy request. For example:

```bash
aws lambda invoke --function-name @app.name@ --payload '{"resource": "/{proxy+}", "path": "/ping", "httpMethod": "GET"}' build/response.txt
cat build/response.txt
```

You should replace the `/ping` path entry with the URI the controller endpoint you wish to invoke.
