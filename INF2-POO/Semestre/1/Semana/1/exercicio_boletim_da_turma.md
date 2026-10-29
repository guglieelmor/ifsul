# Exercício - Boletim da Turma

**Tema da aula:** Introdução à Disciplina (por que precisamos organizar melhor os dados)
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Este exercício é uma revisão de **lógica de programação** em Java, sem orientação a objetos. Você vai praticar:

- Ler dados do teclado com `Scanner`
- Usar **variáveis**, **operadores** e **condicionais** (`if` / `else`)
- Repetir com **laços** (`for` e `while`)
- Validar uma entrada até ela ficar correta
- Guardar vários valores em **vetores** e percorrê-los
- Usar **acumuladores**, **contadores** e encontrar o **maior valor**

---

## Contexto

A coordenação precisa do boletim da turma INF2. Para cada aluno, o professor digita o **nome** e as **4 notas**. A primeira ideia foi criar uma variável para cada coisa:

```java
String nome1 = "Ana";
double n1_1 = 8.5, n1_2 = 7.0;
String nome2 = "Bruno";
double n2_1 = 6.0, n2_2 = 9.5;
// ... repetir até nome35...
```

Com 35 alunos isso vira centenas de variáveis e código repetido. A sua tarefa é escrever um programa que funcione para **qualquer quantidade de alunos**, usando laços e vetores.

> Nas próximas aulas, este mesmo problema vai ser resolvido com classes e objetos (a classe `Aluno`).

---

## Requisitos

Escreva tudo dentro do `main` da classe `Main`.

### Parte A: leitura e validação

1. Pergunte **quantos alunos** tem a turma.
2. Crie dois vetores com esse tamanho:
   - `String[] nomes`: o nome de cada aluno;
   - `double[] medias`: a média de cada aluno.
3. Para cada aluno, leia o **nome** e as **4 notas**.
4. Cada nota precisa estar entre **0 e 10**. Enquanto a nota digitada for inválida, mostre `Nota inválida! Digite de 0 a 10: ` e leia de novo (use `while`).
5. Calcule a média das 4 notas e guarde no vetor `medias`.

> **Dica de leitura:** para evitar problemas com o `Scanner`, leia sempre a linha inteira e converta:
> `int quantidade = Integer.parseInt(entrada.nextLine().trim());`
> `double nota = Double.parseDouble(entrada.nextLine().trim());`
> Digite as notas com **ponto** (`8.5`), não com vírgula.

### Parte B: boletim e resumo

1. Para cada aluno, imprima o nome, a média com 2 casas e a situação:
   - média **maior ou igual a 6,0** → `Aprovado`
   - média **menor que 6,0** → `Em recuperação`
2. No final, imprima um resumo com:
   - a **média da turma** (média das médias);
   - o aluno com a **maior média** e o valor dela;
   - quantos alunos foram **aprovados**;
   - quantos ficaram **em recuperação**.

> **Dica:** `System.out.printf("%.2f%n", valor);` mostra um número com 2 casas decimais.

### Parte C: responda como comentário no final do `Main.java`

1. Quantas variáveis seriam necessárias para guardar o nome e as 4 notas de 35 alunos sem usar vetores? Por que os vetores e os laços resolvem isso?
2. Por que a validação da nota usa `while` e não apenas um `if`?
3. Para encontrar o aluno com a maior média, por que é útil guardar a **posição** no vetor em vez de guardar só o valor da maior média?

---

## Exemplo de execução

Os valores depois de `?` e `:` são digitados pelo usuário.

```
Quantos alunos tem a turma? 3

--- Aluno 1 ---
Nome: Ana
Nota 1: 8.5
Nota 2: 7.0
Nota 3: 9.0
Nota 4: 15
Nota inválida! Digite de 0 a 10: 9.5

--- Aluno 2 ---
Nome: Bruno
Nota 1: 6.0
Nota 2: 5.5
Nota 3: 7.0
Nota 4: -1
Nota inválida! Digite de 0 a 10: 8.0

--- Aluno 3 ---
Nome: Carla
Nota 1: 4.0
Nota 2: 5.0
Nota 3: 6.5
Nota 4: 3.5

=== Boletim da turma ===
Ana: média 8,50 - Aprovado
Bruno: média 6,63 - Aprovado
Carla: média 4,75 - Em recuperação

=== Resumo ===
Média da turma: 6,63
Maior média: Ana (8,50)
Aprovados: 2
Em recuperação: 1
```

> A vírgula em `8,50` vem do `printf` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

1. Mostre também o aluno com a **menor média**.
2. Antes do resumo, liste **só os alunos em recuperação**.
3. Mude a regra: média **≥ 6,0** → `Aprovado`; entre **4,0 e 5,9** → `Em recuperação`; **abaixo de 4,0** → `Reprovado`. Conte quantos alunos ficaram em cada situação.
