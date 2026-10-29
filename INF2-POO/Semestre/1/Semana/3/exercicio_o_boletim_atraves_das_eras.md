# Exercício - O Boletim Através das Eras

**Tema da aula:** Contexto Histórico (da programação procedural à orientação a objetos)
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Situar as **5 eras da programação** numa linha do tempo
- Explicar que problema a POO veio resolver
- Escrever o mesmo boletim no estilo **procedural** e no estilo **orientado a objetos**
- Ver na prática a ideia de **objetos que recebem mensagens** e respondem com os próprios dados
- Reconhecer os **4 pilares da POO** (encapsulamento, herança, polimorfismo e abstração) em exemplos
- Explicar o caminho **código-fonte → bytecode → JVM** do Java

---

## Contexto

Na aula, o mesmo problema apareceu em cada era da programação: o **boletim do aluno**.

| Aluno | Nota 1 | Nota 2 | Nota 3 |
|---|---|---|---|
| Ana Souza | 7,5 | 8,0 | 6,0 |
| João Lima | 5,0 | 6,5 | 4,0 |

Regra: **média = (nota 1 + nota 2 + nota 3) ÷ 3**. Média **maior ou igual a 6,0** → `APROVADO`; abaixo disso → `REPROVADO`. Cada nota vale de **0 a 10**.

Você vai escrever o boletim **duas vezes**: primeiro como um programador de C dos anos 1970 faria (dados soltos e funções separadas), depois como se faz em Java desde 1995 (dados e regras juntos numa classe).

---

## Requisitos

### Parte A: versão procedural (`BoletimProcedural.java`)

Use **só variáveis e métodos `static`**, sem criar objetos. Imite o boletim em C do slide.

A.1. Crie as variáveis soltas (atributos `static` da classe), **um conjunto por aluno**:
- `nomeAna`, `n1Ana`, `n2Ana`, `n3Ana`
- `nomeJoao`, `n1Joao`, `n2Joao`, `n3Joao`

A.2. Crie as funções, separadas dos dados:
- `static double media(double a, double b, double c)`: devolve a média das três notas.
- `static boolean aprovado(double m)`: devolve `true` se a média for maior ou igual a 6,0.
- `static void mostrarBoletim(String nome, double a, double b, double c)`: imprime `Ana Souza: média 7,17 - APROVADO`.

A.3. No `main`:
1. Mostre o boletim da Ana e do João.
2. **Reproduza o problema:** escreva `n1Ana = 11;` direto no `main` e mostre o boletim da Ana de novo.

### Parte B: versão orientada a objetos (`Aluno.java` e `Main.java`)

B.1. Crie a classe `Aluno` (a mesma do diagrama do slide):

| Atributo | Tipo | Visibilidade |
|---|---|---|
| `nome` | `String` | `private` |
| `notas` | `double[]` (tamanho 3) | `private` |
| `qtd` | `int` (quantas notas já foram lançadas) | `private` |

B.2. **Construtor** `Aluno(String nome)`. O aluno começa sem notas.

B.3. **Métodos** (as mensagens que um aluno sabe responder):
- `boolean lancarNota(double n)`: guarda a nota e devolve `true`. Recusa (devolve `false`) se a nota for menor que 0, maior que 10 ou se as 3 notas já foram lançadas.
- `double calcularMedia()`: devolve a média das notas lançadas (ou `0` se ainda não tem nota).
- `boolean estaAprovado()`: devolve `true` se a média for maior ou igual a 6,0.
- `String getNome()`.

B.4. Na classe `Main`, crie um método `static void mostrarBoletim(Aluno aluno)` que imprime no mesmo formato da Parte A. Ele faz o papel da **secretaria**: só envia mensagens ao objeto (`estaAprovado()`, `calcularMedia()`) e não conhece a fórmula.

