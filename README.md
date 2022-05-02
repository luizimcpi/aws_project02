# AWS SQS CONSUMER AND DYNAMODB

## TODO
```
- create docker-compose
```


## Pre requirements
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [JDK 11](https://adoptium.net/temurin/releases) 

## Follow this steps below to run application local

### 1- SPRING PROFILES
```
CONFIGURE ENV VAR: SPRING_PROFILES_ACTIVE=local
```

### 2- LOCALSTACK
```
docker run --rm -it -p 4566:4566 -p 4571:4571 localstack/localstack

*If you don't have docker in your machine install
```

### 3- SEND SQS MESSAGE WITH TEMPLATE
```
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/queue/product-events --message-body "{\"Message\": {\"eventType\":\"PRODUCT_CREATED\",\"data\":\"teste criação\"},\"Type\":\"CREATION\",\"TopiArn\":\"arn:1234556666:1122323\",\"Timestamp\":\"1651514568622\",\"MessageId\":\"e5872d2b-1985-425a-b2a2-50bec46435e5\"}"
```

### 4- CHECK PERSISTED DYNAMO EVENTS USING ENDPOINT
```
curl --location --request GET 'localhost:9090/api/events'
```