# Plano de teste de carga — 30 minutos

**Estado:** proposta; requer aprovação do dono do ambiente antes de executar.

## Objetivo

Medir latência e taxa de erro das rotas de leitura da aplicação com MongoDB
de teste, usando carga controlada. O teste não deve apontar para produção,
para o banco educacional compartilhado ou para qualquer alvo sem autorização.

## Parâmetros propostos

- Duração total: 30 minutos.
- Aquecimento: 2 minutos a 1 usuário virtual.
- Ramp-up: 3 minutos até 10 usuários virtuais.
- Sustentação: 25 minutos a 10 usuários virtuais.
- Mistura de requests: 40% `GET /`, 30% `GET /api/students` e 30%
  `GET /api/teachers`.
- Dados: exclusivamente dados sintéticos em MongoDB descartável/de teste.
- Parâmetros são proposta, não baseline medida ou aprovada.

## Critérios propostos para aprovação

- Taxa de respostas não-2xx inferior a 1%.
- p95 inferior a 500 ms nas rotas de leitura.
- Nenhum erro de timeout/conexão MongoDB durante a sustentação.
- Sem crescimento contínuo de memória, threads ou conexões após o aquecimento.

O responsável pelo produto/ambiente deve aprovar ou substituir os limites
antes da execução. Registrar ferramenta e versão, SHA/tag, configuração,
dataset sintético, métricas agregadas e observações no
[resultado](resultado-30.md).
