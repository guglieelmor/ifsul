# Exercício - Refatorando a Cantina

**Tema da aula:** POO vs Programação Estruturada
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Explicar o que é um **paradigma**: "quais passos?" (estruturada) × "quem faz o quê?" (POO)
- Diferenciar a **divisão por funções** da **divisão por objetos**
- **Refatorar** um programa estruturado para classes, sem mudar o comportamento
- Diferenciar **classe** (molde) de **objeto** (instância com valores concretos)
- Identificar **classes**, **atributos** e **métodos** a partir de um enunciado
- Justificar **qual paradigma usar** em cada cenário

---

## Contexto

O controle de estoque da cantina do câmpus foi escrito no estilo estruturado: cada produto é uma posição `i` espalhada em **três vetores paralelos** (`nomes[i]`, `precos[i]`, `estoques[i]`), e as funções ficam do lado de fora, recebendo o índice.

| Índice | Produto | Preço | Estoque inicial |
|---|---|---|---|
| 0 | Pastel de carne | R$ 6,00 | 5 |
| 1 | Suco de laranja | R$ 4,50 | 15 |
| 2 | Café com leite | R$ 3,00 | 40 |

Regra de venda: **não vende quantidade menor ou igual a zero, nem mais do que tem no estoque.**

Você vai escrever a versão estruturada e depois **refatorá-la** para POO, seguindo os passos do slide. Lembre: refatorar é mudar a estrutura **sem mudar o comportamento**. As duas versões precisam vender, recusar e mostrar exatamente a mesma coisa.

---

## Requisitos

### Parte A: versão estruturada (`CantinaEstruturada.java`)

Use **só vetores e métodos `static`**, sem criar objetos.

A.1. Crie os três vetores paralelos (atributos `static` da classe), já com os valores da tabela:
- `String[] nomes`
- `double[] precos`
- `int[] estoques`

A.2. Crie as funções:
- `static boolean vender(int i, int qtd)`: desconta `qtd` do estoque do produto `i`. Recusa (devolve `false`) se `qtd` for menor ou igual a zero ou maior que o estoque.
- `static void repor(int i, int qtd)`: soma `qtd` ao estoque. Ignora `qtd` menor ou igual a zero.
- `static void mostrarEstoque(int i)`: imprime `Pastel de carne: R$ 6,00 - estoque 12`.

A.3. No `main`, nesta ordem:
1. Venda 3 pastéis e mostre o resultado. Mostre o total da venda (quantidade × preço).
2. Tente vender mais 3 pastéis (só sobraram 2).
3. Venda 2 sucos.
4. Tente vender 0 cafés.
5. Reponha 10 pastéis.
6. Mostre o estoque dos três produtos usando um `for`.
7. **Reproduza o problema:** escreva `estoques[1] = -5;` direto no `main` e mostre o estoque do suco.

### Parte B: refatorando para POO (`Produto.java` e `Main.java`)

Siga os passos do slide "Refatorando a Cantina":

B.1. **Agrupe os vetores paralelos e crie a classe** `Produto`:

| Atributo | Tipo | Visibilidade |
|---|---|---|
| `nome` | `String` | `private` |
| `preco` | `double` | `private` |
| `estoque` | `int` | `private` |

B.2. **Construtor** `Produto(String nome, double preco, int estoque)`.

B.3. **Transforme as funções em métodos**: `vender(i, qtd)` vira `produto.vender(qtd)`.
- `boolean vender(int qtd)`: mesma regra da Parte A.
- `void repor(int qtd)`: mesma regra da Parte A.
- `String getNome()`, `double getPreco()` e `int getEstoque()`.

B.4. Na classe `Main`, crie um método `static void mostrarEstoque(Produto p)` que imprime no mesmo formato da Parte A.

B.5. **Teste o mesmo cenário**: no `main`, crie os objetos `pastel`, `suco` e `cafe` e repita os passos 1 a 6 da Parte A. A saída precisa ser **igual** à da Parte A (só o título muda).

