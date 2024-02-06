# Builder stage
FROM maven:3.8.5-openjdk-17 as Builder
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/
# Skip tests if necessary to avoid MongoDB connection attempt
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-alpine

ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /app/
# Copy the built application JAR from the builder stage
COPY --from=Builder /build/target/kafka-made-simple-${VERSION}.jar /app/application.jar
# Make port 8080 available
EXPOSE 8080
CMD ["java", "-jar", "/app/application.jar"]

