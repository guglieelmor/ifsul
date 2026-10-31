# Exercício - A Playlist da Rádio IF

**Tema da aula:** Sobrecarga
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Criar **várias versões de um mesmo método** (sobrecarga) para a mesma ação com dados diferentes
- Criar **construtores sobrecarregados** que repassam os dados ao construtor completo com `this(...)`
- Saber o que faz parte da **assinatura** (nome, quantidade, tipo e ordem dos parâmetros) e o que não faz (retorno, modificador, nome das variáveis)
- Prever **qual versão o Java escolhe**: tipo exato, ampliação, autoboxing e, por último, varargs
- Reconhecer uma **chamada ambígua** e saber como resolver
- Deixar a regra num lugar só: **a versão simples chama a completa**
- Lembrar a pegadinha do **operador `+`** com números e textos

---

## Contexto

A **Rádio IF** toca música nas caixas de som do pátio durante o intervalo do almoço, que dura **20 minutos**. Cada locutor monta a sua playlist antes do programa.

O programa que monta as playlists foi escrito por três pessoas, e cada uma criou o seu jeito de pôr uma música na lista: `adicionarMusicaSimples`, `adicionarMusicaComArtista`, `adicionarMusicaMinSeg`... Deu nisso:

- ninguém lembrava qual dos sete nomes usar, e o autocompletar mostrava todos;
- só uma das versões conferia o limite de 20 minutos. Na quinta-feira o intervalo acabou no meio de uma música e o sinal tocou por cima do refrão;
- uma música entrou com duração de **2 minutos e 75 segundos**.

A coordenação da rádio pediu à turma de INF2 que reescreva a classe da playlist com **um único nome** para a ação de adicionar música e com a regra do limite **escrita uma vez só**.

---

## Regras do negócio

### A música

1. Toda música tem **título**, **artista** e **duração** (guardada em segundos).

### A playlist

2. Toda playlist tem **nome**, **locutor**, **limite de tempo** (informado em minutos) e a lista de músicas.
3. Uma playlist pode ser criada de **quatro jeitos**:

| O locutor informa | O que a playlist recebe |
|---|---|
| nada | nome `Playlist sem nome`, locutor `Rádio IF`, limite de 20 minutos |
| o nome | locutor `Rádio IF`, limite de 20 minutos |
| o nome e o locutor | limite de 20 minutos |
| o nome, o locutor e o limite em minutos | tudo o que foi informado |

4. As atribuições ficam **só no jeito completo**. Os outros jeitos repassam os dados para ele.

### Adicionar música

5. A ação de adicionar música tem **um único nome** e aceita **cinco formas** de chamada:

| O locutor informa | O que a música recebe |
|---|---|
| só o título | artista `Desconhecido`, duração de 3:00 |
| título e artista | duração de 3:00 |
| título, artista e duração em segundos | tudo o que foi informado |
| título, artista, minutos e segundos | a duração é convertida para segundos |
| vários títulos de uma vez (quantos quiser) | cada um entra como "só o título" |

6. A forma **completa** é a de título, artista e duração em segundos. Só ela confere as regras e guarda a música. As outras **repassam** para ela (direta ou indiretamente).
7. Uma música com duração **zero ou negativa** é recusada com a mensagem `Duração inválida: <título>`.
8. Na forma de minutos e segundos, os segundos vão de **0 a 59** e os minutos não podem ser negativos. Senão, a música é recusada com a mesma mensagem.
9. Uma música que faria a playlist **passar do limite** é recusada com a mensagem `Não cabe: <título> (<duração>)`. As seguintes ainda são testadas (uma mais curta pode caber).
10. A playlist informa o **tempo total**, o **tempo que sobra**, a **quantidade de músicas** e um **resumo** de uma linha (nome, locutor e limite).
11. Toda duração aparece no formato **minutos:segundos**, com os segundos sempre em dois dígitos (ex.: `4:05`, `20:00`).
12. Todos os atributos são `private`.

Sugestão de classes: `Musica`, `Playlist` e `Main`.

---

## Requisitos

### Parte A: planejamento (antes de programar)

A.1. Escreva a **assinatura** (nome do método e tipos dos parâmetros, na ordem) de cada construtor e de cada forma de adicionar música. Confira se **nenhuma** delas tem a mesma assinatura de outra.

A.2. Desenhe com setas **quem chama quem**: qual construtor repassa para qual, e qual forma de adicionar repassa para qual, até chegar na completa.

A.3. Para cada chamada da Parte B.2, diga **qual forma** de adicionar o Java vai escolher e em que passo da ordem de escolha ela foi encontrada (tipo exato, ampliação, autoboxing ou varargs).

### Parte B: implementação

B.1. Escreva as classes seguindo as regras de 1 a 12.

B.2. Na classe principal:

1. Crie uma playlist de **cada um dos quatro jeitos** e mostre o resumo de cada uma:
   - sem informar nada;
   - `Sexta Musical`;
   - `Intervalo do Almoço`, locutora `Bia`;
   - `Especial de Formatura`, locutor `Caio`, 45 minutos.
