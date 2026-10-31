# Exercício - A Guilda de Aventureiros

**Tema da aula:** Interfaces vs Classes Abstratas
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Separar **identidade** ("é um") de **capacidade** ("é capaz de")
- Usar **classe abstrata** para compartilhar estado, construtor e código entre tipos parecidos
- Usar **interface** para dar uma capacidade só a alguns tipos, inclusive de **hierarquias diferentes**
- Combinar os dois: **uma** superclasse e **várias** interfaces na mesma classe
- Resolver o conflito de **dois métodos `default` com o mesmo nome**
- Usar **constante**, **`default`** e **`static`** em interfaces
- Trocar `if` de tipo por **polimorfismo**: pela classe abstrata e pelo contrato

---

## Contexto

A **Guilda dos Aventureiros** do vale está informatizando os seus registros. A primeira versão do sistema foi feita às pressas: uma classe só, `Coisa`, com 40 atributos e um campo `tipo`. Aventureiros, poções, espadas e mapas eram todos "coisas", e cada método tinha um `if` perguntando o tipo. Um mapa do tesouro carregava pontos de vida, sempre zerados. E quando chegou o primeiro paladino, a cura dos magos parou de funcionar.

Agora vocês vão refazer o sistema separando o que cada coisa **é** do que ela **sabe fazer**.

---

## Regras do negócio

### Aventureiros

1. Todo aventureiro tem **nome**, **nível** e **vida**. Ninguém é um "aventureiro genérico": todo aventureiro é de uma classe específica.
2. O nível vai de **1 a 20**. Se alguém tentar criar um aventureiro com nível fora disso, ele começa no **nível 1**. Essa validação é escrita **uma única vez**, para todos.
3. A **vida máxima** é 50 + 10 × nível. O aventureiro nasce com a vida máxima.
4. Todo aventureiro, do mesmo jeito: **recebe dano** (a vida nunca fica abaixo de 0), **recebe cura** (nunca passa da vida máxima), **descansa** (volta à vida máxima) e mostra o seu **status** assim: `Tora (Guerreiro, nível 5) - vida 100/100`.
5. Cada classe de aventureiro tem o **seu jeito de golpear**:

| Classe | Dano do golpe |
|---|---|
| Guerreiro | 8 + 2 × nível |
| Maga | 3 × nível |
| Ladino | 5 + nível |
| Bardo | igual ao nível |

### Itens

6. Todo item tem **nome**, **peso** (em kg; se vier zero ou negativo, fica 0,1 kg) e um **efeito**. Ninguém carrega um "item genérico". Ao ser descrito, um item aparece assim: `Espada Rúnica (3,5 kg): +6 de dano`.

| Item | Peso | Efeito |
|---|---|---|
| Poção | 0,3 kg | `recupera 20 de vida` |
| Espada | 3,5 kg | `+6 de dano` |
| Mapa do tesouro | 0,1 kg | `leva à <destino>` (cada mapa leva a um lugar) |

### Capacidades

7. **Curar.** Só a **maga** e o **bardo** sabem curar. O poder de cura da maga é 5 × nível; o do bardo, 3 × nível. **Nenhuma** cura passa de **30** de vida: esse limite vale para todos os curandeiros, é escrito uma vez só e ninguém pode alterá-lo. O jeito de aplicar a cura (respeitar o limite, curar o alvo e dizer quanto curou) também é escrito uma vez só. Todo curandeiro se apresenta assim: `cura até 20 de vida`.
8. **Ser negociado.** Na loja da guilda há coisas com preço em moedas: a **poção** (12 moedas), a **espada** (80 moedas), o **ladino**, que pode ser contratado por 20 × nível, e o **bardo**, contratado por 15 × nível. O **mapa do tesouro não está à venda**.
   - A guilda cobra **10% de taxa** sobre o valor (arredondando para baixo). Essa regra é escrita uma vez só.
   - Tudo que é negociável se apresenta assim: `custa 13 moedas na guilda`.
   - Deve existir um jeito de perguntar **ao próprio contrato de negociação** se uma coisa cabe num orçamento, sem criar objeto nenhum para isso.
9. Tudo que cura ou é negociável precisa saber dizer o **seu nome**, para aparecer nas listas.
10. O **bardo** cura **e** é negociável, e as duas capacidades têm uma apresentação. Ele se apresenta juntando as duas, separadas por ` | `: `cura até 6 de vida | custa 33 moedas na guilda`.

---

## Requisitos

### Parte A: modelagem (antes de programar)

