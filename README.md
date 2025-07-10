# 🛠️ Migration

Este projeto Java com Spring Boot tem como objetivo gerenciar alterações de banco de dados de forma manual e controlada por código. Ele executa scripts DDL e DML definidos como classes Java, e garante que cada script seja aplicado apenas uma vez, registrando seu ID no banco de dados.

---

Tecnologias Utilizadas

- Java 21
- Spring Boot 3.x
- Spring JDBC
- PostgreSQL
- Maven

---

 Ao iniciar a aplicação:
- O sistema verifica se a tabela `migration` existe. Caso não exista, ela é criada.
- Em seguida, ele executa apenas os scripts cujo ID ainda **não está salvo** na tabela.
  
---
Pré-requisitos
- PostgreSQL rodando
- Criar um banco vazio
- Atualizar o application.properties com suas credenciais

---
👩‍💻 Autora
Patricia Ramos
Desenvolvedora Backend | Java | Spring Boot | PostgreSQL
