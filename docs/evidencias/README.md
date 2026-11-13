# Índice do pacote de evidências

**Estado: CONSOLIDADO — pronto para avaliação de release candidate.**

## Identidade e proveniência

- **Repositório:** `https://github.com/NicolasThomaz17/qualidade_software`
- **Branch principal:** `main`
- **Histórico de tags imutáveis:**
  - `v1.0.0-rc1` (`1b21196`) — baseline inicial após correção dos bloqueadores de build e Thymeleaf.
  - `v1.0.0-rc2` (`43f2702`) — primeira tentativa de pipeline CI (mantida imutável).
  - `v1.0.0-rc3` (`e443ade`) — esteira de CI verde na `main` com testes unitários/MVC e relatórios JaCoCo.
  - `v1.0.0-rc4` — consolidação final do índice de evidências e da decisão de qualidade.

## Evidências exigidas

| Evidência | Referência / Link | Estado |
|---|---|---|
| Execução verde da CI na `main` e nas tags | [GitHub Actions — Workflow CI](https://github.com/NicolasThomaz17/qualidade_software/actions) | **Aprovado** (`./mvnw -Dmaven.multiModuleProjectDirectory=$GITHUB_WORKSPACE -B clean verify` passando com sucesso). |
| Relatório JaCoCo e testes Surefire | Artefato `quality-reports-<SHA>` gerado automaticamente na execução do GitHub Actions (`target/site/jacoco/` e `target/surefire-reports/`). | **Aprovado** (12 testes unitários e de controladores MVC executados sem falhas). |
| Checklist de entrega | [docs/entrega/checklist.md](../entrega/checklist.md) | **Concluído**. |
| Decisão de qualidade assinada | [docs/decisao-qualidade.md](../decisao-qualidade.md) | **Concluído** (Decisão: Liberar com ressalvas e riscos com responsáveis atribuídos). |
| SonarCloud Quality Gate | [Quality Gate](https://sonarcloud.io/) | **Não aplicável nesta entrega** (job configurado no `ci.yml` e ignorado condicionalmente por ausência de credenciais externas). |
| Teste de carga de 30 minutos | [docs/performance/resultado-30.md](../performance/resultado-30.md) | **Não executado** (registrado como risco aceito para ambiente acadêmico). |
| Registro de defeitos | [docs/defeitos/README.md](../defeitos/README.md) | **Documentado** (itens mapeados e vinculados à análise de risco). |

## Conformidade de segurança e governança

1. Todas as integrações na `main` foram realizadas via *branches* e *Pull Requests*.
2. Nenhuma credencial, senha de banco ou token está exposto no código versionado (`application.properties` mantido apenas como exemplo).
3. Nenhuma tag publicada anteriormente foi movida ou sobrescrita.