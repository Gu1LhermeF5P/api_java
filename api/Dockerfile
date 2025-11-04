# --- Estágio 1: "Build" (O Construtor) ---
FROM eclipse-temurin:17-jdk-focal AS builder

WORKDIR /workspace

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 🚨 --- CORREÇÃO ADICIONADA AQUI --- 🚨
# Dá permissão de execução ao script do Maven
RUN chmod +x mvnw
# ------------------------------------

# Baixa as dependências
RUN ./mvnw dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Constroi o JAR, pulando os testes
RUN ./mvnw clean package -DskipTests


# --- Estágio 2: "Runtime" (A Imagem Final) ---
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copia o JAR construído no Estágio 1
COPY --from=builder /workspace/target/api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]