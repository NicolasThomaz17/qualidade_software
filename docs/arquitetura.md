# Arquitetura e diagramas

**Estado:** documentação inicial para revisão; não aprovada para release.

## Contexto

```mermaid
flowchart LR
    Operator[Operador] -->|HTTP, páginas| App[Aplicação Spring Boot]
    Client[Cliente HTTP] -->|JSON, /api/*| App
    App -->|Spring Data MongoDB| DB[(MongoDB)]
```

## Componentes

```mermaid
flowchart TB
    Browser[Thymeleaf UI] --> Web[Controllers MVC]
    API[REST API Controllers] --> Services[Services]
    Web --> Services
    Services --> Repos[MongoRepository interfaces]
    Repos --> Mongo[(MongoDB)]
    Web --> Templates[Thymeleaf templates]
```

## Sequência: criação de estudante pela API

```mermaid
sequenceDiagram
    participant C as Cliente HTTP
    participant A as StudentApiController
    participant S as StudentService
    participant R as StudentRepository
    participant M as MongoDB
    C->>A: POST /api/students (JSON)
    A->>S: existsByEmail / existsByRegistrationNumber
    S->>R: consultar unicidade
    R->>M: consultas
    M-->>R: resultado
    R-->>S: resultado
    alt identificador já existe
        S-->>A: duplicado
        A-->>C: 409 Conflict
    else identificadores únicos
        A->>S: saveStudent
        S->>R: save
        R->>M: inserir documento
        M-->>R: documento persistido
        R-->>S: documento salvo
        S-->>A: documento salvo
        A-->>C: 201 Created + JSON
    end
```

## Componentes de produção

- `controller/`: páginas Thymeleaf e endpoints REST.
- `service/`: delegação de operações de domínio para repositórios.
- `repository/`: interfaces Spring Data MongoDB.
- `entity/`: documentos `Student` e `Teacher`.
- `resources/templates/`: páginas web.

O banco deve ser provisionado externamente e configurado por
`MONGODB_URI`. O teste de contexto do Spring e os testes MVC com serviços
mockados não comprovam a disponibilidade do banco remoto.
