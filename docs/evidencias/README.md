# Índice do pacote de evidências

**Estado: INCOMPLETO — não usar para aprovar/publicar release.**

## Identidade e proveniência

- Tag já publicada: [`v1.0.0-rc1`](https://github.com/NicolasThomaz17/qualidade_software/tree/v1.0.0-rc1)
- SHA da tag existente: `1b21196e1a60ddc0ac7a173c7ef6fdd4e28eecfb`
- SHA do commit desta preparação: **pendente de integração**.
- Toda evidência final deve ser gerada no commit de release e registrar o
  mesmo SHA. Não associar os arquivos desta preparação à RC1; não mover tags.
- Próximo identificador, se a preparação for aprovada: `v1.0.0-rc2`.

## Evidências exigidas

| Evidência | Link esperado | Estado |
|---|---|---|
| Execução verde da CI no commit da tag | [GitHub Actions](https://github.com/NicolasThomaz17/qualidade_software/actions) — substituir pelo link direto da execução de `v1.0.0-rc2` e conferir SHA antes de aprovar. | Pendente: workflow foi adicionado localmente; ainda não executado no GitHub nem na `main`. |
| Relatório JaCoCo como artefato da mesma execução | Artefato `quality-reports-<SHA>` anexado ao run acima; baixar `target/site/jacoco/` e conferir o SHA/run. | Pendente de CI. |
| SonarCloud Quality Gate no SHA da tag | [Quality Gate](https://sonarcloud.io/) — preencher a URL do projeto configurado e a análise exata do SHA. | Pendente de projeto, variáveis `SONAR_ORGANIZATION`/`SONAR_PROJECT_KEY`, segredo `SONAR_TOKEN` e execução. |
| Resultado do teste de carga de 30 minutos | [docs/performance/resultado-30.md](../performance/resultado-30.md) | Não executado; ambiente e aprovação pendentes. |
| Especificação funcional e de qualidade | [docs/fse.md](../fse.md) | Rascunho, aguardando revisão por pares. |
| Registro de defeitos | [docs/defeitos/](../defeitos/) | Inicial, itens abertos/em triagem. |
| Relatório final de qualidade | [docs/relatorio-qualidade.md](../relatorio-qualidade.md) | Provisório; não aprovado. |

## Gate de publicação

Antes de transformar este índice em pacote final:

1. Integrar as mudanças via PR revisado e aprovado.
2. Confirmar CI verde na `main` e no commit exato da tag candidata.
3. Substituir links provisórios pelos URLs do run, artefato e análise SonarCloud
   que mostrem o SHA da tag.
4. Anexar resultado real de carga e relatório final aprovados.
5. Conferir que nenhum artefato contém segredos ou dados pessoais.

Este arquivo não constitui evidência de execução nem substitui qualquer gate.
