# 🧾 Cliente Service

Este microsserviço é responsável pela gestão de clientes de um sistema de pedidos, permitindo o cadastro, consulta e manutenção de informações pessoais e de endereço dos clientes.

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.2**
- **Maven**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Flyway** (migração de banco de dados)
- **Clean Architecture**
- **Lombok**
- **Swagger/OpenAPI**
- **JUnit 5** (para testes)
- **Spring Validation**

## 📦 Estrutura do Projeto

O projeto segue a **Clean Architecture**, com as seguintes camadas:

- `domain`: entidades, objetos de valor e regras de negócio puras.
- `application`: casos de uso (UseCases) e orquestração da lógica.
- `infraestrutura`: repositórios, implementações concretas e integrações externas.
- `api`: controladores (REST), DTOs de entrada (requests) e saída (responses).

## 🚀 Funcionalidades

- Cadastro de cliente com dados pessoais e endereço.
- Consulta de clientes por ID ou listagem completa.
- Atualização de dados do cliente.
- Remoção de cliente e seus endereços (com `cascade delete`).
- Validação de CPF único.
- Persistência automática de dados mockados via Flyway (`V2__insert_clientes_enderecos.sql`).

## 🐳 Executando com Docker

### Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### Comando para subir o projeto:

```bash
docker-compose up --build

```
## O serviço estará disponível em:
- `http://localhost:8081/cliente-service/clientes

## Acesso ao banco de dados (PostgreSQL)
- Host: `localhost`
- Porta: `5432`
- Usuário: `postgres`
- Senha: `postgres`
- Banco: `cliente_db`
- Driver: `PostgreSQL`
- JDBC URL: `jdbc:postgresql://localhost:5432/cliente_db`
- JDBC Driver: `org.postgresql.Driver`
- Dialect: `org.hibernate.dialect.PostgreSQLDialect`

## 📜 Documentação da API
A documentação da API pode ser acessada via Swagger após iniciar a aplicação:
- [Swagger UI](http://localhost:8081/cliente-service/swagger-ui/index.html)

## Principais Endpoints
- `POST /clientes`: Cadastra um novo cliente.
- `GET /clientes`: Lista todos os clientes.
- `GET /clientes/{id}`: Consulta um cliente específico.
- `PUT /clientes/{id}`: Atualiza os dados de um cliente.
- `DELETE /clientes/{id}`: Remove um cliente.

## 🧪 Testes
Os testes unitários estão localizados na pasta `src/test/java` e utilizam o JUnit 5 e Mockito para simulação de comportamentos.

para executar os testes, utilize o seguinte comando:

```bash
mvn test
```

testes com relatorio jacoco:

```bash
mvn clean verify
```

## Migrações de Banco de Dados
As migrações de banco de dados são gerenciadas pelo Flyway. As migrações estão localizadas na pasta `src/main/resources/db/migration`.
Scripts aplicados automaticamente:
- `V1__create_clientes.sql`: Criação da tabela de clientes.
- `V2__insert_clientes_enderecos.sql`: Inserção de dados mockados para testes.

## ✍️ Autor
- Eduardo Sócrates Caria
- GitHub: https://github.com/Edusocrates
- RM: 358568
- Turma: 6ADJT
- Grupo 15