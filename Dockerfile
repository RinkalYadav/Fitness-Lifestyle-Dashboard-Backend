# Use Java 17 (required for Spring Boot)
FROM eclipse-temurin:17-jdk-alpine

# Working directory inside the container
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Download dependencies (helps in faster builds)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests

# Expose backend port
EXPOSE 8080

# Run the packaged JAR file
CMD ["java", "-jar", "target/*.jar"]
