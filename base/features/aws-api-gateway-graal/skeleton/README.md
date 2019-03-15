# Micronaut + GraalVM Native + AWS Lambda Custom Runtime 

This example demonstrates how to use Micronaut AWS API Gateway Proxy support and GraalVM to construct a custom runtime that runs native images or Lambda.

The `Dockerfile` contains the build to build the native image and it can be built with:

```bash
$ docker build . -t @app.name@
$ mkdir -p build
$ docker run --rm --entrypoint cat @app.name@  /home/application/function.zip > build/function.zip
```

Which will add the function deployment ZIP file to `build/function.zip`. You can run function locally using [SAM](https://github.com/awslabs/aws-sam-cli/)

```bash
$ docker build . -t @app.name@
$ ./sam-local.sh
$ curl http://localhost:3000/ping
```

Or you can deploy it to AWS via the console or CLI:

```bash
aws lambda create-function --function-name @app.name@ \
--zip-file fileb://build/function.zip --handler function.handler --runtime provided \
--role ARN_OF_LAMBDA_ROLE
```

To create role for AWS Lambda, use following code:
```bash
```

The function can be invoked by sending an API Gateway Proxy request. For example:

```bash
aws lambda invoke --function-name @app.name@ --payload '{"resource": "/{proxy+}", "path": "/ping", "httpMethod": "GET"}' build/response.txt
cat build/response.txt
```

and response should be something like:

```json
{"statusCode":200,"multiValueHeaders":{},"body":"{\"pong\":true, \"graal\": true}","isBase64Encoded":false}
```

Example controller responding with /ping are included in template.

You should replace the `/ping` path entry with the URI the controller endpoint you wish to invoke.
