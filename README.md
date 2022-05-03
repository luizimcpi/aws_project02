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

### 1- SEND SQS MESSAGE WITH TEMPLATE
```
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/queue/product-events --message-body "{\"Message\":{\"eventType\":\"PRODUCT_CREATED\",\"data\": {\"productId\": 1,\"code\":\"CODE1\",\"username\":\"matilde\"}},\"Type\":\"Teste\",\"TopicArn\":\"arn:test\",\"Timestamp\":\"1651514568622\",\"MessageId\":\"45444561-5317-4fbd-af68-b4269582ad51\"}"
```

### 2- CHECK PERSISTED DYNAMO EVENTS USING ENDPOINT
```
curl --location --request GET 'localhost:9090/api/events'
```