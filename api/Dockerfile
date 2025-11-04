# --- Estágio 1: "Build" (O Construtor) ---
FROM eclipse-temurin:17-jdk-focal AS builder
WORKDIR /workspace
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw clean package -DskipTests

# --- Estágio 2: "Runtime" (A Imagem Final) ---
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=builder /workspace/target/api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# 🚨 --- CORREÇÃO AQUI --- 🚨
# Adiciona o parâmetro "-Djava.net.preferIPv4Stack=true"
# para forçar o Java a usar a rede IPv4 e encontrar o Supabase.
ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "app.jar"]