# Exercício - Missão Rover

**Tema da aula:** Atributos e Métodos
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Declarar atributos de vários tipos (`String`, `double`, `int`, `char`, `boolean`) e usar os **3 jeitos de inicializar**: valor padrão, na declaração e no construtor
- Escrever os **4 tipos de método**: `void` ou com retorno, com ou sem parâmetros
- Diferenciar **parâmetro** de **argumento** e **variável local** de **atributo**
- Proteger o estado com `private`, **getters** e **setters com validação**
- Decidir **o que deixar público**: nem todo atributo ganha setter
- Usar `this` para separar o atributo do parâmetro
- Rastrear o estado de um objeto com um **teste de mesa**

---

## Contexto

O clube de robótica do câmpus ganhou um simulador de Marte e pousou **dois rovers** na cratera de testes:

- **RV-01 Curiosa**, que vai explorar e coletar amostras;
- **RV-02 Teimoso**, que insiste em andar mais do que a bateria permite.

Cada rover sabe onde está (coordenadas `x` e `y`, começando na base em `(0, 0)`), para onde está virado (`N`, `L`, `S` ou `O`) e quanto de bateria ainda tem. A bateria é a vida do rover: se acabar longe da base, ele fica para sempre em Marte.

Você é a equipe do **controle da missão**. Sua tarefa é escrever a classe `Rover` e enviar os comandos pelo `main`.

> **Regra de ouro da missão:** ninguém da Terra mexe direto na bateria. Todo comando passa pelos métodos do rover, que conferem se ele aguenta.

---

## Requisitos

### Parte A: a classe `Rover` (`Rover.java`)

Organize a classe na ordem recomendada: **atributos → construtor → getters e setters → métodos de negócio**.

A.1. **Atributos**, todos `private`:

| Atributo | Tipo | Como inicializa |
|---|---|---|
| `codigo` | `String` | no construtor |
| `nome` | `String` | no construtor |
| `bateria` | `double` (percentual, de 0 a 100) | na declaração: `100.0` |
| `direcao` | `char` (`'N'`, `'L'`, `'S'` ou `'O'`) | na declaração: `'N'` |
| `x`, `y` | `int` | valor padrão (não atribua nada) |
| `amostras` | `int` | valor padrão |
| `modoEconomia` | `boolean` | valor padrão |

Crie também a constante `private final double GASTO_POR_METRO = 2.0;` (a bateria gasta 2% por metro).

A.2. **Construtor** `Rover(String codigo, String nome)`, usando `this`.

A.3. **Getters e setters.** Siga o checklist do slide "O que deixar público?":

| Atributo | get? | set? |
|---|---|---|
| `codigo` | `getCodigo()` | não (identifica o rover) |
| `nome` | `getNome()` | `setNome(String nome)`: recusa `null` ou texto vazio e imprime `Nome inválido: o rover continua <nome atual>` |
| `bateria` | `getBateria()` | **não**: só muda pelos métodos de negócio |
| `amostras` | `getAmostras()` | **não**: só aumenta em `coletarAmostra()` |
| `modoEconomia` | `isModoEconomia()` | `setModoEconomia(boolean modoEconomia)` |

A.4. **Métodos de negócio:**

| Método | Tipo | O que faz |
|---|---|---|
| `void girarDireita()` | sem parâmetros, `void` | gira 90° no sentido horário: N → L → S → O → N |
| `double calcularGasto(int metros)` | com parâmetros e retorno | devolve `metros × GASTO_POR_METRO`. No modo economia, o gasto por metro cai pela **metade**. Use uma **variável local** `gastoPorMetro` |
| `void andar(int metros)` | com parâmetros, `void` | se `metros` for menor ou igual a zero, ou se o gasto for maior que a bateria, imprime `<nome>: bateria insuficiente para andar <metros> m` e não se move. Senão, desconta o gasto e anda na direção atual (N: `y` aumenta; S: `y` diminui; L: `x` aumenta; O: `x` diminui). **Reaproveite** `calcularGasto()` |
| `boolean coletarAmostra()` | sem parâmetros, com retorno | gasta 5% de bateria e soma 1 amostra. Recusa (devolve `false`) se a bateria for menor que 5 |
| `void carregarPainelSolar(int horas)` | com parâmetros, `void` | cada hora de sol carrega 10%, sem passar de 100. Ignora horas menores ou iguais a zero |
| `double calcularAutonomia()` | sem parâmetros, com retorno | quantos metros o rover ainda consegue andar: `bateria ÷ gasto de 1 metro` |
| `int calcularDistanciaBase()` | sem parâmetros, com retorno | `|x| + |y|` (use `Math.abs`) |
| `boolean podeVoltarParaBase()` | sem parâmetros, com retorno | `true` se o gasto para andar a distância até a base couber na bateria |
| `String obterStatus()` | sem parâmetros, com retorno | devolve `RV-01 Curiosa \| (5, 10) virado para L \| bateria 65,0% \| amostras 1 \| economia: não` |

> **Dica para o `obterStatus()`:** `String.format` funciona como o `printf`, mas **devolve** o texto em vez de imprimir. Para mostrar o símbolo `%`, escreva `%%`.

### Parte B: o controle da missão (`Main.java`)

**B.1. Teste de mesa (antes de programar o `main`).** Copie a tabela no caderno e preencha **à mão** o estado da Curiosa depois de cada linha. Só depois rode o programa e confira.

```java
1  Rover curiosa = new Rover("RV-01", "Curiosa");
2  curiosa.andar(10);
3  curiosa.girarDireita();
4  curiosa.andar(5);
5  curiosa.coletarAmostra();
6  double autonomia = curiosa.calcularAutonomia();
```

