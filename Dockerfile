FROM eclipse-temurin:21-jdk-jammy AS build
ARG JAR_FILE=target/order-ms-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]