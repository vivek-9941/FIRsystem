FROM ubuntu:latest

LABEL authors="Shrihari"

# Install necessary packages
RUN apt-get update && \
    apt-get install -y redis-server && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Expose default Redis port
EXPOSE 6379

# Start Redis server in the foreground
ENTRYPOINT ["redis-server", "--protected-mode", "no"]
