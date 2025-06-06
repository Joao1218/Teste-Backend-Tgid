# Sistema de Vendas - Backend

Este projeto é um sistema backend para gerenciamento de vendas, desenvolvido com Java e Spring Boot. Ele permite cadastrar usuários, produtos, realizar vendas e consultar o histórico de vendas.

---

## Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Banco de dados H2 (ou configure outro banco de sua preferência)
- Maven

---

## Funcionalidades

- Cadastro e listagem de usuários
- Cadastro e listagem de produtos
- Realização de vendas (compra de múltiplos produtos por usuário)
- Consulta e exclusão de vendas
- API REST com endpoints para todas as operações

---

## Endpoints principais

| Método | URL               | Descrição                        |
|--------|-------------------|---------------------------------|
| POST   | `/usuario`        | Cadastrar novo usuário           |
| GET    | `/usuario`        | Listar todos os usuários         |
| POST   | `/produto`        | Cadastrar novo produto           |
| GET    | `/produto`        | Listar todos os produtos         |
| POST   | `/venda/{usuarioId}` | Realizar uma venda para usuário |
| GET    | `/venda`          | Listar todas as vendas           |
| GET    | `/venda/{id}`     | Buscar venda por ID              |
| DELETE | `/venda/{id}`     | Excluir venda por ID             |

---
