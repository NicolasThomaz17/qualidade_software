# Decisão de qualidade — Release Candidate

**Decisão: LIBERAR COM RESSALVAS (ambiente acadêmico / homologação controlada).**

Esta decisão avalia a evolução do projeto no repositório `https://github.com/NicolasThomaz17/qualidade_software` após a validação da esteira de CI verde no commit `e443ade` (`v1.0.0-rc3`) e a consolidação documental em `v1.0.0-rc4` em 2026-11-13. Não declaramos prontidão para produção em escala, pois a evidência atual cobre build reproduzível, testes automatizados de unidade/API e governança de repositório, sem testes E2E ou carga em infraestrutura produtiva.

## Critérios e justificativas

| Critério | Resultado | Evidência e justificativa |
|---|---|---|
| CI verde na `main` e na tag candidata | **Aprovado** | Workflow `.github/workflows/ci.yml` executado com sucesso (verde) na branch `main` e na tag candidata no [GitHub Actions](https://github.com/NicolasThomaz17/qualidade_software/actions). |
| Build reproduzível a partir do README | **Aprovado** | Clone limpo validado com JDK 17 e Maven Wrapper (`mvnw clean verify` e `spring-boot:run`), com correções de compatibilidade LF/CRLF (`.gitattributes`). |
| Testes automatizados e cobertura JaCoCo | **Aprovado** | Suíte com 12 testes (`HomeControllerTest`, `StudentApiControllerTest`, `TeacherApiControllerTest`) passando via `MockMvc` e relatórios JaCoCo anexados como artefato no CI. |
| Governança Git e revisão de código | **Aprovado** | Alterações integradas na `main` via *branches* e Pull Requests (#1, #2, #3), mantendo tags anteriores (`rc1`, `rc2`, `rc3`) imutáveis. |
| Segurança e ausência de segredos | **Aprovado** | Credenciais sensíveis não estão expostas no repositório; configurações de banco são injetadas externamente via variável de ambiente. |
| Integração com MongoDB externo | **Ressalva** | Aplicação inicializa normalmente (`HTTP 200` na raiz `/`), mas as rotas `/students` e `/teachers` dependem de instância externa do MongoDB (`crossover.proxy.rlwy.net:24258`), sujeita a *read timeout* quando o serviço remoto compartilhado está pausado. |
| SonarCloud Quality Gate e Teste de Carga | **Ressalva** | Análise SonarCloud ignorada condicionalmente por falta de token externo no repositório e teste de carga de 30 minutos não executado. |

## Riscos aceitos

Os riscos abaixo foram formalmente aceitos para a liberação desta versão acadêmica, com impacto, justificativa e responsável nominal atribuídos:

| Risco aceito | Impacto | Justificativa | Responsável |
|---|---|---|---|
| **Indisponibilidade do MongoDB compartilhado (Railway)** | **Médio** — Rotas de listagem/CRUD (`/students`, `/teachers`) retornam erro 500 por *timeout* se o banco externo estiver pausado. | A camada de persistência está validada via *mocks* no CI e a aplicação suporta redirecionamento imediato para banco local via `SPRING_DATA_MONGODB_URI` sem alterar código. | **Nicolas Thomaz da Silva** |
| **Ausência de Quality Gate no SonarCloud** | **Baixo** — Falta de análise estática externa automatizada no painel do SonarCloud. | O build, a execução de testes e a geração de métricas de cobertura JaCoCo já são verificados obrigatoriamente a cada push/PR no GitHub Actions. | **João Cavanhas** |
| **Ausência de testes E2E e teste de carga (30 min)** | **Médio** — Comportamento sob alta concorrência e fluxos ponta a ponta em navegador não foram medidos. | Escopo restrito à validação funcional e de qualidade de código em ambiente controlado de aula; não há exposição pública em produção. | **Nicolas Thomaz da Silva** |
| **API REST sem autenticação e com CORS aberto (`*`)** | **Baixo (em ambiente local)** — Qualquer origem pode consultar os endpoints enquanto o servidor estiver ativo localmente. | Aplicação destinada exclusivamente a demonstração acadêmica local; antes de qualquer deploy público deverá receber camada de autenticação. | **João Cavanhas** |

## Integrantes e declarações de contribuição

- **Nicolas Thomaz da Silva:** Diagnóstico e correção dos bloqueadores iniciais no clone limpo (JDK 17, injeção do `MockMvc` no `HomeControllerTest` e correção do template Thymeleaf `layout.html`), resolução do erro de quebra de linha `LF`/Maven Wrapper no runner Linux do GitHub Actions, diagnóstico de rede/timeout do MongoDB e formalização da decisão de qualidade.
- **João Cavanhas:** Estruturação da pipeline de CI/CD (`.github/workflows/ci.yml`) com upload de relatórios JaCoCo, criação dos testes automatizados das APIs (`StudentApiControllerTest` e `TeacherApiControllerTest`), revisão do `README.md` e organização da documentação técnica.

## Texto curto para o formulário do Hub

Repositório público: https://github.com/NicolasThomaz17/qualidade_software — tag avaliada: `v1.0.0-rc4` (evolução imutável após CI verde na `v1.0.0-rc3`, commit `e443ade`). Decisão de qualidade: **Liberar com ressalvas**, com esteira de CI verde, pacote de evidências consolidado em `docs/evidencias/README.md` e riscos aceitos com responsáveis atribuídos em `docs/decisao-qualidade.md`.