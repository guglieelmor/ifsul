# Exercício - A Coleta Seletiva do Câmpus

**Tema da aula:** Enum
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Trocar **números mágicos** e **Strings soltas** por um **enum**, deixando o compilador barrar valores inválidos
- Declarar enums com **atributos**, **construtor** e **métodos**
- Usar o enum num **switch com seta**, sem `default`, para o compilador avisar quando faltar um caso
- Percorrer as constantes com **`values()`** e converter texto em constante com segurança
- Conhecer as armadilhas do **`valueOf()`** (maiúsculas, espaços e valores que não existem) e do **`ordinal()`**
- Comparar constantes com **`==`**
- Representar enums no **diagrama de classes UML** com o estereótipo «enumeration»
- Decidir **quando um dado deve ou não virar enum**

---

## Contexto

O grêmio estudantil lançou a **Campanha da Coleta Seletiva**: as turmas trazem resíduos separados, a portaria pesa cada entrega e a turma ganha pontos. No fim do mês, cada turma recebe um **selo** pelos pontos e a campeã ganha um passeio.

A primeira versão do programa guardava o tipo de resíduo como número (`1` = papel, `2` = plástico...) e, às vezes, como texto. Deu nisso:

- alguém registrou **resíduo 7**, que não existe, e a turma ganhou pontos por ele;
- `"Plastico"`, `"plastico"` e `"PLASTICO "` viraram três resíduos diferentes no relatório;
- a cor de cada lixeira e os pontos por kg ficavam espalhados em `if`s por várias telas. Quando o metal passou a valer mais, uma tela mudou e outra não.

O grêmio pediu à turma de INF2 que reescreva o programa usando **enums**.

---

## Regras do negócio

### Os resíduos

1. A campanha aceita **seis tipos de resíduo**, cada um com a cor da lixeira, os pontos por kg e uma dica de descarte:

| Resíduo | Lixeira | Pontos por kg | Dica de descarte |
|---|---|---|---|
| `PAPEL` | azul | 2 | seco e sem gordura |
| `PLASTICO` | vermelha | 3 | lavado e amassado |
| `VIDRO` | verde | 2 | inteiro ou embrulhado em jornal |
| `METAL` | amarela | 5 | latinhas lavadas |
| `ORGANICO` | marrom | 1 | vai para a composteira da horta |
| `REJEITO` | cinza | 0 | vai para o lixo comum |

2. A cor e os pontos são **dados de cada resíduo** e não mudam durante o programa.
3. A dica de descarte é escolhida com um **switch com seta**, com um caso para cada resíduo e **sem `default`**.
4. O resíduo sabe dizer se é **reciclável**: todos são, menos o orgânico e o rejeito.

### Os selos

5. No fim, cada turma recebe um **selo** conforme os pontos:

| Selo | Pontos mínimos |
|---|---|
| `SEM_SELO` | 0 |
| `BRONZE` | 50 |
| `PRATA` | 100 |
| `OURO` | 200 |

6. A turma fica com o **maior** selo que alcançou. Descobrir o selo a partir dos pontos é tarefa do próprio selo, percorrendo os selos existentes (sem um `if` para cada selo).

### As entregas

7. Cada entrega tem a **turma**, o **resíduo** e o **peso** em kg. Ela vale **peso × pontos por kg** do resíduo.
8. Na portaria, o resíduo é **digitado como texto**. O programa deve aceitar o texto com espaços sobrando e em maiúsculas ou minúsculas (`"Plastico "` vale `PLASTICO`).
9. Se o texto não corresponder a nenhum resíduo, a entrega é recusada com a mensagem `Resíduo desconhecido: "<texto>" (<turma>)`. O programa **não pode parar** por causa disso.
10. Peso zero ou negativo é recusado com a mensagem `Peso inválido: <peso> kg de <RESÍDUO> (<turma>)`.
11. Uma entrega aceita mostra `<turma>: <peso> kg de <RESÍDUO> -> lixeira <cor>`.

### Os relatórios

12. **Guia das lixeiras:** todos os resíduos, com cor, pontos e dica. A lista sai dos próprios resíduos, e não de textos digitados um por um.
13. **Placar das turmas:** pontos e selo de cada turma, e a turma campeã (a de mais pontos).
14. **Total por resíduo:** os kg de cada resíduo e o total reciclável.
15. Constantes de enum são comparadas com **`==`**. Todos os atributos são `private`.

Sugestão de classes: `Residuo` e `Selo` (enums), `Entrega`, `Campanha` e `Main`.

---

## Requisitos

### Parte A: planejamento (antes de programar)

A.1. Desenhe o **diagrama de classes UML** do projeto: os dois enums com o estereótipo «enumeration», as constantes, os atributos e os métodos de cada classe, e as associações (quem usa quem como tipo de atributo).

A.2. Para cada regra de 1 a 14, diga **em qual classe** ela fica e por quê.

A.3. Na portaria vão ser digitados os textos da tabela da Parte B.2. Para cada um, diga se a entrega será aceita e, se for, qual constante ele vira.

### Parte B: implementação

B.1. Escreva as classes seguindo as regras de 1 a 15.

B.2. Na classe principal:

