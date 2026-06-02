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

### Pré-requisitos obrigatórios:

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

---

## FAQ

### 1 - Erro de autenticação no PostgreSQL

Ao executar o comando abaixo para subir os containers do PostgreSQL e do Kafka:

```bash
docker-compose up -d
```

Caso seja exibido um erro semelhante ao abaixo:

```text
FATAL: password authentication failed for user "postgres"
```

Pode ser que exista uma instância local do PostgreSQL em execução na mesma porta utilizada pelo banco de dados do projeto. Nesse cenário, o projeto pode estar tentando se conectar ao banco errado.

Verifique se há algum serviço PostgreSQL rodando localmente e, se necessário, interrompa-o antes de iniciar os containers do projeto.

Após interromper o serviço, execute novamente:

```bash
docker-compose up -d
```

---

### 2 - Erro ao executar o Maven Wrapper

Ao tentar iniciar a aplicação utilizando o Maven Wrapper com o comando:

```bash
./mvnw spring-boot:run
```

Caso seja exibido um erro semelhante ao abaixo:

```text
./mvnw : The term './mvnw' is not recognized as the name of a cmdlet, function, script file, or operable program.
```

Certifique-se de estar no diretório raiz do projeto, onde se encontram os arquivos `mvnw`, `mvnw.cmd` e `pom.xml`.

Caso tenha clonado o projeto e esteja em um diretório superior, navegue até a pasta da aplicação utilizando o comando:

```bash
cd digital-bank-api
```

Exemplo:

```text
C:\Users\...\digital-bank\
```

Após executar o comando acima, você deverá estar em um diretório semelhante a:

```text
C:\Users\...\digital-bank\digital-bank-api\
```

Para confirmar que está no local correto, execute:

```bash
dir
```

ou

```bash
ls
```

Os arquivos `mvnw`, `mvnw.cmd` e `pom.xml` devem estar presentes na listagem.

Após isso, execute novamente:

```bash
./mvnw spring-boot:run
```

---

### 3 - Erro: `Port 5432 is already allocated`

Ao executar o comando:

```bash
docker-compose up -d
```

Caso seja exibido um erro semelhante ao abaixo:

```text
Bind for 0.0.0.0:5432 failed: port is already allocated
```

Isso significa que a porta `5432`, utilizada pelo PostgreSQL do projeto, já está sendo utilizada por outro processo em sua máquina.

Na maioria dos casos, existe uma instância local do PostgreSQL em execução. Verifique os serviços ativos e interrompa o PostgreSQL local antes de subir os containers do projeto.

Após interromper o serviço, execute novamente:

```bash
docker-compose up -d
```
