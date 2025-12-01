# --------------------------------
# Stage 1: Build the Spring Boot JAR
# --------------------------------
FROM maven:3.8.6-eclipse-temurin-17 as build

WORKDIR /app

# Copy entire repository into container
COPY . .

# Build the project inside FitnessApp folder
RUN mvn -f FitnessApp/pom.xml clean package -DskipTests


# --------------------------------
# Stage 2: Run the Spring Boot app
# --------------------------------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from the first stage
COPY --from=build /app/FitnessApp/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