A.1. Desenhe o **diagrama UML** com estes nomes: `Aventureiro`, `Guerreiro`, `Maga`, `Ladino`, `Bardo`, `Item`, `Pocao`, `Espada`, `MapaDoTesouro`, `Curador` e `Negociavel`. Para cada um, decida se é **classe abstrata**, **classe concreta** ou **interface**. Ligue-os com linhas cheias (é um) ou tracejadas (é capaz de).

A.2. Para cada regra de 1 a 10, diga **onde** ela vai morar e com que recurso: atributo, construtor ou método (concreto ou abstrato) da classe abstrata, constante, método `default` ou `static` da interface, sobrescrita na classe etc.

A.3. Use o **teste da frase** para justificar três ligações do diagrama: uma com "é um", uma com "é capaz de" e uma que você decidiu **não** fazer (por exemplo: "o mapa é negociável?").

### Parte B: implementação

B.1. Escreva todas as classes e interfaces do seu diagrama, uma por arquivo. Atributos sempre `private`.

B.2. **A guilda.** Crie os aventureiros abaixo, guarde-os **numa única lista** e mostre o status de todos:

| Nome | Classe | Nível |
|---|---|---|
| Tora | Guerreiro | 5 |
| Lyra | Maga | 4 |
| Sombra | Ladino | 3 |
| Dedé | Bardo | 2 |
| Novato | Guerreiro | 25 (inválido) |

B.3. **O Dragão das Cinzas** (120 de vida).
- Num **turno de ataque**, cada aventureiro da lista golpeia. Mostre o golpe de cada um, o dano total e a vida que sobrou ao dragão (nunca abaixo de 0).
- Depois, o dragão cospe fogo e causa **35 de dano em todos**. Mostre a guilda de novo.

B.4. **Curandeiros.** Monte uma lista só com os curandeiros. Cada um cura o Novato, mostrando a apresentação e quanto curou. Depois mostre o status do Novato.

B.5. **Mercado da guilda** (orçamento de 70 moedas). Monte **uma única lista** com uma poção, uma espada, o Sombra e o Dedé. Para cada um, mostre o nome, a apresentação e se cabe no orçamento.

B.6. **Mochila.** Monte uma lista de itens com uma poção, uma espada e um mapa para a `Caverna Esquecida`. Descreva cada item e mostre o peso total.

B.7. **Chegou a paladina.** A guilda recebeu **Íris**, uma **paladina** de nível 3: ela é guerreira (golpeia como guerreiro), mas também cura, com poder 4 × nível. No status, a classe dela aparece como `Paladino`.
- Inclua a Íris na guilda e na lista de curandeiros, e mostre o status dela.
- Faça mais um turno de ataque contra o dragão.
- Todos os curandeiros curam o Novato de novo. Mostre o status dele.

**Regra:** o código do turno de ataque e o da cura **não podem mudar nenhuma linha** para a paladina funcionar.

B.8. **Descanso na taverna.** Todos descansam. Mostre a guilda.

**Regra geral:** em nenhum ponto do programa você pode perguntar o tipo de um objeto (nada de `instanceof` nem de campo `tipo`). Quem sabe golpear, curar ou se apresentar é cada objeto.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Experimentos no compilador.** Faça cada alteração no seu código, compile, anote a mensagem de erro e **desfaça**:

| # | Alteração |
|---|---|
| a | No `main`, tente criar um objeto diretamente do tipo `Aventureiro`. |
| b | Coloque um mapa do tesouro na lista do mercado. |
| c | Apague do bardo o método que junta as duas apresentações. |
| d | Guarde o Sombra numa variável do tipo `Negociavel` e, por ela, peça o golpe dele. |
| e | Faça o ladino ser, ao mesmo tempo, um aventureiro e um item. |
| f | Acrescente ao contrato de curador um atributo para contar quantas curas cada curandeiro já fez. |

Para cada um, explique **por que** o compilador recusou.

**2. Abstrata, interface ou os dois?** Classifique e justifique com "é um" ou "é capaz de":

| # | Situação |
|---|---|
| 01 | Monstros da masmorra: todos têm vida e atacam, cada espécie do seu jeito. |
| 02 | Baús, portas e prisioneiros que podem ser destrancados com uma chave. |
| 03 | Montarias (cavalo, grifo e lobo) têm velocidade e fôlego; só o grifo voa. |
| 04 | Feitiços, poções e pergaminhos que podem ser usados durante a batalha. |
| 05 | Construções da vila (ferraria, taverna e torre) têm nível e custo de manutenção. |
| 06 | Aventureiros, entre os quais só elfos e anões enxergam no escuro. |

