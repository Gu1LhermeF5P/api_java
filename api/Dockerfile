# --- Estágio 1: "Build" (O Construtor) ---
# Usa a imagem oficial do Java 17 (JDK completo)
FROM eclipse-temurin:17-jdk-focal AS builder

# Define o diretório de trabalho
WORKDIR /workspace

# Copia o wrapper do Maven e o pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Baixa as dependências
RUN ./mvnw dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Constroi o JAR, pulando os testes
RUN ./mvnw clean package -DskipTests


# --- Estágio 2: "Runtime" (A Imagem Final) ---
# Usa uma imagem Java 17 (apenas o JRE - mais leve)
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copia o JAR construído no Estágio 1 para a imagem final
# Ele pega o "api-0.0.1-SNAPSHOT.jar" e renomeia para "app.jar"
COPY --from=builder /workspace/target/api-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]