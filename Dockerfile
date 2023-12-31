FROM openjdk:19-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/CarDatabase-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]