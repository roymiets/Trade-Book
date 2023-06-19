FROM ubuntu:latest
LABEL authors="Prakash Roy"
FROM openjdk:17-oracle
WORKDIR /opt
ARG VERSION
ARG APP_NAME
COPY build/libs/${APP_NAME}-${VERSION}.jar bb-search-api.jar
EXPOSE 8089
CMD ["java", "-jar", "bb-search-api.jar"]