#!/bin/bash

# Optionally skip building function.zip, in case you've already done that and haven't made changes
if [ ! "${1}" == "skip-build" ]; then
  echo "Building the function.zip..."
  docker build . -t prime-finder
  mkdir -p build
  docker run --rm --entrypoint cat prime-finder  /home/application/function.zip > build/function.zip
fi

# Check for role, create it if there isn't one already
ROLE_NAME=lambda-basic-role
ROLE_ARN=`aws iam get-role --role-name ${ROLE_NAME} | grep Arn | cut -d'"' -f4`
if [ "${ROLE_ARN}" == "" ]; then
    echo "No role ${ROLE_NAME} exists!"
    echo "Create one using: "
    echo "> aws iam create-role --role-name ${ROLE_NAME} --assume-role-policy-document file://lambda-role-policy.json"
    echo "> aws iam attach-role-policy --role-name ${ROLE_NAME} --policy-arn arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
    exit 1
fi

# PUT YOUR OWN BUCKET NAME HERE
S3_BUCKET_NAME=micronaut-graal-demo

# Check to ensure the bucket exists, create it if it does not
if aws s3api head-bucket --bucket "${S3_BUCKET_NAME}" 2>&1 | grep -q "Not Found"; then
  echo "Bucket not found, creating it..."
  aws s3 mb s3://${S3_BUCKET_NAME}
fi

# Deploy to CloudFormation
aws cloudformation package --template-file sam.yaml --output-template-file packaged-sam.yaml --s3-bucket ${S3_BUCKET_NAME}
aws cloudformation deploy --template-file ./packaged-sam.yaml --stack-name MicronautGraalVmDemo --capabilities CAPABILITY_IAM
aws cloudformation describe-stacks --stack-name MicronautGraalVmDemo --query "Stacks[0].Outputs[0].OutputValue"| cut -d \" -f 2