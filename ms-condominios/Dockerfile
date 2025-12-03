# Estágio 1: Build (Compilação)
# imagem com Maven para gerar o .jar
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila o projeto e pular os testes para ser mais rápido
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Execução)
# Usamos uma imagem leve apenas com o Java (JRE) para rodar
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copia o .jar gerado no estágio anterior para cá
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8082 (apenas documentação, o docker-compose que libera de verdade)
EXPOSE 8082

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]