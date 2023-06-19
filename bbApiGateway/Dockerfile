FROM ubuntu:latest
LABEL authors="Prakash Roy"
FROM openjdk:17-oracle
WORKDIR /opt
ARG VERSION
ARG APP_NAME
COPY build/libs/${APP_NAME}-${VERSION}.jar bb-api-gateway.jar
EXPOSE 8087
CMD ["java", "-jar", "bb-api-gateway.jar"]