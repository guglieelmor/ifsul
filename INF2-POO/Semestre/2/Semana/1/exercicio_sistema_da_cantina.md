# Exercício - Sistema da Cantina

**Tema da aula:** Revisão de POO + Conversão de Tipos + Estático × Instância
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai praticar:

- Diferenciar **classe** de **objeto**
- Declarar **atributos**, **métodos** e **construtores** usando `this`
- Proteger o estado de um objeto com **encapsulamento** (`private`, getters e setters com validação)
- Converter `String` → número (`parseDouble`, `parseInt`) tratando a **vírgula**
- Usar **wrappers**, **autoboxing**, **unboxing** e evitar o `NullPointerException`
- Diferenciar **cast** (`(int)`) de **arredondamento** (`Math.round`)
- Decidir quando um método deve ser **`static`** ou **de instância**

---

## Contexto

A cantina do câmpus pediu uma nova versão do sistema. A primeira versão tinha três problemas:

1. **Preço negativo aceito**: alguém digitou `-5` no preço do pastel e o sistema gravou.
2. **Texto que não vira número**: o formulário enviou `"6,50"` e o programa parou com erro.
3. **Estoque que muda sozinho**: vender pastéis baixava também o estoque do suco.

A sua tarefa é escrever a nova versão sem esses problemas.

---

## Requisitos

### 1. Classe `Produto`

| Atributo | Tipo | Visibilidade |
|---|---|---|
| `nome` | `String` | `private` |
| `preco` | `double` | `private` |
| `estoque` | `int` | `private` |

1.1. **Construtor** `Produto(String nome, double preco, int estoque)` usando `this`.
- O preço deve passar pela mesma validação do setter.
- Estoque negativo não é aceito (o produto fica com `0`).

1.2. **Getters**: `getNome()`, `getPreco()`, `getEstoque()`.

1.3. **Setter** `setPreco(double preco)`: só aceita valores **maiores que zero**. Se o valor for inválido, o preço continua o mesmo.

1.4. **Não crie `setEstoque`.** O estoque só muda por estes métodos:
- `boolean vender(int qtd)`: baixa o estoque e devolve `true`. Recusa (devolve `false`) se `qtd <= 0` ou se não houver estoque suficiente.
- `boolean repor(int qtd)`: soma ao estoque e devolve `true`. Recusa se `qtd <= 0`.

1.5. `double calcularTotal(int qtd)`: devolve `preco * qtd`.

1.6. `double valorEmEstoque()`: devolve `preco * estoque`.

1.7. `static String formatarPreco(double valor)`: devolve o valor no formato `"R$ 6,50"` (dica: `String.format("%.2f", valor)`).

1.8. `toString()` no formato: `Pastel | R$ 6,50 | estoque: 40`

### 2. Classe `Conversor` (utilitária)

Todos os métodos devem ser `static`:

2.1. `textoParaPreco(String texto)`: converte `"6,50"` em `6.5`. Remova os espaços (`trim()`) e troque a vírgula por ponto antes do `parseDouble`.

2.2. `textoParaQuantidade(String texto)`: converte `"40"` em `40` (com `trim()`).

> Nesta semana considere que o atendente sempre digita números. Tratamento de `NumberFormatException` fica para as semanas 11–14.

### 3. Classe `Main`

**Parte A: do formulário ao objeto**

Os dados chegam do formulário como `String`:

```java
String[][] formulario = {
    {"Pastel", "6,50", "40"},
    {"Suco", "4,00", "25"},
    {"Coxinha", " 5,00 ", "0"}
};
```

Converta cada linha usando o `Conversor`, crie um vetor de `Produto` e imprima cada produto.

**Parte B: encapsulamento**

1. Tente `setPreco(-5)` no pastel e mostre que o preço não mudou.
2. Venda 3 pastéis, depois tente vender 50 pastéis.
3. Tente vender 1 coxinha (sem estoque), depois reponha 20 coxinhas.
4. Imprima os três produtos e confirme que vender pastel **não alterou** o suco.

**Parte C: wrappers**

1. Crie um `Map<String, Integer> vendasDoDia` e registre: Pastel → 3 e Suco → 5. Venda também as 5 unidades do suco no objeto.
2. Leia a quantidade de pastéis vendidos para um `int` (**unboxing**).
3. Leia as coxinhas vendidas **sem causar `NullPointerException`**: "Coxinha" não está no mapa.

**Parte D: conversão entre primitivos**

1. Calcule o total vendido no dia (pastéis + sucos) usando `calcularTotal`.
2. Mostre o total convertido com `(int)` e com `Math.round`.
3. Guarde o estoque do pastel numa variável `double` (widening) e imprima.

**Parte E: estático × instância**

Para cada produto, imprima o `valorEmEstoque()` formatado com `Produto.formatarPreco(...)`.

**Parte F: responda como comentário no final do `Main.java`**

1. Por que `formatarPreco` e os métodos de `Conversor` são `static`, e `vender`/`valorEmEstoque` não são?
2. O que aconteceria se `estoque` fosse declarado `private static int estoque`?
3. O que acontece com `Double.parseDouble("6,50")`? Por quê?
4. Por que a classe `Produto` não tem `setEstoque`?
5. Qual a diferença entre o resultado de `(int)` e de `Math.round` no total do dia?

---

## Saída esperada

```
=== Cadastro ===
Pastel | R$ 6,50 | estoque: 40
Suco | R$ 4,00 | estoque: 25
Coxinha | R$ 5,00 | estoque: 0

=== Encapsulamento ===
Depois de setPreco(-5): R$ 6,50
Vender 3 pastéis: true
Vender 50 pastéis: false
Vender 1 coxinha: false
Repor 20 coxinhas: true
Pastel | R$ 6,50 | estoque: 37
Suco | R$ 4,00 | estoque: 25
Coxinha | R$ 5,00 | estoque: 20

=== Vendas do dia ===
Pastéis vendidos: 3
Coxinhas vendidas: 0

=== Fechamento do caixa ===
Total vendido: R$ 39,50
Com cast (int): 39
Com Math.round: 40
Estoque do pastel como double: 37.0

=== Valor em estoque ===
Pastel: R$ 240,50
Suco: R$ 80,00
Coxinha: R$ 100,00
```

> A vírgula em `R$ 6,50` vem do `String.format` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

Organize o projeto em **pacotes**, seguindo a convenção do slide:

```
src/br/edu/ifsul/cantina/
├── modelo/Produto.java
├── util/Conversor.java
└── app/Main.java
```

Declare o `package` de cada classe e faça os `import` necessários. Depois responda: qual modificador de acesso precisaria ter um método de `Produto` que só as classes do pacote `cantina.modelo` podem chamar?
