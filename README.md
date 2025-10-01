# usuarios-api
> API REST simples para cadastro de usuários.

## Descrição do Projeto
Este projeto implementa uma **mini API REST** que permite o cadastro, listagem e consulta de usuários por ID.  

A API atende aos seguintes requisitos:
- Criar um usuário (`POST /usuarios`)
- Listar todos os usuários (`GET /usuarios`)
- Buscar usuário por ID (`GET /usuarios/{id}`)

Extras implementados:
- Validação de email com anotações de Bean Validation
- Tratamento de erros com mensagens customizadas e `@RestControllerAdvice`
- Estrutura organizada em camadas (controller, service, repository, dto, exceptions)
- Testes unitários e de integração

---

## Tecnologias e Ferramentas Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Maven**
- **Spring Data JPA** (persistência de dados)
- **Spring Validation** (validação de entradas)
- **Springdoc OpenAPI (Swagger UI)** (documentação da API)
- **H2 Database** (banco em memória para desenvolvimento e testes)
- **JUnit 5 e Mockito** (testes unitários e de integração)

---

## Estrutura de Pastas
```
src/main/java/com/exemplo/usuarios_api
 ├── config          # Configurações do projeto
 ├── controller      # Controllers REST
 ├── dto             # Data Transfer Objects
 ├── model           # Entidades JPA
 ├── repository      # Interfaces de acesso a dados
 ├── service         # Regras de negócio
 └── exception       # Classes de exceção customizadas
```

---

## Pré-requisitos
- Java 17+
- Maven 3.8+
- IDE (IntelliJ, Eclipse ou VS Code)

---

## ▶️ Como Executar

### Clonar o repositório
```bash
git clone https://github.com/thiag0renatino/usuarios-api.git
cd usuarios-api
```

### Compile o projeto
```bash
mvn clean install
```

### Executar a aplicação
```bash
mvn spring-boot:run
```

### Acessar a API
- Documentação Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Testes

Rodar todos os testes:
```bash
mvn test
```

---

## Exemplos de Endpoints

### Criar usuário
```http
POST /usuarios
Content-Type: application/json

{
  "nome": "Thiago Renatino",
  "email": "thiago@email.com"
}
```

### Listar usuários
```http
GET /usuarios
```

### Buscar por ID
```http
GET /usuarios/1
```

## Autor
- Thiago Renatino Paulino
