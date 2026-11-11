# API REST

Base local: `http://localhost:8080`. Os recursos usam JSON.
Esta documentação descreve o comportamento atual dos controllers; não há
contrato OpenAPI versionado nesta preparação.

## Estudantes

Recurso: `/api/students`.

| Método | Caminho | Sucesso | Outros resultados |
|---|---|---|---|
| GET | `/api/students` | `200 OK`, array de estudantes | — |
| GET | `/api/students/{id}` | `200 OK`, estudante | `404 Not Found` |
| POST | `/api/students` | `201 Created`, estudante salvo | `409 Conflict` se e-mail ou matrícula já existirem |
| PUT | `/api/students/{id}` | `200 OK`, estudante salvo | `404 Not Found`; `409 Conflict` por e-mail/matrícula duplicados |
| DELETE | `/api/students/{id}` | `204 No Content` | `404 Not Found` |

Campos: `id`, `name`, `email`, `registrationNumber`, `createdAt`,
`updatedAt`. O ID e as datas são mantidos/gerados pelo sistema.

Exemplo de criação:

```json
{
  "name": "Ada Lovelace",
  "email": "ada@example.test",
  "registrationNumber": "ST-100"
}
```

## Professores

Recurso: `/api/teachers`.

| Método | Caminho | Sucesso | Outros resultados |
|---|---|---|---|
| GET | `/api/teachers` | `200 OK`, array de professores | — |
| GET | `/api/teachers/{id}` | `200 OK`, professor | `404 Not Found` |
| POST | `/api/teachers` | `201 Created`, professor salvo | `409 Conflict` se o e-mail já existir |
| PUT | `/api/teachers/{id}` | `200 OK`, professor salvo | `404 Not Found`; `409 Conflict` por e-mail duplicado |
| DELETE | `/api/teachers/{id}` | `204 No Content` | `404 Not Found` |

Campos: `id`, `name`, `email`, `department`, `createdAt`, `updatedAt`.

Exemplo de criação:

```json
{
  "name": "Grace Hopper",
  "email": "grace@example.test",
  "department": "Computing"
}
```

## Regras e limitações conhecidas

- `PUT` grava o objeto recebido como registro atualizado; envie todos os
  campos de negócio. Campos omitidos podem ser gravados como `null`.
- O código verifica duplicidades descritas acima, mas não declara validação
  Bean Validation de campos obrigatórios/formato.
- Respostas de erro atuais para `404` e `409` não têm corpo de erro
  documentado.
- A configuração atual declara CORS aberto (`*`) e não implementa
  autenticação/autorização nos controllers. Não exponha a API a redes não
  confiáveis sem uma decisão explícita de segurança.
- A API depende de MongoDB acessível por `MONGODB_URI`; testes MVC com
  dependências mockadas não validam a persistência real.

Os exemplos são sintéticos. Use IDs gerados pelo banco para operações por ID.
