# Exercício - Correio Intergaláctico

**Tema da aula:** Interfaces
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Modelar um **contrato** (interface): o que fazer, sem dizer como
- Decidir entre **"é um"** (`extends`) e **"sabe fazer"** (`implements`)
- Fazer uma classe **herdar e implementar várias interfaces** ao mesmo tempo
- Combinar **classe abstrata** com interface
- Usar **constantes**, métodos **`default`** e métodos **`static`** em interfaces
- Ordenar objetos com **`Comparable`** e `Collections.sort`
- Usar a interface como **tipo** para obter **polimorfismo** e "programar para a interface"

---

## Contexto

O **Correio Intergaláctico** entrega encomendas pela galáxia, e a central tem três entregadores que não têm **nada** em comum:

| Entregador | O que ele é | Limite de peso | Frete |
|---|---|---|---|
| **Pipo** | um pombo estelar: uma **criatura**, como outras que existem na galáxia | até 2 kg | R$ 10,00 + R$ 1,50 por ano-luz |
| **Andrômeda**, código NC-7 | uma nave cargueira: um **veículo**, como outros da frota | até 500 kg | R$ 40,00 + R$ 0,10 por kg + R$ 2,00 por ano-luz |
| **Tele-X** | um teletransportador: uma máquina sem parentesco com ninguém | qualquer peso | R$ 120,00 fixo |

Na primeira versão do sistema, a central perguntava o tipo de cada entregador, um por um, e chamava um método com nome diferente para cada tipo. Toda vez que chegava um entregador novo, alguém precisava mexer em todos esses testes.

A sua tarefa é **projetar e escrever** a nova versão, usando interfaces para que a central trate os três entregadores do mesmo jeito.

---

## Regras do negócio

### Encomendas

1. Toda encomenda tem uma **descrição**, um **destino**, um **peso** (em kg) e uma **distância** (em anos-luz).
2. A **ordem natural** das encomendas é a distância: a mais perto vem primeiro. A central precisa ordenar uma lista de encomendas com a ordenação pronta da biblioteca do Java, sem informar nenhum critério na chamada.
3. Ao imprimir uma encomenda, ela aparece assim: `Carta da vó -> Lua Bolacha (0,1 kg, 2 anos-luz)`.

### Entregadores

4. **Todo entregador**, seja qual for, precisa saber:
   - informar o seu nome;
   - dizer se **aceita** uma encomenda;
   - **calcular o frete** de uma encomenda;
   - **entregar** uma encomenda, devolvendo uma frase que descreve a entrega.
5. **Nenhum** entregador aceita encomendas a mais de **100 anos-luz**. Esse limite vale para todos, deve estar escrito **uma única vez** e ninguém pode alterá-lo.
6. A verificação "está dentro do alcance?" também deve ser escrita **uma única vez** e servir para todos os entregadores, sem que cada classe precise reescrevê-la.
7. Todo entregador também sabe fazer uma **entrega urgente**: é a entrega normal, com `[URGENTE] ` na frente da frase. Escreva esse comportamento **uma única vez**: os entregadores atuais e os futuros ganham sem escrever nada.
8. Deve existir um jeito de pedir **ao próprio contrato de entregador** um "entregador de plantão", sem saber qual classe está por trás. O plantão é um pombo estelar chamado `Pombo de plantão`.

### Rastreamento

9. Algumas coisas na galáxia sabem **dizer onde estão**. O pombo e a nave sabem; o teletransportador não, porque ele não fica em lugar nenhum.
10. **Todo veículo** da frota sabe dizer onde está do mesmo jeito: `NC-7: Hangar`. Ele começa no hangar e vai para o destino a cada entrega. Escreva isso uma vez, para todos os veículos, e não só para a nave.
11. O pombo diz onde está do jeito dele: `Pipo: voando perto de Lua Bolacha`. Ele começa no `Pombal` e passa a estar perto do destino da última entrega.

### Os três entregadores

12. **Pombo estelar:** é uma criatura (toda criatura tem nome e espécie; a dele é `pombo estelar`). Aceita só encomendas dentro do alcance e de até 2 kg. Frase da entrega: `Pipo bateu as asas e entregou Carta da vó em Lua Bolacha`.
13. **Nave cargueira:** é um veículo (todo veículo tem um código). Também tem um nome. Aceita só encomendas dentro do alcance e de até 500 kg. Frase: `Nave NC-7 Andrômeda pousou em Estação Órbita-9 com Peças de foguete`.
14. **Teletransportador:** aceita qualquer peso, desde que dentro do alcance. Frase: `Tele-X teletransportou Piano de cauda para Nebulosa Sonata em 0 segundos`.

> Ninguém "é" uma criatura genérica ou um veículo genérico: não deve ser possível criar um objeto que seja só "criatura" ou só "veículo".

---

## Requisitos

### Parte A: modelagem (antes de programar)

A.1. Desenhe o **diagrama UML** do sistema com estes nomes: `Entregador`, `Rastreavel`, `Encomenda`, `Criatura`, `Veiculo`, `PomboEstelar`, `NaveCargueira` e `Teletransportador`. Para cada um, decida se é **interface**, **classe abstrata** ou **classe concreta**, e ligue-os com linhas cheias (`extends`) ou tracejadas (`implements`).

A.2. Para cada entregador, complete as frases "\_\_\_ **é um** \_\_\_" e "\_\_\_ **sabe** \_\_\_". Use as frases para justificar cada ligação do diagrama.

A.3. Para cada uma das regras 5 a 8 e 10, diga **onde** ela vai ficar no código e com que recurso: constante, método `default`, método `static`, classe abstrata etc.

