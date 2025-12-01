# Use Java 17 (correct and supported)
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Maven wrapper and project files
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Pre-download dependencies (faster builds)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build project
RUN ./mvnw package -DskipTests

EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/*.jar"]