1. Mostre o **guia das lixeiras**.
2. Registre as entregas da semana, nesta ordem, **com o texto exatamente como foi digitado** (repare nos espaços):

| Turma | Resíduo digitado | Peso (kg) |
|---|---|---|
| INF1 | `papel` | 12,0 |
| INF1 | `Plastico ` | 20,0 |
| INF1 | `organico` | 21,0 |
| INF2 | `METAL` | 30,0 |
| INF2 | `vidro` | 30,0 |
| INF2 | `isopor` | 3,0 |
| INF3 | ` plástico` | 6,0 |
| INF3 | `papel` | 18,0 |
| INF3 | `rejeito` | 5,0 |
| INF3 | `metal` | 4,0 |
| INF3 | `vidro` | -2,0 |

3. Mostre o **placar** das turmas INF1, INF2 e INF3, com o selo de cada uma e a campeã.
4. Mostre o **total por resíduo** e o total reciclável.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Experimentos.** Faça cada alteração, compile, rode, anote o que aconteceu e **desfaça**:

| # | Alteração |
|---|---|
| a | Na classe principal, guarde o texto `"PAPEL"` numa variável do tipo do enum de resíduos. |
| b | Na classe principal, tente criar um resíduo novo com `new`, passando a cor `rosa` e 9 pontos. |
| c | No enum de resíduos, acrescente a constante `PILHA` (lixeira laranja, 4 pontos) **sem mexer** no switch da dica. |
| d | Na classe principal, converta o texto `"plastico"` direto com o `valueOf()` do enum e imprima o resultado. |
| e | Imprima o `ordinal()` do `METAL` e o resultado de comparar `PAPEL` com `METAL` usando `compareTo()`. Depois mova `METAL` para o início da lista de constantes e rode de novo. O que mudou na saída? E os pontos? |
| f | No enum dos selos, troque de lugar `BRONZE` e `OURO` na declaração. O que acontece com o placar? |

Para cada um, explique **por que** aconteceu (erro de compilação, erro na execução ou resultado diferente).

**2.** O texto ` plástico` foi limpo e convertido para maiúsculas, mas mesmo assim foi recusado. Por quê?

**3. Enum ou não?** Para cada dado, diga se ele deve virar enum e justifique em uma frase: o tipo de resíduo, a cor da lixeira, o selo, a turma, o peso da entrega, o dia da semana da coleta.

**4. static final.** Se os resíduos fossem constantes `static final int` (`PAPEL = 1`, `PLASTICO = 2`...), o que aconteceria se alguém registrasse o resíduo `7`? O que apareceria ao imprimir o resíduo de uma entrega?

**5.** O orgânico vai passar a contar como reciclável. Quantas classes você precisa mudar? Por quê?

---

## Saída esperada

```
=== Guia das lixeiras ===
PAPEL    | lixeira azul     | 2 pts/kg | seco e sem gordura
PLASTICO | lixeira vermelha | 3 pts/kg | lavado e amassado
VIDRO    | lixeira verde    | 2 pts/kg | inteiro ou embrulhado em jornal
METAL    | lixeira amarela  | 5 pts/kg | latinhas lavadas
ORGANICO | lixeira marrom   | 1 pts/kg | vai para a composteira da horta
REJEITO  | lixeira cinza    | 0 pts/kg | vai para o lixo comum

=== Entregas da semana ===
INF1: 12,0 kg de PAPEL -> lixeira azul
INF1: 20,0 kg de PLASTICO -> lixeira vermelha
INF1: 21,0 kg de ORGANICO -> lixeira marrom
INF2: 30,0 kg de METAL -> lixeira amarela
INF2: 30,0 kg de VIDRO -> lixeira verde
Resíduo desconhecido: "isopor" (INF2)
Resíduo desconhecido: " plástico" (INF3)
INF3: 18,0 kg de PAPEL -> lixeira azul
INF3: 5,0 kg de REJEITO -> lixeira cinza
INF3: 4,0 kg de METAL -> lixeira amarela
Peso inválido: -2,0 kg de VIDRO (INF3)

=== Placar das turmas ===
INF1: 105,0 pontos | selo PRATA
INF2: 210,0 pontos | selo OURO
INF3: 56,0 pontos | selo BRONZE
Turma campeã: INF2

=== Total por resíduo ===
PAPEL     30,0 kg
PLASTICO  20,0 kg
VIDRO     30,0 kg
METAL     34,0 kg
ORGANICO  21,0 kg
REJEITO    5,0 kg
Reciclável: 114,0 kg
```

> O rejeito entra no total de kg, mas vale 0 ponto e não conta como reciclável.
> A vírgula em `12,0` vem do `printf` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

1. **Pilhas.** Acrescente o resíduo `PILHA` (lixeira laranja, 4 pontos, dica "entregar na secretaria, nunca no lixo"). Quantos arquivos você precisou mudar? O guia das lixeiras e o total por resíduo precisaram mudar?
2. **Acentos.** Faça a portaria aceitar `plástico`, `orgânico` e outros textos com acento. Onde fica essa regra?
3. **Falta pouco.** Mostre no placar quantos pontos faltam para o próximo selo (ex.: `INF3: faltam 44,0 pontos para PRATA`). A turma com `OURO` não mostra nada. Dica: o selo pode saber qual é o seguinte.
