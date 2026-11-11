# Plano de testes

**Estado:** plano proposto para revisão.

## Escopo

Validar compilação, testes unitários/MVC, contratos REST, renderização da
página inicial, persistência MongoDB, análise estática e carga. Os testes que
requerem MongoDB devem usar banco isolado e autorizado, nunca o banco de
produção.

## Cenários e evidências

| ID | Tipo | Cenário | Resultado esperado | Execução |
|---|---|---|---|---|
| T-01 | Build | `./mvnw -B clean verify` com Java 17 | Build verde; testes e relatório JaCoCo gerados. | CI em PR, `main` e tags `v*`. |
| T-02 | MVC | Página `/` | HTTP 200 e view `home`. | `HomeControllerTest`. |
| T-03 | API estudantes | Listar/obter/criar/remover, ID inexistente e e-mail/matrícula duplicados. | Códigos e JSON conforme `docs/api.md`. | Teste MVC com `StudentService` mockado; não substitui integração. |
| T-04 | API professores | Listar/obter/criar, ID inexistente e e-mail duplicado. | Códigos e JSON conforme `docs/api.md`. | Teste MVC com `TeacherService` mockado; não substitui integração. |
| T-05 | Integração | CRUD com MongoDB de teste isolado. | Persistência, leitura, atualização e remoção confirmadas. | Pendente de ambiente MongoDB isolado e autorizado. |
| T-06 | Static analysis | SonarCloud | Quality Gate aprovado no SHA candidato. | Pendente de projeto/variáveis/token SonarCloud. |
| T-07 | Carga | Cenário de 30 minutos aprovado. | Atingir limites aprovados em `docs/performance/plano.md`. | Pendente de aprovação do alvo e ambiente. |
| T-08 | Empacotamento | Conferir JAR e iniciar em ambiente configurado. | Aplicação inicia e páginas/API operam com banco disponível. | Pendente de ensaio de integração no ambiente de estágio. |

## Execução local

```bash
./mvnw -B clean verify
```

No Windows:

```powershell
.\mvnw.cmd -B clean verify
```

Guardar o log do comando e os diretórios `target/surefire-reports/` e
`target/site/jacoco/`. Antes de anexar logs, remover credenciais e dados
pessoais.

## Regras de entrada/saída

- Entrada: Java 17, dependências resolvíveis e checkout limpo.
- Testes MVC e build não exigem acesso ao MongoDB; testes de integração e
  carga exigem ambiente aprovado.
- Saída: build sem falhas, relatórios com o SHA, defeitos abertos registrados
  e aceite explícito dos gates externos antes da tag.
