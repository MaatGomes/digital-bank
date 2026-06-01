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
No terminal, certifique-se de estar no diretorio correto copiando e colando o comando abaixo:

```bash
cd digital-bank-api
```
Suba o banco de dados PostgreSQL que vai rodar via Docker copiando e colando o comando abaixo no terminal:

```bash
docker-compose up -d
```

### Executando no Windows:

Execute a aplicação com Maven Wrapper colando o comando abaixo no terminal:

```bash
./mvnw spring-boot:run
```
### Executando no Linux/Mac:

Dê permissão ao Maven Wrapper:
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