B.5. No `main`:
1. Crie três alunos: Ana Souza, João Lima e Bia Rocha.
2. Lance as notas da Ana nesta ordem: `7.5`, `8.0`, **`11`** (mostre o resultado), `6.0` e **`9.0`** (uma 4ª nota; mostre o resultado).
3. Lance as notas do João (tabela acima) e da Bia (`9.0`, `10.0`, `8.5`).
4. **Tente reproduzir o problema:** escreva `ana.notas[0] = 11;` e compile. Anote o erro que aparece, depois comente a linha (`//`) para o programa compilar.
5. Mostre o boletim dos três alunos.

### Parte C: responda como comentário no final do `Main.java`

**1. Linha do tempo.** Coloque as eras em ordem e escreva uma linguagem de cada uma:
`Multiparadigma` · `Procedural` · `Baixo nível` · `Consolidação` · `Nasce a POO`

**2. Procedural ou OO?** Marque o paradigma e justifique em uma frase:

| # | Situação |
|---|---|
| 01 | Os preços dos produtos ficam num vetor e o total é calculado numa função `calcularTotal()` separada. |
| 02 | Para sacar dinheiro é preciso chamar `contaDaAna.sacar(50)`, que recusa se não houver saldo. |
| 03 | Qualquer função do sistema pode alterar a variável global `estoqueTotal`. |
| 04 | A classe `Livro` tem o método `emprestar()`, que usa a informação de disponibilidade guardada no próprio objeto. |
| 05 | O `main()` chama, em sequência, `lerDados()`, `processar()` e `imprimir()`. |

**3. Os 4 pilares.** Diga qual pilar aparece em cada frase. Quais deles aparecem no seu código?

| # | Frase |
|---|---|
| a | As notas são `private` e só mudam por `lancarNota()`. |
| b | A secretaria chama `estaAprovado()` sem conhecer a fórmula da média. |
| c | `AlunoIntegrado` é um `Aluno`: aproveita nome e notas e ganha o curso técnico. |
| d | `calcularMedia()` faz média simples em `Aluno` e média com pesos em `AlunoSuperior`. |

**4.** Na versão procedural, quantas variáveis seriam necessárias para 40 alunos? E na versão OO, quantos objetos e quantas classes?

**5.** Por que `n1Ana = 11` funciona na Parte A, enquanto na Parte B `ana.notas[0] = 11` não compila e `ana.lancarNota(11)` devolve `false`?

**6.** Ao rodar `javac *.java`, que arquivo é gerado para a classe `Aluno`? Por que esse mesmo arquivo roda no Windows do laboratório e no Linux de casa, enquanto um programa em C precisaria ser compilado de novo?

---

## Saída esperada

**`BoletimProcedural`**

```
=== Boletim procedural ===
Ana Souza: média 7,17 - APROVADO
João Lima: média 5,17 - REPROVADO
Depois de n1Ana = 11:
Ana Souza: média 8,33 - APROVADO
```

**`Main`**

```
=== Boletim orientado a objetos ===
Ana: lançar nota 11 -> false
Ana: lançar 4ª nota (9.0) -> false
Ana Souza: média 7,17 - APROVADO
João Lima: média 5,17 - REPROVADO
Bia Rocha: média 9,17 - APROVADO
```

**Erro ao compilar `ana.notas[0] = 11;`**

```
error: notas has private access in Aluno
```

> A vírgula em `7,17` vem do `printf` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

Responda à pergunta do slide: **"Que outros dados e regras a classe Aluno deveria ter?"**

- Acrescente o atributo `private String turma` (ex.: `"INF2"`) ao construtor e um `getTurma()`.
- Crie o método `boolean estaCompleto()`, que devolve `true` só quando as 3 notas já foram lançadas.
- Mude `mostrarBoletim` para imprimir `EM ANDAMENTO` quando o aluno ainda não tem as 3 notas, em vez de `APROVADO` ou `REPROVADO`.

No `main`, crie um quarto aluno com só duas notas lançadas e mostre que o boletim dele aparece como `EM ANDAMENTO`. Repare que a classe `Main` não precisou saber **como** o aluno conta as notas: só mandou a mensagem `estaCompleto()`.
