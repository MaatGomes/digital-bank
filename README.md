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
- Kafka

---

## Funcionalidades

### Contas
- Criar conta
- Listar contas

### Transferências
- Transferência entre contas
- Validação de saldo
- Registro automático de movimentações
- Envio de notificação após a conclusão da transferência

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
---

## Decisões de Design e Arquitetura:

### Arquitetura adotada:

O projeto foi estruturado seguindo os princípios de:

- Clean Architecture
- Domain-Driven Design (DDD)
- Clean Code

A aplicação foi organizada em camadas bem definidas para garantir baixo acoplamento e alta coesão.

---

### A aplicação foi dividida nas seguintes camadas utilizando a Layered Architecture:

- Controller → expõe os endpoints da API
- Service → contém as regras de negócio
- Repository → responsável pela persistência de dados
- Domain → contém as entidades e regras do domínio
- DTO → objetos para entrada e saída de dados da API
- Infrastructure → configurações e componentes de infraestrutura
- Exception → tratamento centralizado de exceções

---

### Transações:

As operações financeiras são protegidas por controle transacional via Spring (@Transactional) para garantir:

- Execução completa da operação ou rollback em caso de erro
- Consistência dos saldos das contas
- Integridade dos dados durante a persistência 

---

### Notificação:
Após a conclusão da transferência, a aplicação publica um evento no Kafka. Um consumidor escuta esse evento e realiza o envio da notificação. Dessa forma, a lógica de transferência permanece desacoplada da lógica de notificação, permitindo processamento assíncrono e maior escalabilidade.

---

### Testes:
- Testes unitários com JUnit
- Mocks com Mockito
- Foco em regras de negócio (Service Layer)