B.6. **Proteja os dados:** escreva `suco.estoque = -5;` e compile. Anote o erro que aparece, depois comente a linha (`//`) para o programa compilar.

### Parte C: responda como comentário no final do `Main.java`

**1. Classe ou objeto?** Marque e, se for objeto, diga de qual classe:

| # | Item |
|---|---|
| 01 | "Produto" |
| 02 | "O café com leite de R$ 3,00, com 40 unidades no estoque" |
| 03 | "Notebook" |
| 04 | "O notebook de patrimônio 4471, marca Dell" |
| 05 | "Lanche" |
| 06 | "O aluno Bruno, matrícula 2026102" |

**2. Do enunciado às classes.** Use os passos do slide (substantivos, verbos, "a ação vai para quem tem o dado"):

> "O bolsista registra o empréstimo de um notebook do laboratório para um aluno. Cada notebook tem patrimônio e marca e pode ser emprestado e devolvido. O sistema conta quantos empréstimos cada aluno já fez."

Liste as classes, os atributos de cada uma e os métodos de cada uma. Um colega propôs as classes `Emprestar` e `Patrimonio`: qual dos **3 erros comuns** do slide ele cometeu em cada caso?

**3. Estruturada ou POO?** Escolha e justifique em uma frase:

| # | Cenário |
|---|---|
| 01 | Programa que calcula, uma única vez, o troco de uma compra. |
| 02 | Sistema da biblioteca do câmpus, usado por anos e mantido por 3 bolsistas. |
| 03 | Sensor de nível da caixa d'água num microcontrolador de 2 KB: liga a bomba abaixo de 20%. |
| 04 | Sistema de matrículas com cursos, turmas, professores e regras que mudam todo semestre. |

**4.** Onde fica a regra "não vender sem estoque" em cada versão? Por que `estoques[1] = -5` funcionou na Parte A e `suco.estoque = -5` não compila na Parte B?

**5. Mito ou fato?** "A `CantinaEstruturada` usa a palavra `class`, então ela é orientada a objetos." Explique.

**6. Abordagem híbrida.** No método `vender()` da classe `Produto`, o que é POO e o que é programação estruturada?

---

## Saída esperada

**`CantinaEstruturada`**

```
=== Cantina estruturada ===
Vender 3 x Pastel de carne: true
Total: R$ 18,00
Vender 3 x Pastel de carne: false
Vender 2 x Suco de laranja: true
Vender 0 x Café com leite: false
Repor 10 x Pastel de carne
Pastel de carne: R$ 6,00 - estoque 12
Suco de laranja: R$ 4,50 - estoque 13
Café com leite: R$ 3,00 - estoque 40
Depois de estoques[1] = -5:
Suco de laranja: R$ 4,50 - estoque -5
```

**`Main`**

```
=== Cantina orientada a objetos ===
Vender 3 x Pastel de carne: true
Total: R$ 18,00
Vender 3 x Pastel de carne: false
Vender 2 x Suco de laranja: true
Vender 0 x Café com leite: false
Repor 10 x Pastel de carne
Pastel de carne: R$ 6,00 - estoque 12
Suco de laranja: R$ 4,50 - estoque 13
Café com leite: R$ 3,00 - estoque 40
```

**Erro ao compilar `suco.estoque = -5;`**

```
error: estoque has private access in Produto
```

> A vírgula em `R$ 6,00` vem do `printf` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

**O requisito mudou.** A coordenação pediu duas regras novas:

- não se pode vender **mais de 5 unidades** de uma vez;
- o estoque nunca pode passar de **50 unidades** numa reposição (`repor` ignora a reposição que ultrapassaria 50).

Implemente as duas regras **nas duas versões**. Depois, acrescente na Parte A um trecho que faz `estoques[2] -= 7;` direto no `main` (imitando "outra função" do sistema que alguém esqueceu de atualizar) e mostre que a regra das 5 unidades foi furada. Responda: em qual versão você precisou procurar mais lugares para garantir que a regra vale sempre?
