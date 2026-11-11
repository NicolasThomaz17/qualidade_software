# Resultado do teste de carga de 30 minutos

**Status: NÃO EXECUTADO.**

Não há evidência de execução de carga para o commit de preparação. Não
registrar este documento como resultado aprovado nem inferir métricas a
partir dos testes unitários ou da inicialização da aplicação.

| Campo | Resultado |
|---|---|
| Commit/tag de release | Pendente; esta preparação ainda não foi integrada à `main`. |
| Ambiente/alvo autorizado | Pendente de provisionamento e aprovação. |
| Ferramenta, versão e comando | Pendente. |
| Dataset | Pendente; deve ser sintético e isolado. |
| Duração/carga reais | Não medidos. |
| Throughput, p50, p95, p99 | Não medidos. |
| Taxa de erro e timeouts | Não medidas. |
| Resultado | Pendente; não liberar com base neste documento. |

Durante uma avaliação independente anterior, as rotas web de estudantes e
professores excederam o limite de requisição enquanto o log indicou timeout
na conexão ao MongoDB remoto. Essa observação é uma ocorrência de
disponibilidade, não um teste de carga nem uma medida de performance. Repetir
o ensaio apenas em ambiente isolado e autorizado.

Usar os parâmetros sujeitos a aprovação em
[plano de teste de carga](plano.md) e preencher esta tabela somente após
execução real. Associar os resultados ao SHA exato da tag candidata.
