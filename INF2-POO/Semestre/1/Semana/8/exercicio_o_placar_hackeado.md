# Exercício - O Placar Hackeado

**Tema da aula:** Encapsulamento
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Explicar o que é **encapsulamento**: esconder o estado e expor comportamentos
- Organizar as classes em **pacotes** e usar **`import`**
- Escolher o **modificador de acesso** de cada membro: `public`, `protected`, default (sem palavra) ou `private`
- Criar **getters**, **setters com validação** e um **construtor** que exige os dados obrigatórios
- Decidir quando um atributo **não deve ter setter**
- Usar um **método `private`** como detalhe interno da classe

---

## Contexto

Na final do **Interclasses** do câmpus, INF2 contra INF4, o telão do ginásio mostrou de repente:

> **INF2 99 x 0 INF4**

O jogo ainda estava 1 x 1. Depois se descobriu que o programa do placar guardava os gols em atributos **públicos**, e alguém do INF2 escreveu uma linha no próprio computador mudando o placar. Na mesma noite apareceu na súmula um gol do **TSI1**, um time que nem estava jogando, e trocaram o árbitro no meio do segundo tempo.

A organização pediu à turma um sistema **à prova de fraude**: o placar só muda de acordo com as regras do jogo, e a súmula oficial só pode ser escrita pela própria partida.

---

## Regras do negócio

### A partida

1. Toda partida tem **time da casa**, **time visitante**, os **gols de cada um**, a **situação** (`agendada`, `em andamento` ou `encerrada`) e um **árbitro**.
2. Uma partida **só pode ser criada informando os dois times**. Ela nasce `agendada`, com 0 x 0 e sem árbitro.
3. Se os dois times tiverem o mesmo nome, o visitante passa a se chamar `A definir`, e o sistema avisa: `Times iguais: visitante fica "A definir"`.
4. Os nomes dos times **nunca mudam** depois que a partida é criada.
5. O **árbitro** pode ser definido ou trocado, mas **só enquanto a partida estiver agendada** e nunca com um nome vazio. Se a troca for recusada, o sistema avisa quem continua: `Árbitro recusado: Profa. Marta continua` (ou `ninguém`, se ainda não houver árbitro).
6. A partida **só começa** se estiver agendada **e** já tiver árbitro. O resultado informa se ela começou.
7. **Os gols não podem ser alterados diretamente por ninguém.** Eles só mudam por três ações, e cada uma informa se deu certo:
   - **marcar gol** de um time: só com a partida em andamento e só para um dos dois times que estão jogando;
   - **anular o último gol** (VAR): só com a partida em andamento e só se houver um gol ainda não anulado. Não dá para anular duas vezes seguidas;
   - **encerrar** a partida: só se estiver em andamento.
8. A verificação "este time está jogando?" é um **detalhe interno** da partida: ninguém de fora precisa dela.
9. Qualquer um pode **consultar**: o placar no formato `INF2 1 x 2 INF4`, a situação, os gols, os times, o árbitro e o **vencedor**. O vencedor é o nome do time, `empate` ou `jogo não terminou`.

### A súmula

10. Toda partida tem a sua **súmula oficial**. Cada linha é numerada, como na saída esperada.
11. A súmula registra o início do jogo (com o árbitro), cada gol, cada anulação do VAR e o fim do jogo, sempre com o placar atualizado.
12. **Qualquer um pode ler** a súmula, mas **só as classes do pacote `modelo`** podem criar uma súmula ou escrever nela. O programa principal, que fica em outro pacote, não consegue anotar nada.

### Organização do projeto

13. O projeto fica em pacotes que seguem o domínio do câmpus ao contrário:
    - `br.edu.ifsul.interclasses.modelo`: as classes `Partida` e `Sumula`;
    - `br.edu.ifsul.interclasses.principal`: a classe `Main`.
14. Cada pacote é uma pasta dentro de `src`, e cada arquivo declara o pacote em que está.

---

## Requisitos

### Parte A: planejamento (antes de programar)

A.1. Faça uma tabela com **todos os atributos e métodos** que você vai criar em `Partida` e `Sumula`. Para cada um, escolha o modificador (`public`, `protected`, default ou `private`) e justifique em uma frase.

A.2. Para cada atributo da `Partida`, responda: **tem getter? tem setter?** Se não tiver setter, diga **como** o atributo muda (ou se nunca muda).

A.3. Desenhe a árvore de pastas do projeto, de `src` até cada arquivo `.java`.

### Parte B: implementação

B.1. Escreva as classes `Partida` e `Sumula` no pacote `modelo`, seguindo as regras de 1 a 12.

B.2. Escreva a classe `Main` no pacote `principal`. Ela usa a `Partida` por meio de um `import`. Faça tudo na ordem abaixo, como na saída esperada:

