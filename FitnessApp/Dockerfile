FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy maven wrapper and project files
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Give permission to mvnw
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy src files
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Find the built jar automatically
RUN JAR_FILE=$(ls target/*.jar | head -n 1) && echo $JAR_FILE && mv $JAR_FILE app.jar

EXPOSE 8080

# Run renamed jar
CMD ["java", "-jar", "app.jar"]
