FROM ubuntu:latest

LABEL authors="Shrihari"

# Install necessary packages
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y \
    redis-server \
    mysql-server \
    openjdk-17-jdk \
    curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set environment variables
ENV REDIS_PORT=6379 \
    MYSQL_PORT=3307 \
    SPRING_PORT=8080 \
    MYSQL_ROOT_PASSWORD=8999617581@Sh

# Expose ports
EXPOSE ${REDIS_PORT} ${MYSQL_PORT} ${SPRING_PORT}

# Copy application and entrypoint
COPY target/FIRsystem-0.0.1-SNAPSHOT.jar /app/FIRsystem.jar
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
