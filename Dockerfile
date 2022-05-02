FROM adoptopenjdk/openjdk11
LABEL maintainer="luizimcpi@gmail.com"
VOLUME /tmp
ARG JAR_FILE=build/libs/aws_project02-*.jar
ADD ${JAR_FILE} aws_project02.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/aws_project02.jar"]