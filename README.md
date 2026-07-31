# RD Autopeças

Sistema ERP Full Stack para gerenciamento de uma loja de autopeças, desenvolvido com **Java, Spring Boot e React**. O projeto tem como objetivo ser um ambiente de negócio real, contemplando módulos essenciais de gestão e aplicando boas práticas de desenvolvimento.

## Tecnologias

### Backend
- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- Lombok
- Flyway

### Banco de Dados
- PostgreSQL

### Frontend
- React *(em desenvolvimento)*

## Documentação da API

A documentação da API está disponível através do **Swagger** após iniciar a aplicação:

```
http://localhost:8080/swagger
```

##  Estrutura do Projeto

```text
backend/src/main/java/com/rd_autopecas/erp_autopecas/
├── domain
│   ├── auth
│   ├── user
│   ├── venda
│   └── ...
├── config
├── exceptions
└── infra
```