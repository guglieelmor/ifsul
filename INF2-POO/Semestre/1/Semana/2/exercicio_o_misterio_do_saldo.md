# Exercício - O Mistério do Saldo

**Tema da aula:** Motivação da POO (programação estruturada × orientada a objetos)
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Escrever o mesmo problema em **dois paradigmas**: estruturado e orientado a objetos
- Ver na prática por que **variáveis globais** e **dados sem proteção** causam erros
- Reconhecer **variável global**, **código duplicado**, **dados sem proteção** e **acoplamento**
- Descrever um objeto como **dados (atributos) + comportamento (métodos)**
- Identificar **classes**, **atributos** e **métodos** num cenário do câmpus

---

## Contexto

Uma aluna reclamou na cantina:

> "Meu cartão da cantina tinha R$ 20,00. Comprei um pastel de R$ 6,00 e o sistema agora mostra R$ -50,00."

Por trás da tela, o saldo dos cartões fica num vetor global, e 40 funções do sistema podem alterá-lo. Ninguém sabe quem mudou o valor.

Você vai escrever **duas versões** do sistema de cartões: primeiro a estruturada, para reproduzir o problema, e depois a orientada a objetos, para resolvê-lo.

---

## Requisitos

### Parte A: versão estruturada (`CantinaEstruturada.java`)

Use **só vetores e métodos `static`**, sem criar objetos.

A.1. Crie dois vetores globais (atributos `static` da classe):
- `String[] titulares = {"Ana", "Bruno"};`
- `double[] saldo = new double[2];` (posição `0` é a Ana, posição `1` é o Bruno)

A.2. Crie os métodos:
- `static boolean recarregar(int aluno, double valor)`: soma o valor ao saldo. Recusa (devolve `false`) se o valor for menor ou igual a zero.
- `static boolean pagar(int aluno, double valor)`: desconta o valor. Recusa se o valor for menor ou igual a zero ou maior que o saldo.
- `static void mostrarSaldo(int aluno)`: imprime `Saldo de Ana: R$ 14,00`.

A.3. No `main`:
1. Recarregue R$ 20,00 para a Ana.
2. A Ana paga um pastel de R$ 6,00. Mostre o saldo.
3. A Ana tenta pagar R$ 20,00. Mostre o saldo.
4. **Reproduza o mistério:** escreva `saldo[0] = -50;` direto no `main` e mostre o saldo dos dois alunos.

### Parte B: versão orientada a objetos (`CartaoCantina.java` e `Main.java`)

B.1. Crie a classe `CartaoCantina`:

| Atributo | Tipo | Visibilidade |
|---|---|---|
| `matricula` | `String` | `private` |
| `titular` | `String` | `private` |
| `saldo` | `double` | `private` |

B.2. **Construtor** `CartaoCantina(String matricula, String titular)`. O saldo começa em `0`.

B.3. **Métodos** (agora sem o parâmetro `aluno`: cada cartão cuida do próprio saldo):
- `boolean recarregar(double valor)`: mesma regra da Parte A.
- `boolean pagar(double valor)`: mesma regra da Parte A.
- `double consultarSaldo()`: devolve o saldo.
- `String getTitular()` e `String getMatricula()`.

B.4. Na classe `Main`, crie um método `static void mostrarSaldo(CartaoCantina cartao)` que imprime no mesmo formato da Parte A.

B.5. No `main`:
1. Crie dois cartões: Ana (`"2026101"`) e Bruno (`"2026102"`).
2. Repita os passos 1 a 3 da Parte A com a Ana.
3. A Ana tenta recarregar R$ -5,00. Mostre o saldo.
4. Recarregue R$ 4,00 para o Bruno e tente pagar um pastel de R$ 6,00. Mostre o saldo dele.
5. **Tente reproduzir o mistério:** escreva `ana.saldo = -50;` e compile. Anote o erro que aparece, depois comente a linha (`//`) para o programa compilar.
6. Mostre o saldo da Ana de novo.

### Parte C: responda como comentário no final do `Main.java`

**1. Qual é o problema?** Para cada situação, diga se é **variável global**, **duplicação**, **sem proteção** ou **acoplamento** (pode ter mais de um):

| # | Situação |
|---|---|
| 01 | A função `gerarRelatorio()` zerou sem querer a variável global `totalVendas`. |
| 02 | O desconto de 10% para servidores está copiado em 5 funções diferentes. |
| 03 | Um trecho do código fez `estoque[7] = -3` e o sistema aceitou. |
| 04 | Trocar o preço de `float` para `int` (centavos) obrigou a alterar 38 funções. |
| 05 | Corrigiram o arredondamento em `pagar()`, mas esqueceram de corrigir em `estornar()`. |
| 06 | Para testar `pagar()`, é preciso preparar antes todas as variáveis globais. |

**2. Identificando classes.** No texto abaixo, liste as **classes** (substantivos), os **atributos** (características) e os **métodos** (verbos):

> "Na biblioteca do câmpus, o aluno pega um livro emprestado. Cada livro tem título, autor e ISBN e pode ser emprestado e devolvido."

**3.** Por que `saldo[0] = -50` funciona na Parte A e `ana.saldo = -50` não compila na Parte B?

**4.** Se cada função pode falar com qualquer outra, os pares possíveis são `n × (n − 1) ÷ 2`. Quantos pares existem com 10 funções? E com 20?

---

## Saída esperada

**`CantinaEstruturada`**

```
=== Versão estruturada ===
Recarregar R$ 20,00 para Ana: true
Ana paga pastel de R$ 6,00: true
Saldo de Ana: R$ 14,00
Ana tenta pagar R$ 20,00: false
Saldo de Ana: R$ 14,00
Depois de saldo[0] = -50:
Saldo de Ana: R$ -50,00
Saldo de Bruno: R$ 0,00
```

**`Main`**

```
=== Versão orientada a objetos ===
Recarregar R$ 20,00 para Ana: true
Ana paga pastel de R$ 6,00: true
Saldo de Ana: R$ 14,00
Ana tenta pagar R$ 20,00: false
Ana tenta recarregar R$ -5,00: false
Saldo de Ana: R$ 14,00
Recarregar R$ 4,00 para Bruno: true
Bruno tenta pagar pastel de R$ 6,00: false
Saldo de Bruno: R$ 4,00
Saldo de Ana: R$ 14,00
```

**Erro ao compilar `ana.saldo = -50;`**

```
error: saldo has private access in CartaoCantina
```

> A vírgula em `R$ 14,00` vem do `printf` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

Com base no **Caso 1** do slide, crie a classe `Livro`:

- atributos `private`: `titulo`, `autor`, `isbn` e `disponivel` (começa `true`);
- `boolean emprestar()`: recusa se o livro já estiver emprestado;
- `boolean devolver()`: recusa se o livro já estiver disponível.

No `main`, crie dois livros, empreste o mesmo livro duas vezes e mostre que a segunda tentativa é recusada, enquanto o outro livro continua disponível.
