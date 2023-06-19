FROM ubuntu:latest
LABEL authors="Prakash Roy"
FROM openjdk:17-oracle
WORKDIR /opt
ARG VERSION
ARG APP_NAME
COPY build/libs/${APP_NAME}-${VERSION}.jar bb-equity-api.jar
EXPOSE 8088
CMD ["java", "-jar", "bb-equity-api.jar"]


