FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY /target/sf-integration-0.0.1.jar /app
CMD ["java", "-jar", "sf-integration-0.0.1.jar"]

