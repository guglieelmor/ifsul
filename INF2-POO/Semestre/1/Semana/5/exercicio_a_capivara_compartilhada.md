# Exercício - A Capivara Compartilhada

**Tema da aula:** Classes e Objetos
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Separar **estado** (atributos) de **comportamento** (métodos)
- Escrever uma classe Java com **atributos**, **construtores** (inclusive dois, por sobrecarga) e **métodos**
- Usar métodos `void`, com **parâmetros** e com **retorno**
- Criar **objetos independentes** com `new`
- Entender que a variável guarda uma **referência** (stack) e o objeto fica no **heap**
- Ver na prática o que acontece em `c2 = c1`, em `==`, com `null` e com o **Garbage Collector**

---

## Contexto

O grêmio estudantil lançou o **Bichinhos do Câmpus**: cada aluno adota um bichinho virtual e cuida dele durante o semestre. O bichinho sente fome, cansa de brincar e recupera a energia dormindo.

No primeiro dia, a Ana adotou a capivara **Pipoca** e o Bruno adotou o gato **Faísca**. Um ovo misterioso também apareceu, ainda sem nome.

Aí veio o problema. A Carla queria uma capivara igual à da Ana e escreveu:

```java
Bichinho bichinhoDaCarla = pipoca;
```

A Carla passou a tarde brincando com o "bichinho dela". No fim do dia, a Ana abriu o app e encontrou a Pipoca **exausta e faminta**, sem ter brincado com ela.

Sua tarefa é escrever a classe `Bichinho`, reproduzir o caso da capivara compartilhada e explicar o que aconteceu na memória.

---

## Requisitos

### Parte A: a classe `Bichinho` (`Bichinho.java`)

A.1. **Abstração.** O bichinho só guarda o que importa para o jogo:

| Atributo | Tipo | Significado |
|---|---|---|
| `nome` | `String` | nome do bichinho |
| `especie` | `String` | capivara, gato, ovo… |
| `fome` | `int` | de `0` (satisfeito) a `10` (morrendo de fome) |
| `energia` | `int` | de `0` (exausto) a `10` (cheio de energia) |
| `dias` | `int` | quantas noites o bichinho já dormiu |

Todos os atributos são `private`, como recomendado em **Boas Práticas**.

A.2. **Construtores** (sobrecarga):
- `Bichinho(String nome, String especie)`: começa com `fome = 5`, `energia = 10` e `dias = 0`.
- `Bichinho()`: cria um ovo, com nome `"Sem nome"`, espécie `"ovo"` e os mesmos valores iniciais.

A.3. **Métodos:**

| Método | O que faz |
|---|---|
| `void alimentar()` | diminui a fome em 3 (nunca abaixo de 0) |
| `boolean brincar()` | se a energia for menor que 3, devolve `false` e não muda nada. Senão, gasta 3 de energia, aumenta a fome em 2 (nunca acima de 10) e devolve `true` |
| `void dormir()` | energia volta a 10, passa 1 dia e a fome aumenta 1 (nunca acima de 10) |
| `String humor()` | `"faminto"` se fome ≥ 8; senão `"cansado"` se energia ≤ 2; senão `"feliz"` |
| `String obterInfo()` | devolve `Pipoca (capivara) - dia 0 \| fome 5/10 \| energia 10/10 \| feliz` |
| `String getNome()` | devolve o nome |

> Repare que `humor` **não é atributo**: ele é calculado a partir da fome e da energia toda vez que alguém pergunta.

### Parte B: o caso da capivara compartilhada (`Main.java`)

Faça tudo na ordem abaixo, imprimindo os títulos de cada bloco como na saída esperada.

**B.1. Dia da adoção.** Crie três objetos com `new`: `pipoca` (capivara), `faisca` (gato) e `ovo` (construtor sem parâmetros). Imprima o `obterInfo()` dos três.

**B.2. Tarde no pátio.**
1. A Ana alimenta a Pipoca e brinca com ela uma vez (imprima o resultado).
2. O Bruno tenta brincar com o Faísca **4 vezes** usando um `for` (imprima cada resultado).
3. Imprima o `obterInfo()` da Pipoca e do Faísca.

**B.3. A Carla "adota" um bichinho.**
1. Escreva `Bichinho bichinhoDaCarla = pipoca;`
2. A Carla brinca **duas vezes** com `bichinhoDaCarla` (imprima os resultados).
3. Imprima o `obterInfo()` do bichinho da Carla e da Pipoca.
4. Imprima o resultado de `pipoca == bichinhoDaCarla`.

**B.4. A Carla ganha o próprio bichinho.**
1. Faça `bichinhoDaCarla = new Bichinho("Pipoca", "capivara");` (mesmo nome, mesma espécie).
2. Imprima o `obterInfo()` dos dois e o resultado de `pipoca == bichinhoDaCarla` de novo.

**B.5. Noite.** A Pipoca e o Faísca dormem. Imprima o `obterInfo()` dos quatro bichinhos (Pipoca, Faísca, o da Carla e o ovo).

