# Exercício - Pizzaria Forno a Lenha

**Tema da aula:** Instanciação de Objetos
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Diferenciar **classe**, **objeto** e **referência**
- Explicar os **4 passos do `new`**: aloca, zera, constrói e devolve o endereço
- Reconhecer os **valores padrão** dos atributos
- Escrever **construtores** com parâmetros usando `this`
- Criar **vários construtores** na mesma classe (sobrecarga) sem repetir a regra de montagem
- Perceber o perigo de **argumentos na ordem errada**
- **Contar** quantos objetos um programa cria e quais ficam para o **Garbage Collector**

---

## Contexto

A **Pizzaria Forno a Lenha**, que fica em frente ao câmpus, anotava os pedidos em comandas de papel. Na sexta-feira passada deu tudo errado:

- uma comanda dizia só "pizza", sem sabor nem tamanho, e o pizzaiolo inventou;
- o Bruno recebeu a pizza com **duas fatias a menos** (o motoboy jura que não comeu nada);
- o forno preparou uma pizza de sabor **"Edu"** para um cliente chamado **"Calabresa"**.

O dono pediu à turma de INF2 um programa em que **toda pizza já nasce completa**, criada a partir de um único molde: a classe `Pizza`.

---

## Regras do negócio

1. Toda pizza tem **cliente**, **sabor**, **tamanho** (`P`, `M` ou `G`), **quantidade de fatias** e se tem **borda recheada** ou não.
2. As fatias dependem do tamanho: **P = 4**, **M = 6** e **G = 8**.
3. Se o tamanho informado não for `P`, `M` ou `G`, a pizza sai **média**.
4. O preço é **R$ 30,00** (P), **R$ 42,00** (M) ou **R$ 55,00** (G), mais **R$ 8,00** se tiver borda recheada.
5. A pizzaria aceita pedidos de **quatro jeitos**, e todos precisam gerar uma pizza completa:

| O cliente informa | O que a pizza recebe |
|---|---|
| nada (pedido de balcão) | cliente `Balcão`, sabor `Margherita`, tamanho médio, sem borda |
| cliente e sabor | tamanho médio, sem borda |
| cliente, sabor e tamanho | sem borda |
| cliente, sabor, tamanho e borda | tudo o que foi informado |

6. A regra de montar a pizza (validar o tamanho e calcular as fatias) fica escrita **uma única vez**. Os outros jeitos de pedir **reaproveitam** esse, em vez de repetir o código.
7. Uma pizza pode ter uma fatia comida: a quantidade diminui 1, mas nunca fica abaixo de zero. A pizza também informa se a fatia foi mesmo comida (não dá para comer de uma pizza vazia).
8. Toda pizza sabe se descrever assim: `Portuguesa P com borda para Carla | 4 fatias | R$ 38,00`. Sem borda, o trecho ` com borda` não aparece.
9. Todos os atributos são `private`.

---

## Requisitos

### Parte A: a classe `Pizza`

Escreva a classe `Pizza` seguindo as regras de 1 a 9. Você decide os nomes dos atributos e dos métodos, mas cada jeito de pedir da regra 5 precisa ser um **construtor**.

### Parte B: a noite de sexta (`Main.java`)

Faça tudo na ordem abaixo, imprimindo os títulos de cada bloco como na saída esperada.

**B.1. Abertura do forno.** Crie e descreva estas cinco pizzas, cada uma usando o construtor que combina com o pedido:

| Pizza | Pedido |
|---|---|
| 1 | pedido de balcão (sem informar nada) |
| 2 | Ana, Calabresa |
| 3 | Bruno, Quatro Queijos, grande |
| 4 | Carla, Portuguesa, pequena, com borda |
| 5 | Dani, Frango, tamanho `X` (inválido) |

**B.2. O pedido trocado.** O Edu pediu uma Calabresa, mas o atendente digitou o **sabor primeiro e o cliente depois**. Reproduza o erro e descreva a pizza que saiu do forno.

**B.3. Duas margheritas.** Crie duas pizzas de balcão.
- Mostre se elas são **o mesmo objeto**.
- Coma 2 fatias da primeira e mostre as fatias das duas.

**B.4. O motoboy esfomeado.** O motoboy recebeu a pizza do Bruno numa **variável só dele**, sem pizza nova nenhuma.
- Pela variável do motoboy, coma 2 fatias.
- Mostre se a variável do motoboy e a do Bruno apontam para **o mesmo objeto**.
- Descreva a pizza que chegou ao Bruno.

