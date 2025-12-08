# Estágio de Build
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Pula testes para agilizar o build local
RUN mvn clean package -DskipTests

# Estágio de Execução
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# A porta exposta é apenas documentação, o compose define a real
ENTRYPOINT ["java", "-jar", "app.jar"]