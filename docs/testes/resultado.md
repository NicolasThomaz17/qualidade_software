# Resultado local dos testes — preparação RC2

**Status:** aprovado somente para a execução local abaixo; não é evidência de
CI nem de commit de release.

## Ambiente e proveniência

- Data: 2026-11-11.
- Base Git: `b0067a5f2daa8b8f3df393fa777cbf49114e6bb6`.
- O checkout local estava modificado; portanto, o resultado não deve ser
  atribuído ao SHA base, à `main` ou à tag `v1.0.0-rc1`.
- Java: 17.0.12; `JAVA_HOME` apontado para um JDK 17 durante a execução.
- Comando: `.\mvnw.cmd -B clean verify`.

## Resultado

| Suite | Testes | Falhas | Erros | Ignorados |
|---|---:|---:|---:|---:|
| `StudentApiControllerTest` | 6 | 0 | 0 | 0 |
| `TeacherApiControllerTest` | 4 | 0 | 0 | 0 |
| `HomeControllerTest` | 1 | 0 | 0 | 0 |
| `EducationalQualityProjectApplicationTests` | 1 | 0 | 0 | 0 |
| **Total** | **12** | **0** | **0** | **0** |

Resultado Maven: **BUILD SUCCESS**.

## JaCoCo local

| Métrica agregada | Coberto | Total | Cobertura |
|---|---:|---:|---:|
| Linhas | 85 | 169 | 50,30% |
| Branches | 9 | 36 | 25,00% |
| Instruções | 275 | 641 | 42,90% |
| Métodos | 40 | 73 | 54,79% |
| Classes | 10 | 10 | 100,00% |

Relatório HTML/XML gerado em `target/site/jacoco/`. Ainda não foi enviado
como artefato GitHub Actions. Não há limiar numérico aprovado; consultar
[critérios de release](../criterios.md).

## Limites desta execução

- Os testes REST usam serviços mockados e não validam persistência ou
  conectividade MongoDB.
- SonarCloud, CI GitHub em `main`, teste de carga e validação CRUD com MongoDB
  de teste não foram executados.
- Para evidência de release, repetir em checkout limpo do SHA candidato e
  anexar resultados ao run da tag.