**B.5. A Ana mudou de ideia.** A Ana trocou a Calabresa por uma Margherita. Faça a **mesma variável** da pizza dela receber uma pizza nova e descreva-a.

**B.6. Rodízio.** Com um laço, crie e descreva três pizzas pequenas para o cliente `Rodízio`, uma de cada sabor: Atum, Milho e Chocolate. Use uma variável **declarada dentro do laço**.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Experimentos.** Faça cada alteração, compile (e rode, se compilar), anote o que aconteceu e **desfaça**:

| # | Alteração |
|---|---|
| a | No construtor que monta a pizza, comente as linhas que guardam o sabor, a borda e as fatias. Rode e veja as descrições do B.1. |
| b | No `main`, tente criar uma pizza informando **só o tamanho**. |
| c | Coloque `void` antes do construtor que monta a pizza. |
| d | Declare uma variável de pizza **sem `new`** e peça a descrição dela. |
| e | Declare uma variável de pizza valendo `null` e peça a descrição dela. |

No experimento **a**, explique de onde vieram os valores que apareceram. No **c**, explique por que os **outros** construtores também pararam de compilar. No **d** e no **e**, explique por que um erro aparece ao compilar e o outro só ao rodar.

**2. Os 4 passos do `new`.** Descreva o que acontece, em ordem, quando a pizza da Carla (B.1) é criada. Depois faça o mesmo para a pizza de balcão: quais construtores rodam e em que ordem?

**3. O pedido trocado.** Por que o compilador **não reclamou** no B.2? O que você faria para o atendente não errar de novo?

**4. Duas margheritas × motoboy.** No B.3, por que comer fatias de uma margherita não afetou a outra? No B.4, por que o Bruno recebeu menos fatias? Desenhe a **stack** e o **heap** logo depois de o motoboy receber a pizza.

**5. Quantos objetos?** Ao final do `main`:
- quantos objetos `Pizza` foram criados ao todo?
- quantos ainda têm alguma variável apontando para eles?
- quais ficaram **sem referência** e podem ser removidos pelo Garbage Collector? Explique o caso da B.5 e o do B.6.

---

## Saída esperada

```
=== Abertura do forno ===
Margherita M para Balcão | 6 fatias | R$ 42,00
Calabresa M para Ana | 6 fatias | R$ 42,00
Quatro Queijos G para Bruno | 8 fatias | R$ 55,00
Portuguesa P com borda para Carla | 4 fatias | R$ 38,00
Frango M para Dani | 6 fatias | R$ 42,00

=== O pedido trocado ===
Edu M para Calabresa | 6 fatias | R$ 42,00

=== Duas margheritas ===
m1 == m2? false
Depois de comer 2 fatias da m1:
m1: 4 fatias | m2: 6 fatias

=== O motoboy esfomeado ===
p3 == daEntrega? true
Pizza que chegou ao Bruno: Quatro Queijos G para Bruno | 6 fatias | R$ 55,00

=== A Ana mudou de ideia ===
Margherita M para Ana | 6 fatias | R$ 42,00

=== Rodízio ===
Atum P para Rodízio | 4 fatias | R$ 30,00
Milho P para Rodízio | 4 fatias | R$ 30,00
Chocolate P para Rodízio | 4 fatias | R$ 30,00
```

> Os nomes `m1`, `m2`, `p3` e `daEntrega` na saída são os nomes das variáveis do gabarito. Use os nomes que você escolheu.
> A vírgula em `R$ 42,00` vem do `printf`/`String.format` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

1. **Repetir o pedido.** O Dani gostou tanto da pizza da Carla que pediu "uma igual". Crie um **quinto construtor**, que recebe **uma pizza já existente** e o nome do novo cliente e monta uma pizza nova com o mesmo sabor, tamanho e borda. Mostre que:
   - as duas pizzas **não são** o mesmo objeto;
   - comer uma fatia de uma não muda a outra.

   Compare com o que aconteceu no B.4.
2. **Sabor em branco.** Se o sabor vier vazio, a pizza sai `Margherita`. Em quantos construtores você precisou mexer? Por quê?
3. **Meio a meio.** Crie um jeito de pedir uma pizza meio a meio (dois sabores, sempre grande). Na descrição, ela aparece como `Calabresa/Frango G para ...`. Em que construtor essa regra deve morar para não ser repetida?
