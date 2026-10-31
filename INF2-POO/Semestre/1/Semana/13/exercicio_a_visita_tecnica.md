# Exercício - A Visita Técnica

**Tema da aula:** ArrayList
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Usar **`ArrayList`** quando a quantidade de elementos muda durante o uso
- Declarar listas tipadas com **Generics**, inclusive de números com a classe **wrapper** `Integer`
- Usar **`add`**, **`add` numa posição**, **`get`**, **`set`**, **`remove`**, **`size`**, **`isEmpty`** e **`contains`**
- Diferenciar **inserir** (empurra os outros) de **substituir** (troca no lugar)
- Cair (e sair) da armadilha do **`remove` com `Integer`**: índice ou valor?
- Percorrer listas com **for tradicional** e **for-each**, e saber quando usar cada um
- **Buscar** um objeto numa lista de objetos e entender por que o `contains` não acha um objeto "igual"
- **Ordenar** uma lista com `Collections.sort`

---

## Contexto

A coordenação do curso marcou uma **visita técnica ao Polo Tecnológico**. O micro-ônibus tem **5 lugares** para alunos.

As inscrições eram anotadas numa folha com 5 linhas. Na primeira semana:

- o sexto interessado escreveu o nome **na margem** e ninguém soube se ele ia ou não;
- quando o Beto desistiu, riscaram o nome dele e a linha ficou **em branco**: ninguém lembrou de chamar quem estava esperando;
- a Ana se inscreveu **duas vezes** e ocupou duas linhas;
- um aluno que tinha perdido a visita anterior por doença deveria ter **prioridade**, mas foi parar no fim da fila.

A coordenação pediu à turma de INF2 um programa que controle as vagas, a lista de espera e as desistências.

---

## Regras do negócio

### O aluno

1. Todo aluno tem **nome** e **matrícula** (número inteiro). Ao ser impresso, aparece como `Ana (2026101)`.

### A visita

2. A visita tem um **destino**, uma **quantidade de vagas** e três listas que começam vazias:
   - **confirmados**, na ordem dos lugares;
   - **lista de espera**, na ordem de atendimento;
   - **matrículas bloqueadas**: as matrículas (só os números) de quem desistiu.

### Inscrição

3. Ao se inscrever, o aluno ganha uma vaga se ainda houver lugar. Senão, entra **no fim** da lista de espera. A mensagem mostra `<aluno>: vaga confirmada` ou `<aluno>: lista de espera, posição <n>`.
4. Quem perdeu a visita anterior se inscreve **com prioridade**: se não houver vaga, entra **no início** da lista de espera, à frente de todos (`<aluno>: lista de espera com prioridade, posição 1`).
5. Um aluno que já está inscrito (confirmado ou na espera) não entra de novo: `<aluno>: já está inscrito na visita`. A verificação é pela **matrícula**.
6. Um aluno com matrícula bloqueada não pode se inscrever: `<aluno>: desistiu antes e não pode se inscrever de novo`.

### Desistência, troca e liberação

7. Quando um aluno **desiste** (informando a matrícula), a matrícula é bloqueada e:
   - se ele estava na espera, só sai dela: `<aluno> saiu da lista de espera`;
   - se estava confirmado, sai da lista e **o primeiro da espera assume a vaga** (vai para o fim dos confirmados): `<aluno> desistiu. <próximo> assumiu a vaga.`;
   - se estava confirmado e ninguém espera: `<aluno> desistiu. A vaga ficou livre.`;
   - se a matrícula não existe: `Matrícula <número> não encontrada`.
8. Um confirmado pode **passar a vaga** para um colega. O colega fica **no mesmo lugar** da lista, e ninguém é empurrado. A mensagem é `<antigo> passou a vaga para <novo>`. O colega precisa cumprir as regras 5 e 6. Passar a vaga não bloqueia a matrícula de quem passou.
9. A coordenação pode **liberar** uma matrícula bloqueada: `Matrícula <número> liberada para nova inscrição` (ou `não estava bloqueada`).
10. É possível perguntar se um aluno (pela matrícula) está na visita, confirmado ou na espera.

### Relatório

11. O relatório mostra:
    - os confirmados numerados a partir de 1, com a ocupação (ex.: `5/5`);
    - a lista de espera numerada, ou `Lista de espera: vazia`;
    - as matrículas bloqueadas;
    - a **lista de embarque**: só os nomes dos confirmados, em **ordem alfabética**.
12. Nenhuma lista tem tamanho fixo no código, e nenhuma posição fica vazia (`null`) no meio.
13. Todos os atributos são `private`.

Sugestão de classes: `Aluno`, `VisitaTecnica` e `Main`.

---

## Requisitos

### Parte A: no papel (antes de programar)

A.1. Para cada lista da regra 2, diga **o tipo dos elementos** e por que a lista de matrículas bloqueadas não pode ser declarada com `int`.

A.2. Simule à mão a Parte B.2 e escreva **como ficam as três listas** depois de cada linha da tabela de mudanças.

A.3. Para cada regra de 3 a 11, diga qual **método do ArrayList** você pretende usar (pode ser mais de um).

### Parte B: implementação

B.1. Escreva as classes seguindo as regras de 1 a 13.

B.2. Na classe principal, crie a visita `Visita ao Polo Tecnológico` com 5 vagas e faça, nesta ordem:

**Inscrições:**

