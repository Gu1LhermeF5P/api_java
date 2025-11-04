# --- Estágio 1: "Build" (O Construtor) ---
# Usamos uma imagem oficial do Java 17 JDK (Kit de Desenvolvimento)
FROM eclipse-temurin:17-jdk-focal AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /workspace

# --- Configuração do MAVEN ---
# Copia o wrapper do Maven e o pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Baixa as dependências (Isso acelera builds futuros se as dependências não mudarem)
RUN ./mvnw dependency:go-offline

# Copia o código-fonte da sua aplicação
COPY src ./src

# Constroi o JAR (pulando os testes, pois devem ser feitos no pipeline de CI)
RUN ./mvnw clean package -DskipTests
# --- Fim do MAVEN ---


# --- Estágio 2: "Runtime" (A Imagem Final) ---
# Usamos uma imagem JRE (Java Runtime) enxuta, que é muito menor
FROM eclipse-temurin:17-jre-focal

# Define o diretório de trabalho final
WORKDIR /app

# --- Configuração do MAVEN ---
# Copia o .jar construído no Estágio 1 (da pasta "target")
COPY --from=builder /workspace/target/*.jar app.jar
# --- Fim do MAVEN ---

# Expõe a porta que o Spring Boot usa por padrão (8080)
EXPOSE 8080

# Comando final para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
