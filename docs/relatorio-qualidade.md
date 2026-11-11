# Relatório de qualidade — preparação RC2

**Estado:** provisório, não é aceite final de release.
**Data desta preparação:** 2026-11-11.
**Referência anterior:** commit avaliado/tag `v1.0.0-rc1`,
`1b21196e1a60ddc0ac7a173c7ef6fdd4e28eecfb`. As mudanças deste relatório ainda
não pertencem a esse commit nem à tag existente.

## Evidências locais desta preparação

- `.\mvnw.cmd -B clean verify` com JDK 17 terminou em `BUILD SUCCESS`: 12
  testes, 0 falhas, 0 erros e 0 ignorados.
- JaCoCo local: 50,30% de linhas, 25,00% de branches e 42,90% de instruções.
- Detalhes e limites de proveniência em
  [resultado local dos testes](testes/resultado.md).
- O checkout estava modificado; não atribuir o resultado ao SHA base, à
  `main` ou à tag RC1. O relatório ainda não foi publicado como artefato de
  CI.

## Gates ainda não aprovados

- CI na `main` e execução da CI associada ao commit/tag definitivo: pendente.
- Revisão por pares: pendente.
- Testes CRUD contra MongoDB isolado: pendentes.
- Artefato JaCoCo publicado pela CI do SHA definitivo: pendente.
- SonarCloud Quality Gate: pendente de identificação/configuração do projeto,
  segredo `SONAR_TOKEN` e execução no SHA definitivo.
- Teste de carga de 30 minutos: não executado; consultar
  [resultado-30.md](performance/resultado-30.md).
- D-001 e D-002 do [registro de defeitos](defeitos/README.md): abertos/em
  triagem.

## Conclusão

**Não aprovado para release.** A tag `v1.0.0-rc1` já existe e não será
movida. As mudanças só podem ser candidatas a uma nova tag após revisão,
integração na `main`, CI verde no SHA candidato e fechamento/aceite dos gates
externos. A lista de links e a reconciliação de SHA ficam em
[docs/evidencias/README.md](evidencias/README.md).
