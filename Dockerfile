# ----------------------------
# Stage 1: Build the JAR file
# ----------------------------
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

# Copy entire project
COPY . .

# Build inside FitnessApp folder
RUN mvn -f FitnessApp/pom.xml clean package -DskipTests


# ----------------------------
# Stage 2: Run the JAR file
# ----------------------------
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/FitnessApp/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
