# Use official Maven image to build the code
FROM maven:3.8.6-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy the project
COPY . .

# Go inside FitnessApp folder and build
RUN mvn -f FitnessApp/pom.xml clean package -DskipTests


# Run stage
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy built JAR from previous stage
COPY --from=build /app/FitnessApp/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Start Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