### Parte B: implementação

B.1. Escreva todas as classes e interfaces do seu diagrama, uma por arquivo, seguindo as regras do negócio. Atributos sempre `private`, com getters quando precisar.

B.2. Na classe `Main`, crie as encomendas abaixo, **nesta ordem**. Ordene a lista e imprima.

| Descrição | Destino | Peso (kg) | Anos-luz |
|---|---|---|---|
| Peças de foguete | Estação Órbita-9 | 300 | 20 |
| Carta da vó | Lua Bolacha | 0,1 | 2 |
| Mudança completa | Galáxia Distante | 50 | 150 |
| Pizza gigante | Planeta Calabresa | 1,5 | 8 |
| Piano de cauda | Nebulosa Sonata | 800 | 40 |

B.3. Monte a **frota** numa única lista com os três entregadores.

B.4. **Central de entregas.** Para cada encomenda:
- mostre uma linha com o frete de cada entregador da frota, ou `não leva`;
- escolha o **mais barato** entre os que aceitam e mande-o entregar;
- se ninguém aceitar, avise mostrando o alcance máximo.

**Regra da central:** o código que mostra as ofertas e escolhe o mais barato **não pode citar** `PomboEstelar`, `NaveCargueira` ou `Teletransportador`, nem usar `instanceof`. Ele só conhece o contrato.

B.5. **Entrega urgente.** Peça o entregador de plantão ao contrato (regra 8) e faça uma entrega urgente de um `Remédio` (0,2 kg, 2 anos-luz) para a `Lua Bolacha`.

B.6. **Rastreamento.** Numa lista de coisas rastreáveis, coloque o Pipo, a Andrômeda e o pombo de plantão do B.5. Imprima onde cada um está.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Experimentos no compilador.** Faça cada alteração no **seu** código, compile, anote a mensagem de erro e **desfaça** a alteração:

| # | Alteração |
|---|---|
| a | No `main`, tente criar um objeto diretamente do tipo `Entregador`. |
| b | Apague, no `Teletransportador`, o método que faz a entrega. |
| c | Tire o `public` de um método do `Teletransportador` que cumpre o contrato. |
| d | Dentro do `Teletransportador`, tente mudar o valor do alcance máximo. |
| e | Guarde a nave numa variável cujo tipo é `Entregador` e, por essa variável, peça a localização dela. |
| f | Peça o entregador de plantão chamando o método pela classe `PomboEstelar`, e não pelo contrato. |

Para cada experimento, explique com suas palavras **por que** o compilador recusou.

**2.** No B.6, o que você precisou fazer para colocar o pombo de plantão na lista de rastreáveis? Por quê? O que aconteceria se o plantão passasse a ser um teletransportador?

**3.** Por que `Entregador` precisa ser uma **interface**, e não uma classe abstrata como `Veiculo`?

**4.** Para incluir um novo entregador na frota, quantas linhas da central (B.4) precisam mudar? Por quê?

---

## Saída esperada

```
=== Encomendas por distância ===
Carta da vó -> Lua Bolacha (0,1 kg, 2 anos-luz)
Pizza gigante -> Planeta Calabresa (1,5 kg, 8 anos-luz)
Peças de foguete -> Estação Órbita-9 (300,0 kg, 20 anos-luz)
Piano de cauda -> Nebulosa Sonata (800,0 kg, 40 anos-luz)
Mudança completa -> Galáxia Distante (50,0 kg, 150 anos-luz)

=== Central de entregas ===
Carta da vó: Pipo R$ 13,00 | Andrômeda R$ 44,01 | Tele-X R$ 120,00
  -> Pipo bateu as asas e entregou Carta da vó em Lua Bolacha
Pizza gigante: Pipo R$ 22,00 | Andrômeda R$ 56,15 | Tele-X R$ 120,00
  -> Pipo bateu as asas e entregou Pizza gigante em Planeta Calabresa
Peças de foguete: Pipo não leva | Andrômeda R$ 110,00 | Tele-X R$ 120,00
  -> Nave NC-7 Andrômeda pousou em Estação Órbita-9 com Peças de foguete
Piano de cauda: Pipo não leva | Andrômeda não leva | Tele-X R$ 120,00
  -> Tele-X teletransportou Piano de cauda para Nebulosa Sonata em 0 segundos
Mudança completa: Pipo não leva | Andrômeda não leva | Tele-X não leva
  -> nenhum entregador aceita (alcance máximo: 100 anos-luz)

=== Entrega urgente ===
[URGENTE] Pombo de plantão bateu as asas e entregou Remédio em Lua Bolacha

=== Rastreamento ===
Pipo: voando perto de Planeta Calabresa
NC-7: Estação Órbita-9
Pombo de plantão: voando perto de Lua Bolacha
```

> A vírgula em `R$ 13,00` vem do `printf` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

1. **Um quarto entregador.** O **Buraco de Minhoca** leva qualquer peso a **qualquer** distância (para ele, o alcance máximo não vale) e cobra R$ 300,00 + R$ 1,00 por ano-luz. Inclua-o na frota e confira que a "Mudança completa" agora é entregue. Quantas linhas da central você mudou?
2. **Outras ordens.** A ordem natural das encomendas é a distância. Sem mexer na classe da encomenda, ordene a lista também **por peso**, usando uma lambda, e imprima de novo.
3. **Dois contratos, mesmo método.** Crie um novo contrato, "cobrável", que já vem com uma descrição pronta: `"Serviço cobrado"`. Faça o contrato de entregador também vir com uma descrição pronta: `"Entregador da galáxia"`. Faça o teletransportador cumprir os dois contratos. O que o compilador exige? Resolva.