| Linha | bateria | direcao | x | y | amostras | autonomia |
|---|---|---|---|---|---|---|
| 1 | 100.0 | N | 0 | 0 | 0 | — |
| 2 | | | | | | — |
| 3 | | | | | | — |
| 4 | | | | | | — |
| 5 | | | | | | — |
| 6 | | | | | | |

**B.2. Pouso.** Crie a Curiosa e o Teimoso e mostre o status dos dois.

**B.3. Missão da Curiosa.** Execute as linhas 2 a 5 do teste de mesa, imprimindo o retorno de `coletarAmostra()`. Depois mostre:
- o status;
- a autonomia, com 1 casa decimal;
- a distância até a base;
- se ela pode voltar para a base.

**B.4. Missão do Teimoso.**
1. Tente renomeá-lo para `""` (texto vazio) e depois para `"Teimoso II"`.
2. Mande ele andar 60 m (ele vai recusar). Mostre quanto custariam esses 60 m.
3. Ligue o modo economia, mostre o novo custo dos 60 m e mande andar de novo.
4. Mostre o status e se ele pode voltar para a base.
5. Carregue o painel solar por 3 horas e mostre a bateria. Depois, por mais 5 horas, e mostre de novo.

**B.5. Relatório final.** Mostre o status dos dois rovers.

**B.6. Sabotagem.** Escreva `teimoso.bateria = 500;` e compile. Anote o erro e depois comente a linha.

### Parte C: responda como comentário no final do `Main.java`

**1.** Copie a tabela do teste de mesa (B.1) preenchida.

**2. Tipos de método.** Classifique cada método da classe `Rover` em: sem parâmetros/`void`, sem parâmetros/com retorno, com parâmetros/`void` ou com parâmetros/com retorno.

**3.** Por que `bateria`, `amostras` e `codigo` **não** têm setter? O que poderia dar errado com um `setBateria(500)`?

**4. Escopo.** No método `calcularGasto(int metros)`, quais são as **variáveis locais/parâmetros** e quais são os **atributos**? Onde cada um vive na memória e por quanto tempo? Por que `x` e `y` valem `0` sem você atribuir nada, enquanto uma variável local sem valor não compila?

**5.** O que aconteceria com o `setNome` se você escrevesse `nome = nome;` em vez de `this.nome = nome;`? Qual seria o nome do Teimoso no final?

**6. Encontre os erros.** A equipe de comunicação escreveu a classe `Antena` do rover. Ela tem **pelo menos 6 problemas**: alguns impedem a compilação; outros compilam, mas quebram o encapsulamento ou a convenção. Para cada um, diga a linha, o problema e a correção.

```java
1  public class Antena {
2      private String Canal;
3      public int sinal;
4      private boolean ligada;
5
6      public Antena(String canal) {
7          canal = canal;
8      }
9
10     public void setSinal(int s) {   // sinal vai de 0 a 100
11         sinal = s;
12     }
13
14     public double medirQualidade() {
15         int ruido;
16         double qualidade = sinal - ruido;
17     }
18
19     public boolean ligada() {
20         return ligada;
21     }
22 }
```

---

## Saída esperada

```
=== Pouso ===
RV-01 Curiosa | (0, 0) virado para N | bateria 100,0% | amostras 0 | economia: não
RV-02 Teimoso | (0, 0) virado para N | bateria 100,0% | amostras 0 | economia: não

=== Missão da Curiosa ===
Coletou amostra? true
RV-01 Curiosa | (5, 10) virado para L | bateria 65,0% | amostras 1 | economia: não
Autonomia: 32,5 m
Distância até a base: 15 m
Pode voltar para a base? true

=== Missão do Teimoso ===
Nome inválido: o rover continua Teimoso
Teimoso II: bateria insuficiente para andar 60 m
Gasto para 60 m: 120,0%
Gasto para 60 m em economia: 60,0%
RV-02 Teimoso II | (0, 60) virado para N | bateria 40,0% | amostras 0 | economia: sim
Pode voltar para a base? false
Depois de 3 h de sol: 70,0%
Depois de mais 5 h de sol: 100,0%

=== Relatório final ===
RV-01 Curiosa | (5, 10) virado para L | bateria 65,0% | amostras 1 | economia: não
RV-02 Teimoso II | (0, 60) virado para N | bateria 100,0% | amostras 0 | economia: sim
```

**Erro ao compilar `teimoso.bateria = 500;`**

```
error: bateria has private access in Rover
```

> A vírgula em `65,0` vem do `printf`/`String.format` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

**Resgate do Teimoso.** No fim do B.4, o Teimoso estava a 60 m da base com 40% de bateria e `podeVoltarParaBase()` dava `false`.

1. Escreva o método `void girarEsquerda()` (N → O → S → L → N).
2. Escreva o método `boolean voltarParaBase()`. Ele só funciona se `podeVoltarParaBase()` for `true`. Nesse caso, o rover gasta a bateria da distância, volta para `(0, 0)`, fica virado para `N` e o método devolve `true`.
3. Crie o atributo `int comandos`, que conta quantos comandos de movimento (`andar`, `girarDireita`, `girarEsquerda`) o rover **aceitou**. Ele tem getter, mas não tem setter. Por quê?

No `main`, logo depois do B.4, descubra **por teste de mesa** quantas horas de painel solar o Teimoso precisa para conseguir voltar. Depois carregue exatamente essas horas, chame `voltarParaBase()` e mostre o status. Com quanto de bateria ele chega? E se o modo economia estivesse desligado, quantas horas seriam necessárias?
