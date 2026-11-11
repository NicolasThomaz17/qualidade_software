# Especificação funcional e de qualidade

**Estado:** rascunho para revisão por pares; não aprovado para release.
**Versão candidata:** alterações desta preparação, destinadas a um futuro
`v1.0.0-rc2`. A tag existente `v1.0.0-rc1` não contém esta especificação.

## Objetivo e escopo

O sistema oferece interface web e API REST para cadastrar e manter estudantes
e professores. Os dados são persistidos em MongoDB. O escopo desta versão não
inclui autenticação, autorização, paginação nem operações em lote.

## Atores e integrações

- Operador: usa a interface web para consultar, criar, editar e remover
  registros.
- Cliente HTTP: consome a API `/api/students` e `/api/teachers`.
- MongoDB: persiste as coleções `students` e `teachers`.

## Requisitos funcionais

| ID | Requisito | Critério de aceite |
|---|---|---|
| RF-01 | Listar estudantes e professores pela interface e API. | A listagem apresenta os documentos persistidos; a API responde `200` e uma lista JSON. |
| RF-02 | Consultar um documento por ID na API. | ID existente responde `200` com o documento; inexistente responde `404`. |
| RF-03 | Criar estudante. | Requisição válida responde `201`; e-mail ou matrícula duplicados respondem `409`. |
| RF-04 | Criar professor. | Requisição válida responde `201`; e-mail duplicado responde `409`. |
| RF-05 | Atualizar estudante ou professor por ID. | ID inexistente responde `404`; ID existente mantém o ID e responde `200`. Duplicidade de identificadores únicos responde `409`. |
| RF-06 | Remover estudante ou professor por ID. | ID existente responde `204`; ID inexistente responde `404`. |
| RF-07 | Validar integração com MongoDB. | Em ambiente de integração autorizado e disponível, operações CRUD persistem e recuperam dados; sem banco acessível, essa aceitação permanece pendente. |

## Requisitos não funcionais

- RNF-01: construir e testar com Java 17 e o Maven Wrapper por
  `./mvnw -B clean verify`.
- RNF-02: não armazenar credenciais MongoDB no código, README, artefatos ou
  histórico; fornecer `MONGODB_URI` por configuração segura do ambiente.
- RNF-03: a pipeline de CI deve executar no commit candidato à release e
  publicar Surefire e JaCoCo associados ao mesmo SHA.
- RNF-04: o Quality Gate SonarCloud e o teste de carga precisam de evidências
  reais associadas ao SHA da release; enquanto não disponíveis, o gate de
  release fica bloqueado.
- RNF-05: operações funcionais que acessam o banco só podem ser declaradas
  aceitas após ensaio em MongoDB de teste/estágio autorizado.

## Limitações e decisões pendentes

- O contrato atual não declara validação de campos obrigatórios ou formato de
  e-mail na API. O comportamento deve ser decidido e testado antes de ampliar
  a especificação.
- A aplicação declara MongoDB, enquanto `src/main/resources/db/migration/`
  contém uma migração SQL PostgreSQL sem integração evidente com o fluxo
  MongoDB. Confirmar se o arquivo é legado ou remover em mudança aprovada.
- Os requisitos de disponibilidade, retenção, autenticação e volume de dados
  dependem de aprovação do responsável pelo produto.
- O limiar numérico de cobertura e os limites finais de performance dependem
  de aprovação; consultar [critérios](criterios.md).

## Evidências

O commit desta preparação ainda não é um commit de release. A vinculação dos
resultados ao SHA definitivo é controlada pelo
[índice de evidências](evidencias/README.md).
