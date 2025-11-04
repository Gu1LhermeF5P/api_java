# MotoScan API 🏍️

Este repositório contém a API RESTful desenvolvida em Java com Spring Boot, que serve como backend para a aplicação mobile **[MotoScan](https://github.com/Gu1LhermeF5P/MottoScan)**. *(<-- Troque pelo link do seu repositório do frontend)*

A API é responsável por gerenciar toda a lógica de negócio, autenticação de usuários e persistência de dados da frota de motocicletas.

---

## ✨ Funcionalidades Principais

-   **Autenticação Segura:** Sistema de registro e login de usuários com tokens **JWT** (JSON Web Tokens).
-   **Endpoints Protegidos:** As rotas de gerenciamento de motos são protegidas e só podem ser acessadas com um token válido.
-   **CRUD Completo:** Endpoints para Criar, Ler, Atualizar e Deletar (CRUD) os registros de motocicletas.
-   **Codificação de Senhas:** As senhas dos usuários são armazenadas de forma segura usando o algoritmo **BCrypt**.
-   **Versionamento de Banco de Dados:** A estrutura do banco de dados (tabelas e colunas) é criada e gerenciada automaticamente pelo **Flyway**.

---

## 🛠️ Tecnologias Utilizadas

-   **Java 17**
-   **Spring Boot 3**
-   **Spring Security:** Para o framework de segurança e autenticação.
-   **Spring Data JPA:** Para a comunicação com o banco de dados.
-   **JJWT (Java JWT):** Para a geração e validação dos tokens.
-   **Flyway Migration:** Para o versionamento do banco de dados.
-   **H2 Database:** Banco de dados em memória/arquivo para desenvolvimento.
-   **Lombok:** Para reduzir o código boilerplate nas entidades.
-   **Maven:** Para o gerenciamento de dependências e build do projeto.

---

## 🚀 Endpoints da API

| Método HTTP | Rota                     | Protegido? | Descrição                                               |
| :---------- | :----------------------- | :--------- | :-------------------------------------------------------- |
| `POST`      | `/auth/register`         | ✅ Sim    | Cria um novo usuário.                                     |
| `POST`      | `/auth/login`            | ✅ Sim     | Autentica um usuário e retorna um token JWT.              |
| `GET`       | `/motos`                 | ✅ Sim     | Lista todas as motos cadastradas.                         |
| `POST`      | `/motos`                 | ✅ Sim     | Cadastra uma nova moto.                                   |
| `PUT`       | `/motos/{placa}`         | ✅ Sim     | Atualiza os dados de uma moto existente pela placa.       |
| `DELETE`    | `/motos/{placa}`         | ✅ Sim     | (Não implementado, mas seria para) Exclui uma moto pela placa. |

---

## ▶️ Como Rodar Localmente

**Pré-requisitos:**
-   Java (JDK 17 ou superior) instalado.
-   Maven instalado e configurado nas variáveis de ambiente.

**Passos:**

1.  Clone o repositório:
    ```bash
    git clone [https://github.com/SeuUsuario/motoscan-api.git](https://github.com/SeuUsuario/motoscan-api.git)
    cd motoscan-api
    ```

2.  Execute a aplicação com o Maven Wrapper (recomendado):
    ```bash
    # Para Linux/macOS
    ./mvnw spring-boot:run

    # Para Windows (CMD ou PowerShell)
    ./mvnw.cmd spring-boot:run
    ```

3.  A API estará disponível em `http://localhost:8080`.

---

## 🗃️ Banco de Dados

-   O projeto utiliza o **H2 Database** em modo arquivo para desenvolvimento. O banco de dados físico (`motoscan_db.mv.db`) será criado na pasta `/data` na raiz do projeto na primeira execução.
-   As tabelas `MOTO` e `USUARIOS` são criadas e versionadas automaticamente pelo **Flyway**.

**Acesso ao Console do H2:**
1.  Com a aplicação rodando, acesse no seu navegador: `http://localhost:8080/h2-console`
2.  Use a seguinte URL JDBC para se conectar: `jdbc:h2:file:./data/motoscan_db`
3.  O usuário é `sa` e a senha é `password`.
