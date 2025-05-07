#!/bin/bash

# Update MySQL to use port 3307
sed -i 's/3306/3307/' /etc/mysql/mysql.conf.d/mysqld.cnf

# Start Redis
redis-server --protected-mode no &

# Start MySQL service
service mysql start

# Set root password and create database
echo "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '${MYSQL_ROOT_PASSWORD}'; FLUSH PRIVILEGES;" | mysql -u root
echo "CREATE DATABASE IF NOT EXISTS efir;" | mysql -u root -p"${MYSQL_ROOT_PASSWORD}"

# Wait for DB to be ready
sleep 5

# Run the Spring Boot application with the datasource URL passed as a command-line argument
java -jar /app/FIRsystem.jar --spring.datasource.url=jdbc:mysql://localhost:3307/efir --spring.datasource.username=root --spring.datasource.password=${MYSQL_ROOT_PASSWORD}
