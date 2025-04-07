# Use imagem do Java 17 com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia os arquivos e resolve dependências
COPY . .

RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=builder /app/target/cliente-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