**Final do Interclasses**
1. Crie a final, INF2 (casa) contra INF4 (visitante), e mostre o placar com a situação.
2. Tente marcar um gol do INF2 antes de o jogo começar.
3. Tente começar o jogo sem árbitro.
4. Tente definir um árbitro com nome vazio. Depois defina a `Profa. Marta` e comece o jogo.

**O jogo**
5. Marque, nesta ordem: gol do INF2, gol do INF4, gol do INF2 e um gol do TSI1.
6. Tente trocar o árbitro para `Prof. Jonas`.
7. Chame o VAR duas vezes seguidas.
8. Marque um gol do INF4 e encerre o jogo.
9. Tente marcar um gol do INF2 depois do apito final.
10. Mostre o placar com a situação e o vencedor.

**Súmula oficial**
11. Mostre a súmula.

**Amistoso**
12. Crie uma partida INF4 contra INF4, mostre o placar e o vencedor.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Tentativas de fraude.** Faça cada alteração, compile, anote a mensagem de erro e **desfaça**:

| # | Alteração |
|---|---|
| a | No `Main`, tente mudar os gols do time da casa para 99 diretamente. |
| b | No `Main`, tente usar a verificação "este time está jogando?" da partida. |
| c | No `Main`, importe a `Sumula` e tente criar uma súmula. |
| d | Apague o `import` da `Partida` no `Main`. |
| e | Tire o `public` da classe `Partida`. |
| f | Tire o `public` do método que devolve o placar. |

Para cada um, explique qual regra de acesso impediu.

**2.** No experimento **c**, a classe `Sumula` é pública, mas mesmo assim o `Main` não conseguiu criar uma súmula. Por quê? E por que a `Partida` consegue?

**3.** Por que os gols **não têm setter**? Se existisse um setter que aceitasse só valores de 0 a 30, a fraude do telão estaria resolvida? Explique.

**4.** Que modificador você deu ao método que **anota** na súmula? O que aconteceria se ele fosse `public`? E se fosse `private`?

**5. Pensando no futuro.** A organização quer uma **partida eliminatória**, que vai **herdar** da `Partida` e ficar em outro pacote, `br.edu.ifsul.interclasses.mata`. Ela vai precisar mexer nos gols para registrar os pênaltis. Que modificador permitiria isso? Que outras classes também ganhariam acesso? Por que isso é um risco?

---

## Saída esperada

```
=== Final do Interclasses ===
INF2 0 x 0 INF4 (agendada)
Gol do INF2 antes do apito: false
Começar sem árbitro: false
Árbitro recusado: ninguém continua
Começar com árbitra: true

=== O jogo ===
Gol do INF2: true
Gol do INF4: true
Gol do INF2: true
Gol do TSI1: false
Árbitro recusado: Profa. Marta continua
VAR: true
VAR de novo: false
Gol do INF4: true
Encerrar: true
Gol do INF2 depois do apito: false
INF2 1 x 2 INF4 (encerrada)
Vencedor: INF4

=== Súmula oficial ===
1. Início de jogo, árbitro(a) Profa. Marta
2. Gol do INF2 (INF2 1 x 0 INF4)
3. Gol do INF4 (INF2 1 x 1 INF4)
4. Gol do INF2 (INF2 2 x 1 INF4)
5. VAR anulou o gol do INF2 (INF2 1 x 1 INF4)
6. Gol do INF4 (INF2 1 x 2 INF4)
7. Fim de jogo (INF2 1 x 2 INF4)

=== Amistoso ===
Times iguais: visitante fica "A definir"
INF4 0 x 0 A definir (agendada)
Vencedor do treino: jogo não terminou
```

> **Como compilar com pacotes:** de dentro da pasta `src`, compile os arquivos dos dois pacotes juntos, informando o caminho das pastas. Para rodar, use o nome completo da classe principal (pacote + classe). Na IDE, basta rodar a classe `Main`.

---

## Desafio extra (opcional)

1. **Cartões.** A súmula também registra cartões amarelos e vermelhos, com o nome do jogador e o time: `Cartão amarelo: Bia (INF4)`. Só vale com a partida em andamento e para um dos dois times. Em quais classes você mexeu? Algum modificador mudou?
2. **Telão.** Crie, no pacote `principal`, uma classe `Telao` que mostra o placar em letras grandes: `INF2  1  x  2  INF4`. O que o telão **consegue** e o que **não consegue** fazer com a partida? Por quê?
3. **Prorrogação.** Se a final terminar empatada, ela pode ir para a prorrogação: volta a ficar em andamento uma única vez. Escreva essa regra sem criar nenhum setter.