**B.6. O bichinho fantasma.** No fim do `main`, escreva as duas linhas abaixo, rode o programa e anote o erro. Depois comente as linhas (`//`).

```java
Bichinho fantasma = null;
fantasma.brincar();
```

### Parte C: responda como comentário no final do `Main.java`

**1. Classe, objeto ou nenhum dos dois?** Se for objeto, diga de qual classe:

| # | Item |
|---|---|
| 01 | "Bichinho" |
| 02 | "A capivara Pipoca da Ana, com fome 8" |
| 03 | "Especie" |
| 04 | "O gato Faísca, no dia 1" |
| 05 | "O arquivo `Bichinho.java`" |

**2. Abstração.** Por que `fome` e `energia` entraram na classe, e `corDosOlhos`, `dataDoDesenho` e `tamanhoDaImagemEmKB` ficaram de fora? Em que tipo de sistema a `corDosOlhos` seria importante?

**3. Desenhe a memória** (stack e heap, em texto) logo **depois** de `Bichinho bichinhoDaCarla = pipoca;`. Quantas variáveis existem? Quantos objetos? Use o desenho para explicar por que a Pipoca da Ana cansou.

**4.** No bloco B.4, os dois bichinhos têm o mesmo nome e a mesma espécie, mas `pipoca == bichinhoDaCarla` dá `false`. Por quê?

**5.** Que erro apareceu no bloco B.6? O programa **compilou**? Por que o erro só aparece ao rodar?

**6.** Um colega escreveu `public void Bichinho(String nome, String especie)`, com `void`. O que acontece com a linha `new Bichinho("Pipoca", "capivara")`? Por quê?

**7. Ciclo de vida.** Se, no fim do `main`, fizermos `pipoca = null;`, o que acontece com o **primeiro** objeto Pipoca? Alguém ainda aponta para ele? Quem libera essa memória?

---

## Saída esperada

```
=== Dia da adoção ===
Pipoca (capivara) - dia 0 | fome 5/10 | energia 10/10 | feliz
Faísca (gato) - dia 0 | fome 5/10 | energia 10/10 | feliz
Sem nome (ovo) - dia 0 | fome 5/10 | energia 10/10 | feliz

=== Tarde no pátio ===
Ana brinca com Pipoca: true
Bruno brinca com Faísca (1): true
Bruno brinca com Faísca (2): true
Bruno brinca com Faísca (3): true
Bruno brinca com Faísca (4): false
Pipoca (capivara) - dia 0 | fome 4/10 | energia 7/10 | feliz
Faísca (gato) - dia 0 | fome 10/10 | energia 1/10 | faminto

=== A Carla "adota" um bichinho ===
Carla brinca: true
Carla brinca: true
Bichinho da Carla: Pipoca (capivara) - dia 0 | fome 8/10 | energia 1/10 | faminto
Bichinho da Ana:   Pipoca (capivara) - dia 0 | fome 8/10 | energia 1/10 | faminto
pipoca == bichinhoDaCarla? true

=== A Carla ganha o próprio bichinho ===
Bichinho da Carla: Pipoca (capivara) - dia 0 | fome 5/10 | energia 10/10 | feliz
Bichinho da Ana:   Pipoca (capivara) - dia 0 | fome 8/10 | energia 1/10 | faminto
pipoca == bichinhoDaCarla? false

=== Noite ===
Pipoca (capivara) - dia 1 | fome 9/10 | energia 10/10 | faminto
Faísca (gato) - dia 1 | fome 10/10 | energia 10/10 | faminto
Pipoca (capivara) - dia 0 | fome 5/10 | energia 10/10 | feliz
Sem nome (ovo) - dia 0 | fome 5/10 | energia 10/10 | feliz
```

**Erro ao rodar o bloco B.6**

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "Bichinho.brincar()" because "<local5>" is null
```

> O nome que aparece no lugar de `<local5>` pode variar.

---

## Desafio extra (opcional)

**Hora do recreio.** Acrescente à classe `Bichinho`:

1. O método `boolean brincarCom(Bichinho amigo)`: os dois bichinhos precisam ter energia de pelo menos 3. Se tiverem, **os dois** gastam 3 de energia e ganham 2 de fome, e o método devolve `true`. Senão, ninguém muda e devolve `false`.
2. O método `void chocar(String nome, String especie)`: só funciona se a espécie atual for `"ovo"`. Ele troca o nome e a espécie do bichinho. Se o bichinho já tiver nascido, não faz nada.

No `main`:
- choque o ovo (ele vira o dragão `"Brasa"`) e tente chocá-lo de novo, com outro nome;
- faça a Pipoca da Carla brincar com o Faísca;
- **teste esquisito:** chame `pipoca.brincarCom(pipoca)`. O que acontece com a energia da Pipoca? Ela gastou 3 ou 6? Explique usando o desenho da memória: quantos objetos o `this` e o `amigo` representam nessa chamada?
