# Changelog

## Preparação para v1.0.0-rc2 (não publicada)

Esta seção registra mudanças em preparação no branch de trabalho. Não
representa uma release, não altera a tag `v1.0.0-rc1` e não contém resultados
de CI, SonarCloud ou carga ainda não executados.

### Incluído
- Pipeline GitHub Actions para `./mvnw -B clean verify` em PRs para `main`,
  pushes em `main` e tags `v*`, com artefatos Surefire e JaCoCo.
- Geração local do relatório JaCoCo durante `verify`.
- Testes MVC isolados dos endpoints de estudantes e professores.
- Verificação local nesta preparação: 12 testes passaram; JaCoCo mediu
  50,30% de linhas e 25,00% de branches. O checkout estava modificado; estes
  números não são evidência de CI ou de release.
- Documentação de especificação, arquitetura, API, critérios e plano de
  testes em `docs/`.
- Índice de evidências e relatórios provisórios com itens externos
  explicitamente pendentes.

### Pendente antes da release
- Merge por revisão por pares, execução verde na `main` e execução vinculada
  ao commit candidato.
- Configuração do projeto e token SonarCloud, Quality Gate aprovado e
  evidência publicada.
- Execução aprovada do teste de carga e registro de resultados medidos.
- Verificação funcional das rotas que dependem do MongoDB.

## 2026-02-10 - Falha no `mvn clean install` por `MockMvc` ausente

### O que aconteceu
Ao executar:

```bash
mvn clean install
```

o build falhou na fase de testes com o erro:

```text
No qualifying bean of type 'org.springframework.test.web.servlet.MockMvc' available
```

Arquivo afetado:
- `src/test/java/com/example/educationalqualityproject/controller/HomeControllerTest.java`

### Causa raiz (explicacao para alunos)
No teste foi usada a anotacao `@AutoConfigureWebMvc`, mas ela **nao registra** o bean `MockMvc` para injecao no teste.
Como o campo abaixo depende desse bean, a injecao falha:

```java
@Autowired
private MockMvc mockMvc;
```

### Como corrigir (passo a passo)
1. Abra o arquivo `src/test/java/com/example/educationalqualityproject/controller/HomeControllerTest.java`.
2. Troque o import:

```java
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
```

por:

```java
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
```

3. Troque a anotacao da classe:

```java
@AutoConfigureWebMvc
```

por:

```java
@AutoConfigureMockMvc
```

4. Salve o arquivo.
5. Rode novamente:

```bash
mvn clean install
```

6. Verifique se os testes passam.

### Exemplo final esperado do teste
```java
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHomeView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
```

### Nota didatica
Resumo para memorizar:
- `@AutoConfigureWebMvc` configura infraestrutura MVC.
- `@AutoConfigureMockMvc` disponibiliza `MockMvc` para testes web.
