# First stage
FROM eclipse-temurin:21-jdk as builder

# Set the working directory in the container
WORKDIR /opt/app

# Create a non-root user
RUN addgroup build; adduser --ingroup build --disabled-password user

# Give necessary permissions to the user
RUN chown -R user:build /opt/app

# Switch to the non-root user
USER user

# Copy the Gradle wrapper files
COPY gradlew .
COPY gradle gradle

# Copy the build configuration files
COPY build.gradle .
COPY settings.gradle .

# Download dependencies
RUN ./gradlew --no-daemon dependencies

# Copy the application source code
COPY src src

# Build the application
RUN ./gradlew --no-daemon build

# Second stage
FROM eclipse-temurin:latest

# Set the working directory in the container
WORKDIR /opt/app

# Copy jar file from the first stage
COPY --from=builder /opt/app/build/libs/*.jar my-app.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "my-app.jar"]
