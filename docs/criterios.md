# Critérios de aceitação e release

**Estado:** critérios propostos, aguardando revisão por pares e aprovação.

## Critérios funcionais

1. As respostas e códigos HTTP de CRUD correspondem à
   [especificação](fse.md) e à [documentação da API](api.md).
2. Criações e alterações persistem corretamente em uma instância MongoDB de
   teste autorizada; esse ensaio não pode depender de dados de produção.
3. Erros de conexão ao banco não podem ser reportados como operação CRUD
   bem-sucedida.

## Critérios de qualidade

| Gate | Condição de aprovação | Evidência exigida |
|---|---|---|
| Build e testes | `./mvnw -B clean verify` termina com código zero e nenhum teste falha. | Job `verify` da CI no SHA candidato. |
| Revisão | Uma pessoa diferente do autor aprova o PR; nenhuma conversa bloqueadora fica aberta. | Revisão registrada no PR que introduziu o SHA candidato em `main`. |
| Cobertura | Relatório JaCoCo gerado e artefato baixável no mesmo run/SHA. O limiar numérico ainda precisa de aprovação do responsável do produto; não presumir um valor. | `jacoco.xml`/relatório HTML e metadados do artefato da CI. |
| Análise estática | Quality Gate SonarCloud aprovado no SHA candidato. | Página do projeto/Quality Gate com análise correspondente ao SHA. |
| Performance | Teste de carga aprovado conforme parâmetros assinados no plano e sem acesso não autorizado a sistemas compartilhados. | Resultado medido associado ao SHA, com métricas e ambiente. |
| Defeitos | Nenhum defeito crítico/alto aberto; defeitos restantes têm severidade, impacto, mitigação e aceite documentados. | [Registro de defeitos](defeitos/README.md) revisado. |
| Evidências | Todos os links do índice resolvem e apontam ao commit/tag exatos da release. | [Índice do pacote](evidencias/README.md). |

## Bloqueios de release atuais

- A tag anotada `v1.0.0-rc1` já existe no commit
  `1b21196e1a60ddc0ac7a173c7ef6fdd4e28eecfb`; ela não pode ser movida.
- Na avaliação da preparação, não existia workflow nem execução de Actions na
  `main`.
- SonarCloud, revisão por pares, teste de carga e validação CRUD no MongoDB
  aguardam evidência externa.
- Qualquer commit com as correções precisa de nova revisão, CI na `main` e
  nova tag candidata (`v1.0.0-rc2`), não de alteração da tag anterior.