| # | Ação |
|---|---|
| 1 | inscrever Ana (2026101) |
| 2 | inscrever Beto (2026102) |
| 3 | inscrever Caio (2026103) |
| 4 | inscrever Duda (2026104) |
| 5 | inscrever Eva (2026105) |
| 6 | inscrever Ana (2026101) de novo |
| 7 | inscrever Fabi (2026106) |
| 8 | inscrever Gabi (2026107) |
| 9 | inscrever Hugo (2026108) **com prioridade** |

**Mudanças da semana:**

| # | Ação |
|---|---|
| 1 | a matrícula 2026102 desiste |
| 2 | a matrícula 2026103 passa a vaga para Iris (2026109) |
| 3 | a matrícula 2026107 desiste |
| 4 | inscrever Beto (2026102) |
| 5 | a coordenação libera a matrícula 2026102 |
| 6 | inscrever Beto (2026102) |
| 7 | a matrícula 2026199 desiste |

**Conselho de classe:** pergunte se o Caio (2026103) e a Iris (2026109) estão na visita.

Por fim, mostre o **relatório**.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Experimentos.** Faça cada alteração, compile, rode, anote o que aconteceu e **desfaça**:

| # | Alteração |
|---|---|
| a | Declare a lista de matrículas bloqueadas com `int` no lugar de `Integer`. |
| b | Na liberação, remova a matrícula da lista passando o `int` da matrícula direto para o `remove`. |
| c | No laço que mostra os confirmados no relatório, troque o `<` por `<=`. |
| d | Na desistência, tire o aluno dos confirmados com um **for-each** na própria lista, removendo dentro do laço. |
| e | Apague a criação (`new`) da lista de espera, deixando só a declaração do atributo. |
| f | Pergunte à lista de confirmados, com `contains`, se ela tem um `new Aluno("Ana", 2026101)`. |
| g | Na lista de embarque, tente ordenar com `Collections.sort` a lista de **alunos**, em vez da lista de nomes. |

Para cada um, explique **por que** aconteceu (erro de compilação, exceção na execução ou resultado inesperado).

**2. Inserir × substituir.** Na entrada do Hugo com prioridade e na troca do Caio pela Iris, o que aconteceu com os outros elementos da lista e com o `size()` em cada caso?

**3. Capacidade.** A coordenação vai alugar um ônibus de 44 lugares. Começando com a capacidade padrão do ArrayList, quantas vezes o array interno é copiado até caberem os 44 inscritos? Qual fica sendo o `size()` e qual a capacidade?

**4. Array ou ArrayList?** Marque e justifique em uma frase: as poltronas numeradas do ônibus de 44 lugares; os inscritos na visita; a lista de espera; os 3 horários de parada da viagem; as matrículas bloqueadas.

**5.** Explique o que daria trabalho se a turma tivesse usado um **array de 5 posições** para os confirmados: na desistência do Beto, na subida do Hugo e na prioridade da lista de espera.

---

## Saída esperada

```
=== Inscrições ===
Ana (2026101): vaga confirmada
Beto (2026102): vaga confirmada
Caio (2026103): vaga confirmada
Duda (2026104): vaga confirmada
Eva (2026105): vaga confirmada
Ana (2026101): já está inscrito na visita
Fabi (2026106): lista de espera, posição 1
Gabi (2026107): lista de espera, posição 2
Hugo (2026108): lista de espera com prioridade, posição 1

=== Mudanças da semana ===
Beto (2026102) desistiu. Hugo (2026108) assumiu a vaga.
Caio (2026103) passou a vaga para Iris (2026109)
Gabi (2026107) saiu da lista de espera
Beto (2026102): desistiu antes e não pode se inscrever de novo
Matrícula 2026102 liberada para nova inscrição
Beto (2026102): lista de espera, posição 2
Matrícula 2026199 não encontrada

=== Conselho de classe ===
Caio vai? false
Iris vai? true

=== Visita ao Polo Tecnológico ===
Confirmados (5/5):
  1. Ana (2026101)
  2. Iris (2026109)
  3. Duda (2026104)
  4. Eva (2026105)
  5. Hugo (2026108)
Lista de espera (2):
  1. Fabi (2026106)
  2. Beto (2026102)
Matrículas bloqueadas: [2026107]
Lista de embarque: [Ana, Duda, Eva, Hugo, Iris]
```

> Repare que a Iris ficou no 2º lugar, onde estava o Caio, e o Hugo, que subiu da espera, foi para o fim dos confirmados.
> As matrículas bloqueadas aparecem entre colchetes porque o próprio ArrayList sabe se imprimir.

---

## Desafio extra (opcional)

1. **Posição na fila.** Crie a consulta "em que posição da lista de espera está a matrícula X?", respondendo `não está na espera` quando for o caso. Que valor o `indexOf` devolveria se você usasse ele direto com um `new Aluno(...)`? Por quê?
2. **Visita cancelada para uma turma.** Os alunos da INF3 não vão mais. Acrescente a turma ao aluno e remova da visita (confirmados e espera) todos os da INF3, chamando a espera para ocupar as vagas. Cuidado com o experimento **d**.
3. **Mais um ônibus.** Se a coordenação aumentar as vagas de 5 para 7 com a visita em andamento, os primeiros da lista de espera devem subir automaticamente. Implemente e mostre o relatório de novo.