**3.** Por que "curar" não virou um método na classe de todos os aventureiros? E por que seria errado dar ao ladino um método de cura que não faz nada?

**4.** Por que a paladina não foi feita como filha da maga, já que ela também cura? Quantas classes você criou e quantas alterou para ela entrar?

**5.** O mercado mistura poções, espadas e aventureiros. Por que isso não poderia ser resolvido com uma classe abstrata "negociável"?

**6.** O mercado e os curandeiros mostram o nome de cada um. Como você resolveu isso **sem perguntar o tipo do objeto**? Você precisou escrever algo novo nas classes concretas para isso? Por quê?

---

## Saída esperada

```
=== A guilda ===
Tora (Guerreiro, nível 5) - vida 100/100
Lyra (Maga, nível 4) - vida 90/90
Sombra (Ladino, nível 3) - vida 80/80
Dedé (Bardo, nível 2) - vida 70/70
Novato (Guerreiro, nível 1) - vida 60/60

=== Dragão das Cinzas (120 de vida) ===
Tora golpeia: 18 de dano
Lyra golpeia: 12 de dano
Sombra golpeia: 8 de dano
Dedé golpeia: 2 de dano
Novato golpeia: 10 de dano
Dano total: 50 | vida do dragão: 70
O dragão cospe fogo: 35 de dano em todos!
Tora (Guerreiro, nível 5) - vida 65/100
Lyra (Maga, nível 4) - vida 55/90
Sombra (Ladino, nível 3) - vida 45/80
Dedé (Bardo, nível 2) - vida 35/70
Novato (Guerreiro, nível 1) - vida 25/60

=== Curandeiros ===
Lyra (cura até 20 de vida) cura Novato: +20
Dedé (cura até 6 de vida | custa 33 moedas na guilda) cura Novato: +6
Novato (Guerreiro, nível 1) - vida 51/60

=== Mercado da guilda (orçamento: 70 moedas) ===
Poção de vida: custa 13 moedas na guilda | cabe no orçamento? true
Espada Rúnica: custa 88 moedas na guilda | cabe no orçamento? false
Sombra: custa 66 moedas na guilda | cabe no orçamento? true
Dedé: cura até 6 de vida | custa 33 moedas na guilda | cabe no orçamento? true

=== Mochila ===
Poção de vida (0,3 kg): recupera 20 de vida
Espada Rúnica (3,5 kg): +6 de dano
Mapa do tesouro (0,1 kg): leva à Caverna Esquecida
Peso total: 3,9 kg

=== Chegou a paladina ===
Íris (Paladino, nível 3) - vida 80/80
Tora golpeia: 18 de dano
Lyra golpeia: 12 de dano
Sombra golpeia: 8 de dano
Dedé golpeia: 2 de dano
Novato golpeia: 10 de dano
Íris golpeia: 14 de dano
Dano total: 64 | vida do dragão: 6
Lyra (cura até 20 de vida) cura Novato: +20
Dedé (cura até 6 de vida | custa 33 moedas na guilda) cura Novato: +6
Íris (cura até 12 de vida) cura Novato: +12
Novato (Guerreiro, nível 1) - vida 60/60

=== Descanso na taverna ===
Tora (Guerreiro, nível 5) - vida 100/100
Lyra (Maga, nível 4) - vida 90/90
Sombra (Ladino, nível 3) - vida 80/80
Dedé (Bardo, nível 2) - vida 70/70
Novato (Guerreiro, nível 1) - vida 60/60
Íris (Paladino, nível 3) - vida 80/80
```

> A vírgula em `3,9 kg` vem do `printf`/`String.format` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

1. **A clériga.** Chegou uma clériga: é uma aventureira que golpeia com 2 × nível, cura com 6 × nível e pode ser contratada por 25 × nível. Inclua uma clériga de nível 6 na guilda, nos curandeiros e no mercado. Anote quantas classes você criou e quantas alterou. A cura dela respeitou o limite de 30 sem você escrever nada? Por quê?
2. **O dragão vencido.** O dragão ficou com 6 de vida. Faça mais um turno até ele cair. Depois coloque no mercado a **escama do dragão**: um item de 2 kg, que dá `+10 de defesa` e vale 300 moedas.
3. **Mercado em ordem.** Sem mexer em nenhuma classe, ordene a lista do mercado do **mais barato para o mais caro** (preço na guilda), usando uma lambda, e mostre de novo.
