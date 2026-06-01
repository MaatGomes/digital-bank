# 🏦 Digital Bank API

API REST para simulação de um sistema bancário simples, permitindo criação de contas, transferências entre contas e registro de movimentações financeiras.

---

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Docker
- Bean Validation
- Swagger (Springdoc OpenAPI)
- JUnit + Mockito (testes)
- Lombok

---

## Funcionalidades

### Contas
- Criar conta
- Listar contas

### Transferências
- Transferência entre contas
- Validação de saldo
- Registro automático de movimentações

### Movimentações
- Histórico de transações por conta

---

##  Como rodar o projeto:

### Pré-requisitos

- Java 21
- Docker

---

###  Rodando localmente

Clone o projeto:

```bash
git clone https://github.com/MaatGomes/digital-bank.git
```
---

### Subindo o banco de dados (PostgreSQL com Docker)

Antes de executar a aplicação, é necessário subir o banco de dados PostgreSQL via Docker.

No terminal, dentro da raiz do projeto (certifique-se de estar na raiz, caso contrario, não funcionará), execute o comando abaixo:
```bash
docker-compose up -d
```

### Executando a aplicação no Windows:

Após subir o Docker com o banco de dados, execute a aplicação com Maven Wrapper colando o comando abaixo no terminal:

```bash
./mvnw spring-boot:run
```
### Executando no Linux/Mac:

Caso esteja em uma máquina Linux ou Mac será necessário dar permissão ao Maven Wrapper colando o comando abaixo no terminal:
```bash
chmod +x mvnw
```
Execute a aplicação com Maven Wrapper colando o comando abaixo no terminal:

```bash
./mvnw spring-boot:run
```

---

##  Acessos

### API (Caso queira testar pelo Postman/Insomnia):
```bash
http://localhost:8080
```

### Swagger:
```bash
http://localhost:8080/swagger-ui/index.html
```
---

## Testes

### Executar testes:
```bash
./mvnw test
```