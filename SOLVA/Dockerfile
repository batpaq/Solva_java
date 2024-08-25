FROM openjdk:22-jdk-slim-buster
WORKDIR /app
COPY target/SOLVA-0.0.1-SNAPSHOT.jar /app/phrases.jar
ENTRYPOINT ["java","-jar","phrases.jar"]