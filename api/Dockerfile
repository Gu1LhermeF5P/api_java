# Estágio 1: Build da Aplicação
# Usa a imagem oficial do Maven com o Java 17 para construir o .jar
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Baixa as dependências offline antes do build principal
RUN ./mvnw dependency:go-offline
COPY src ./src
# Comando para fazer o build do projeto e pular os testes (para deploy)
RUN ./mvnw clean package -DskipTests

# Estágio 2: Imagem Final de Produção
# Usa uma imagem leve (JRE) apenas para rodar o app
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Argumento para pegar o arquivo .jar criado no estágio de build
ARG JAR_FILE=target/*.jar
COPY --from=builder /app/${JAR_FILE} app.jar

# Define a porta do servidor web
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]