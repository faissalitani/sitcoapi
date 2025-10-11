FROM maven:3.9.8-eclipse-temurin-17-alpine
WORKDIR /app

RUN apk add --no-cache bash

# Copy Maven wrapper and pom.xml (for dependency caching)
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./

# Download dependencies (so they’re already cached)
RUN ./mvnw dependency:go-offline

# Default command for development — can be overridden
CMD ["./mvnw", "spring-boot:run"]
