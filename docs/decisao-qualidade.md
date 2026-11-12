# Decisão de qualidade — v1.0.0-rc2

**Decisão: BLOQUEAR a promoção/liberação.**

Esta decisão avalia a tag anotada `v1.0.0-rc2`, no commit
`43f2702b3a65ef52b1d0a6c05e8648a247e7200a`, em 2026-11-11. A tag existe no
repositório público, mas sua existência não significa aprovação de qualidade.
Não promover este candidato a release ou produção. A RC1 permanece uma tag
distinta e imutável.

## Critérios e justificativas

| Critério | Resultado | Evidência e justificativa |
|---|---|---|
| CI verde na `main` e no commit candidato | **Reprovado** | O `main` público ainda aponta para `1b21196e1a60ddc0ac7a173c7ef6fdd4e28eecfb`, não para RC2; não há execução listada para a branch `main`. As execuções GitHub Actions de PR e push do commit `43f2702b3a65ef52b1d0a6c05e8648a247e7200a` falharam no job `Verify and coverage`. [Run do push](https://github.com/NicolasThomaz17/qualidade_software/actions/runs/43031156826) e [PR #2](https://github.com/NicolasThomaz17/qualidade_software/pull/2). O endpoint público de logs retornou 403 nesta avaliação; portanto, a causa específica da falha não foi confirmada. |
| Build e testes no commit tagueado | **Reprovado / sem evidência positiva** | A CI no SHA da tag falhou na etapa `Verify`, e o envio dos relatórios foi ignorado. O resultado local de 12 testes aprovados em [`docs/testes/resultado.md`](testes/resultado.md) foi produzido em checkout modificado antes do commit RC2; não demonstra que o SHA tagueado passa. |
| Revisão por pares | **Reprovado** | O [PR #2](https://github.com/NicolasThomaz17/qualidade_software/pull/2) está aberto, não foi mergeado e não apresenta revisão aprovada. Assim, os documentos e alterações da RC2 não têm aprovação independente demonstrada. |
| JaCoCo associado ao artefato da RC2 | **Pendente** | Há métricas locais (50,30% de linhas e 25% de branches), mas não um artefato de CI associado ao SHA da tag. Como `Verify` falhou, o passo de upload foi ignorado. Não foi aprovado um limiar de cobertura nos critérios consultados. |
| SonarCloud Quality Gate | **Pendente** | O check/job SonarCloud do commit foi marcado como `skipped`; não há link de análise ou Quality Gate aprovado. |
| Execução funcional com MongoDB | **Não demonstrada** | A evidência disponível da avaliação anterior em RC1 relata que `/` respondeu HTTP 200, mas `/students` e `/teachers` excederam o tempo limite durante timeout de conexão ao MongoDB. Isso não prova falha da aplicação RC2, mas tampouco há ensaio CRUD em MongoDB isolado para a tag avaliada. |
| Teste de carga | **Reprovado / não executado** | [`docs/performance/resultado-30.md`](performance/resultado-30.md) declara explicitamente que não houve execução nem métricas. O plano ainda depende de autorização/aprovação do alvo e dos limites. |
| Especificação, API, arquitetura e critérios | **Parcial** | Os documentos existem no candidato, mas são rascunhos/pendentes de revisão. O índice de evidências ainda se declara incompleto e mantém referências de preparação, não URLs de resultados vinculados à RC2. |
| Defeitos e risco operacional | **Pendente** | [`docs/defeitos/README.md`](defeitos/README.md) mantém D-001 aberto/em investigação e D-002 em triagem. Não há evidência de reprodução/resolução ou aceite formal dos responsáveis. |
| Relatório final e proveniência | **Reprovado** | [`docs/relatorio-qualidade.md`](relatorio-qualidade.md) está marcado provisório e contém a proveniência da preparação anterior; o índice [`docs/evidencias/README.md`](evidencias/README.md) declara-se incompleto. O material não demonstra um pacote final reconciliado com o SHA da tag. |
| Segurança para exposição em produção | **Não demonstrada** | A documentação da API registra CORS aberto (`*`) e ausência de autenticação/autorização. Não há nesta evidência uma avaliação/aprovação de segurança ou uma decisão explícita de exposição pública. Não afirmar que a API está adequada para produção. |

## Riscos aceitos

**Nenhum risco foi aceito para release ou produção.** A decisão é bloquear,
não há aceite formal anexado ao pacote e não é apropriado inferir aprovação
ou atribuir um responsável nominal por uma aceitação que não foi registrada.

Os riscos abaixo permanecem **não aceitos**; seus responsáveis individuais
precisam ser designados no fluxo de trabalho:

| Risco não aceito | Suporte/evidência | Justificativa para não aceitar | Responsável |
|---|---|---|---|
| O commit candidato pode não compilar ou passar os testes na CI. | Os runs do SHA da RC2 falharam no passo `Verify`; o log detalhado não foi acessível nesta avaliação. | O gate obrigatório de build/testes não está verde, e o resultado local anterior não pertence ao SHA tagueado. | Responsável por build/CI a designar; PR autor Nicolas Thomaz é a referência de autoria do ajuste de wrapper, não um aceite de risco. |
| CRUD pode falhar ou ficar indisponível por integração/conectividade MongoDB. | Timeout das rotas web observado na avaliação anterior da RC1; sem CRUD verificado em banco de teste na RC2. | Persistência é função central; não há teste de integração reproduzível no ambiente aprovado. | Responsável por aplicação/ambiente MongoDB a designar. |
| Desempenho sob carga é desconhecido. | Resultado de carga marcado “NÃO EXECUTADO”; sem latências, throughput ou taxa de erro medidos. | Não há dados para comparar com limites aprovados; plano e alvo também carecem de aprovação. | Responsável de performance/ambiente a designar. |
| Exposição de API pode permitir acesso/origens não pretendidas. | API documentada sem autenticação/autorização e com CORS `*`; sem aprovação de threat/security review. | Não foi demonstrado que a aplicação possa ser exposta a redes não confiáveis com segurança. | Responsável de segurança/produto a designar. |
| Defeitos e inconsistências documentais podem persistir. | D-001 aberto e D-002 em triagem no registro de defeitos. | Causa, impacto e resolução não foram confirmados nem aceitos formalmente. | Dono de produto e responsáveis técnicos a designar. |

## Integrantes identificados nos registros

O repositório não contém declarações pessoais assinadas ou aprovadas sobre
contribuições. A tabela abaixo é **atribuição de autoria verificável no
histórico Git**, não uma fala escrita em nome de cada integrante.

| Nome registrado | Contribuição verificável | Evidência |
|---|---|---|
| João Cavanhas | Autoria do commit de pipeline, testes MVC da API, JaCoCo e documentação/preparação da RC2; também aparece como tagger da `v1.0.0-rc2`. | Commit `3d65984217346c9a32694232ea256174e501450f`, PR #2 e metadados da tag. A contribuição não constitui aprovação de release. |
| Nicolas Thomaz | Autoria do ajuste do Maven Wrapper para LF/reexecução da CI; autoria de mudanças anteriores de build/layout/testes/checklist da RC1; autor do PR #2. | Commits `43f2702b3a65ef52b1d0a6c05e8648a247e7200a` e `b0067a5f2daa8b8f3df393fa777cbf49114e6bb6`; PR #2 aberto. |
| afonsolelis | Contribuição histórica à migração para MongoDB e à implementação das APIs REST de estudantes e professores. | Commit `5e5d4bada6b2d2951be7ce705b1bb265ba6f930a`. É autoria anterior; não há evidência de participação ou aceite na decisão da RC2. |

Não foi localizada revisão aprovada no PR #2. Qualquer declaração individual
adicional deve ser coletada diretamente dos integrantes, sem ser presumida
por este relatório.

## Condições para reconsiderar

1. Corrigir a falha `Verify` e obter CI verde no PR, na `main` após merge
   revisado e no commit exato de uma nova tag candidata; preservar os logs e
   relatórios.
2. Obter revisão por pares aprovada e atualizar a proveniência do pacote para
   o SHA definitivo.
3. Publicar o relatório JaCoCo como artefato desse run e obter/configurar o
   Quality Gate SonarCloud aplicável.
4. Executar CRUD contra MongoDB isolado e autorizado; registrar o resultado.
5. Aprovar previamente o alvo e os critérios de carga, então executar os 30
   minutos e registrar métricas reais.
6. Triar D-001/D-002, definir responsável e disposição; realizar revisão de
   segurança para qualquer exposição externa.
7. Atualizar o relatório final e o índice de evidências com links que
   comprovem o mesmo SHA/tag em todos os artefatos.

A `v1.0.0-rc2` já existe e não deve ser movida. Se forem necessárias mudanças,
elas devem compor um novo commit e uma nova tag candidata.

## Texto curto para o formulário do Hub

Repositório público: https://github.com/NicolasThomaz17/qualidade_software — tag avaliada: [`v1.0.0-rc2`](https://github.com/NicolasThomaz17/qualidade_software/tree/v1.0.0-rc2), commit `43f2702b3a65ef52b1d0a6c05e8648a247e7200a`. Decisão de qualidade: **Bloquear**; a CI falhou e as demais evidências de release permanecem incompletas.
