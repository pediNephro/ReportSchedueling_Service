# Dockerfile — Microservice Spring Boot
# Placer à la racine de chaque projet Spring Boot (dossier du microservice)
# Etape 1 : compilation avec Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B # cache des dépendances
COPY src ./src
RUN mvn package -DskipTests
# Etape 2 : image finale légère (sans Maven ni sources)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8084 # adapter selon le port du microservice
ENTRYPOINT ["java", "-jar", "app.jar"]