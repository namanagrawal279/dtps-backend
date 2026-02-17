# -------- Stage 1: Build --------
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# -------- Stage 2: Runtime --------
FROM gcr.io/distroless/java21-debian12

WORKDIR /app

COPY --from=builder /app/target/dtps-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

USER nonroot

ENTRYPOINT ["java","-jar","app.jar"]
