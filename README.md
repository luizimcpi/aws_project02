# AWS SQS CONSUMER AND DYNAMODB

## TODO
```
- fix message template to execute tests
```

## Pre requirements
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [JDK 11](https://adoptium.net/temurin/releases) 
- [AWS CLI](https://aws.amazon.com/pt/cli/)

## Follow this steps below to run application local

## USING DOCKER

```
Run this commands inside project root folder
1 -> ./gradlew build 
2 -> docker-compose up -d
3 -> java -jar build/libs/aws_project02-1.0.0.jar --spring.profiles.active=local
```

## Testing

### USE AWS PROJECT 01 - SEE README.md
[AWS-PUBLISHER-PROJETC](https://github.com/luizimcpi/aws_project01)

or

### 1- SEND SQS MESSAGE WITH TEMPLATE
```
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/queue/product-events --message-body "{"Type": "Notification", "MessageId": "9db951e0-a869-4980-8d7f-39f9bbf85d2d", "TopicArn": "arn:aws:sns:us-east-1:000000000000:product-events", "Message": "{\"eventType\":\"PRODUCT_CREATED\",\"data\":\"{\\\"productId\\\":1,\\\"code\\\":\\\"COD1\\\",\\\"username\\\":\\\"matilde\\\"}\"}", "Timestamp": "2022-05-03T20:58:22.297Z"}"

aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/queue/product-events --message-body "{\"Type\": \"Notification\", \"MessageId\": \"9db951e0-a869-4980-8d7f-39f9bbf85d2d\", \"TopicArn\": \"arn:aws:sns:us-east-1:000000000000:product-events\", \"Message\": "{\"eventType\":\"PRODUCT_CREATED\",\"data\":\"{\\\"productId\\\":1,\\\"code\\\":\\\"COD1\\\",\\\"username\\\":\\\"matilde\\\"}\"}", \"Timestamp\": \"2022-05-03T20:58:22.297Z\"}"
```

### 2- CHECK PERSISTED DYNAMO EVENTS USING ENDPOINT
```
curl --location --request GET 'localhost:9090/api/events'
```