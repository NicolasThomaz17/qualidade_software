# Registro de defeitos e ocorrências

**Estado:** registro inicial; precisa ser revisado pelo time. Cada confirmação
deve incluir versão/SHA, ambiente, evidência sem segredos, severidade,
responsável e resolução.

| ID | Tipo / severidade | Estado | Evidência e impacto | Próxima ação |
|---|---|---|---|---|
| D-001 | Ocorrência de disponibilidade / alta (provisória) | Aberta, causa não confirmada | Na avaliação independente do commit `1b21196e1a60ddc0ac7a173c7ef6fdd4e28eecfb`, `/` respondeu HTTP 200; `/students` e `/teachers` excederam o limite de requisição. Log registrou timeout de leitura no MongoDB remoto. A funcionalidade dependente do banco não foi validada. | Reproduzir em ambiente isolado; verificar conectividade/configuração e só então classificar como defeito de aplicação ou incidente do serviço. |
| D-002 | Inconsistência de documentação/artefato / baixa (provisória) | Em triagem | A aplicação usa Spring Data MongoDB, mas há migration SQL PostgreSQL em `src/main/resources/db/migration/`; sua execução não foi verificada. | Confirmar se é resíduo didático ou parte do produto; documentar ou remover em alteração aprovada. |

As severidades são provisórias até revisão do responsável. Não há evidência
de que D-001 seja causado por código, nem de que a migração de D-002 seja
executada em runtime. Não fechar itens sem reprodução e evidência.
