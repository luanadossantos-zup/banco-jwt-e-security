# Banco com JWT e Security

  ## Propósito do Trabalho
  Este projeto tem como objetivo o treinamento do uso da biblioteca JWT (JSON Web Token) com Spring Security. Ele implementa autenticação e autorização de usuários com diferentes roles (perfis de acesso).

  ## Tecnologias Necessárias
  - IntelliJ IDEA
- PostgreSQL
- Spring Web

## Como Rodar a Aplicação no IntelliJ
  1. Certifique-se de ter o PostgreSQL instalado e configurado.
  2. Crie um banco de dados chamado `security` no PostgreSQL.
  3. Configure o arquivo `application.properties` com o nome do banco, usuário e senha (detalhes abaixo).
  4. Abra o projeto no IntelliJ IDEA.
  5. Certifique-se de que as dependências foram baixadas corretamente (Maven ou Gradle).
  6. Execute a aplicação através da classe principal (geralmente marcada com `@SpringBootApplication`).

  ## Dependências Usadas
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - PostgreSQL Driver
  - Lombok

  ## Configuração do `application.properties`
  Certifique-se de configurar o arquivo `application.properties` com as informações corretas do banco de dados. Exemplo:

  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/security
  spring.datasource.username=seu_usuario
  spring.datasource.password=sua_senha
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  ```

  ## Exemplo de JSON Enviado e JSON de Retorno

  ### Requisição
  O usuário faz uma requisição GET para o endpoint `/user` com o token no cabeçalho `Authorization`:
  ```
  Authorization: Bearer <token>
  ```

  ### Resposta
  O sistema valida o token e retorna o seguinte JSON:
  ```json
  {
      "message": "Bem-vindo, Pedro!",
      "department": "IT"
  }
  ```
