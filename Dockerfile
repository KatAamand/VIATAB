FROM openjdk:21

# Setting working directory in container
WORKDIR /backend

# Copying mvn pom.xml for caching dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Giving execution rights for mvnw and getting dependencies with maven wrapper
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy rest of project into container
COPY src ./src

# Build project
RUN ./mvnw package -DskipTests
RUN mv target/*.jar target/backend.jar

# Exposing standard Spring Boot-port
EXPOSE 8080

# Run Spring Boot jar-file
CMD ["java", "-jar", "target/backend.jar"]

