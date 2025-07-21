FROM eclipse-temurin:17-jdk-alpine

# Create app directory
WORKDIR /app

# Copy jar file
COPY target/FIRsystem-0.0.1-SNAPSHOT.jar app.jar

# Copy the config mount location path (will be overridden in volume at runtime)
VOLUME /config

# Entry point with external config
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/resources/application.properties"]
