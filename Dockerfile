# Use a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Install bash as well for waiting script
RUN apt-get update && apt-get install -y bash

# Set working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Waiting script
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Expose port (if your app runs on 8080)
EXPOSE 8080

# temp
RUN echo "SPRING_REDIS_HOST=$SPRING_REDIS_HOST"

# Wait for MySQL on hostname "mysql" port 3306, then start the app
ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--", "/wait-for-it.sh", "redis:6379", "--", "java", "-jar", "app.jar"]
