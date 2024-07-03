# Stage 1: Build with Maven
FROM amazoncorretto:17-alpine-jdk AS builder

WORKDIR /tmp/app

# Copy the Maven Wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Go offline - fetch all dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Stage 2: Package the application
FROM amazoncorretto:17-alpine-jdk

WORKDIR /workspace

EXPOSE 8080 8000

COPY --from=builder /tmp/app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