2. Na playlist `Intervalo do Almoço`, adicione nesta ordem, **uma chamada por linha**:

| Chamada | O que o locutor informou |
|---|---|
| 1 | só o título `Abertura do Intervalo` |
| 2 | `Pôr do Sol no Jacuí`, de `Banda Charqueada` |
| 3 | `Código Sem Bug`, de `Os Compiladores`, 245 segundos |
| 4 | `Recreio Eterno`, de `Trio Sineta`, 3 minutos e 50 segundos |
| 5 | `Saideira`, de `Os Compiladores`, 2 minutos e 75 segundos |
| 6 | de uma vez só: `Chimarrão`, `Mate Amargo` e `Vento Minuano` |

3. Mostre a playlist `Intervalo do Almoço` numerada, com o tempo total e o que sobra.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Experimentos.** Faça cada alteração, compile, anote o que aconteceu e **desfaça**:

| # | Alteração |
|---|---|
| a | Na playlist, crie outra forma de adicionar com os **mesmos parâmetros** da forma completa, mas que devolve `boolean` em vez de não devolver nada. |
| b | Na playlist, crie uma cópia da forma "título e artista" mudando **só os nomes** dos dois parâmetros. |
| c | No construtor que recebe só o nome, ponha um `println` **antes** da linha que repassa para o outro construtor. |
| d | Na classe principal, passe a duração do `Código Sem Bug` como `245L` (um `long`). |
| e | Na classe principal, crie um método estático `testar` com duas versões: uma recebe um `int` e um `double`, a outra recebe um `double` e um `int`. Chame `testar(5, 10)`. |
| f | Na playlist, apague a forma que recebe **só o título**. Compila? A saída mudou? |
| g | Na classe principal, crie um método estático `volume` com duas versões: uma recebe `long`, a outra recebe `Integer`. Cada uma imprime qual foi chamada. Chame `volume(10)`. |

Para cada um, explique **por que** o compilador recusou (ou por que aceitou).

**2. A pegadinha dos dois títulos.** O locutor quis adicionar de uma vez só `Chimarrão` e `Mate Amargo`, só esses dois. O que entra na playlist? Por quê? Que mudança no projeto evitaria esse engano?

**3. O `+`.** Na hora de mostrar a quantidade, alguém escreveu o texto `Músicas: ` seguido de `+ 2 + 3`. O que aparece? E com `2 + 3` antes do texto ` músicas`? Como mostrar `Músicas: 5` sem calcular antes?

**4. Sobrecarga × sobrescrita.** A rádio quer uma `PlaylistAoVivo`, filha de `Playlist`, que calcula o tempo total somando 10 segundos de fala do locutor entre as músicas. Isso é sobrecarga ou sobrescrita? E as cinco formas de adicionar música? Quem decide qual versão roda em cada caso: o compilador ou a JVM?

**5. Uma regra, um lugar.** A coordenação decidiu que nenhuma música pode passar de 6 minutos. Quantos métodos você precisa mudar? Quantos seriam se cada forma de adicionar conferisse as regras sozinha?

---

## Saída esperada

```
=== Programas da semana ===
Playlist sem nome | locutor: Rádio IF | limite: 20:00
Sexta Musical | locutor: Rádio IF | limite: 20:00
Intervalo do Almoço | locutor: Bia | limite: 20:00
Especial de Formatura | locutor: Caio | limite: 45:00

=== Montando o intervalo ===
Duração inválida: Saideira
Não cabe: Vento Minuano (3:00)

=== Intervalo do Almoço (Bia) ===
 1. Abertura do Intervalo  Desconhecido         3:00
 2. Pôr do Sol no Jacuí    Banda Charqueada     3:00
 3. Código Sem Bug         Os Compiladores      4:05
 4. Recreio Eterno         Trio Sineta          3:50
 5. Chimarrão              Desconhecido         3:00
 6. Mate Amargo            Desconhecido         3:00
Músicas: 6
Tempo total: 19:55 de 20:00
Sobra: 0:05
```

> Repare que o `Vento Minuano` foi recusado: com ele a playlist passaria de 20 minutos (19:55 + 3:00).
> O alinhamento das colunas é só um capricho: o que importa é a informação de cada linha.

---

## Desafio extra (opcional)

1. **Duração como texto.** Crie mais uma forma de adicionar que recebe título, artista e a duração escrita como texto (ex.: `"3:45"`). Depois disso, o que acontece com uma chamada de adicionar que passa **três títulos**? Ela ainda vai para a forma de vários títulos? Por quê?
2. **Remover.** Crie a ação de remover música de dois jeitos: pela **posição** na playlist (começando em 1) ou pelo **título**. As duas devem usar o mesmo nome. A remoção pela posição pode reaproveitar a outra, ou o contrário?
3. **Ampliação na prática.** Crie uma forma de adicionar que recebe a duração em segundos como `double` (ex.: `212.7`, arredondando para o inteiro mais próximo). Agora, quando você passa `245`, qual versão é escolhida? E se você apagar a versão com `int`?
