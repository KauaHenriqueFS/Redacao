#  Projeto Redação

Exercício escolar utilizando **Java**, **JPA** e **PostgreSQL** para cadastro e consulta de redações.

---

##  Descrição

Este projeto tem como objetivo criar um sistema simples de gerenciamento de redações, aplicando os conceitos de persistência com JPA. A aplicação permite cadastrar, listar e realizar consultas específicas no banco de dados.

---

##  Tecnologias Utilizadas

- Java
- JPA (Java Persistence API)
- PostgreSQL
- NetBeans IDE

---

##  Funcionalidades

- Cadastro de redações
- Listagem de todas as redações
- Consulta por parte do título
- Contagem de redações com a mesma nota
- Estatísticas das notas:
  - Soma das notas
  - Maior nota
  - Menor nota
  - Média das notas
  - Total de notas cadastradas

---

##  Estrutura da Tabela

```sql
CREATE TABLE redacao (
  codigo SERIAL PRIMARY KEY,
  titulo VARCHAR(50),
  texto VARCHAR(8192),
  nota FLOAT
);
