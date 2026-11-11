# Checklist de release — preparação RC2

Atualizado em 2026-11-11. Esta é uma preparação local, ainda não revisada,
integrada ou executada na CI da `main`. A tag anotada `v1.0.0-rc1` já aponta
para `1b21196e1a60ddc0ac7a173c7ef6fdd4e28eecfb`; ela não será alterada. Se
aprovadas, as mudanças requerem novo commit/PR e, depois dos gates, `v1.0.0-rc2`.

| Item | Status | Atualizado | Caminho / evidência |
|---|---|---|---|
| Especificações com revisão por pares | PARCIAL — rascunho sem revisão | 2026-11-11 | `docs/fse.md`; precisa de revisor independente e aprovação. |
| Aplicação executável | PARCIAL — depende de MongoDB | 2026-11-11 | `README.md` documenta `MONGODB_URI`; a avaliação anterior iniciou a aplicação e obteve HTTP 200 em `/`, mas `/students` e `/teachers` excederam o tempo limite no MongoDB remoto. Validar CRUD em banco isolado. |
| README | PARCIAL — atualizado com índice de docs | 2026-11-11 | `README.md`; trecho legado de conexão não foi alterado nesta preparação por conter informação protegida. Revisar a configuração da conexão antes da release. |
| Documentação da API | RASCUNHO | 2026-11-11 | `docs/api.md`; contrato baseado nos controllers atuais, precisa revisão e testes de integração. |
| Especificação `docs/fse.md` | RASCUNHO | 2026-11-11 | `docs/fse.md`; requisitos e aceite pendentes de revisão. |
| Diagramas de contexto, componentes e arquitetura | RASCUNHO | 2026-11-11 | `docs/arquitetura.md` (Mermaid), pendente de validação pela equipe. |
| Critérios de aceitação/release | RASCUNHO | 2026-11-11 | `docs/criterios.md`; limiar de cobertura e limites de carga aguardam aprovação. |
| Plano de testes | RASCUNHO | 2026-11-11 | `docs/testes/plano.md`; T-05 a T-08 dependem de ambiente/configuração externa. |
| Relatório de testes | EXECUÇÃO LOCAL APROVADA; não é evidência de CI/release | 2026-11-11 | `docs/testes/resultado.md`: 12 testes aprovados; JaCoCo local 50,30% linhas e 25% branches. Checkout estava modificado. |
| Pipeline de CI | CONFIGURADO LOCALMENTE; não executado | 2026-11-11 | `.github/workflows/ci.yml`; requer PR, merge/revisão e execução GitHub Actions na `main` e no SHA da tag. A consulta anterior encontrou zero workflows/runs. |
| Relatório JaCoCo | LOCAL GERADO; artefato da CI pendente | 2026-11-11 | `target/site/jacoco/`; números locais em `docs/testes/resultado.md`; publicar como artefato no run da tag. |
| SonarCloud / Quality Gate | PENDENTE | 2026-11-11 | `.github/workflows/ci.yml` suporta vars `SONAR_ORGANIZATION`, `SONAR_PROJECT_KEY` e secret `SONAR_TOKEN`; falta configurar e obter gate aprovado. |
| Plano de teste de carga | PROPOSTA | 2026-11-11 | `docs/performance/plano.md`; parâmetros requerem aprovação do dono do ambiente. |
| Resultado do teste de carga (30 min) | NÃO EXECUTADO | 2026-11-11 | `docs/performance/resultado-30.md`; sem alvo autorizado ou métricas medidas. |
| Registro de defeitos | INICIAL / NÃO REVISADO | 2026-11-11 | `docs/defeitos/README.md`; itens em aberto/triagem. |
| Relatório final de qualidade | PROVISÓRIO / NÃO APROVADO | 2026-11-11 | `docs/relatorio-qualidade.md`. |
| Índice do pacote de evidências | INCOMPLETO | 2026-11-11 | `docs/evidencias/README.md`; não aponta ainda para execução real/artefato/gate de um SHA de release. |

## Bloqueadores em ordem

1. **Sem CI verde no commit de release:** o workflow foi criado localmente, mas
   ainda não há PR/merge nem run na `main`; sem isso não se pode atestar o
   candidato nem vincular JaCoCo.
2. **Revisão por pares não realizada:** as especificações e critérios são
   rascunhos; exige aprovação independente em PR.
3. **SonarCloud não configurado/executado:** faltam projeto, variáveis e
   segredo; nenhum Quality Gate pode ser afirmado.
4. **Integração MongoDB não validada:** as rotas de dados dependem de serviço
   externo; executar CRUD apenas em MongoDB de teste autorizado.
5. **Teste de carga pendente:** plano é proposta; requer aprovação de alvo e
   execução de 30 minutos, sem inventar métricas.
6. **Pacote de evidências incompleto:** substituir todos os links provisórios
   pelos URLs do run, artefato e análise que declarem o SHA da tag; relatório
   final depende do fechamento/aceite dos gates anteriores.

Nenhuma tag foi criada ou movida nesta preparação. A tag `v1.0.0-rc1` existente
é imutável; mudanças aprovadas serão candidatas a `v1.0.0-rc2`.
