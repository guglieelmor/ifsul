# Exercício - A Reprografia do Câmpus

**Tema da aula:** Boas Práticas (código limpo)
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Reconhecer **código "sujo"** e explicar por que ele atrapalha quem precisa mudar o sistema
- Nomear **classes** (substantivos em PascalCase), **métodos** (verbos em camelCase), **variáveis**, **booleanos** (`esta…`, `tem…`, `eh…`) e **constantes** (UPPER_SNAKE_CASE)
- Trocar **números mágicos** por **constantes** com nome, separando valores iguais que significam coisas diferentes
- Organizar cada classe na **estrutura padrão**: atributos, construtores, métodos de negócio, getters e setters
- **Formatar** o código: recuo de 4 espaços, chaves e espaços em volta dos operadores
- Dividir uma **"classe Deus"** em classes com **um só motivo para mudar** (SRP)
- Escrever **métodos pequenos**, que fazem uma coisa só e recebem no máximo 3 parâmetros
- Escrever **comentários que explicam o porquê** (e Javadoc nos métodos públicos), e reescrever o código quando o comentário só explica o quê

---

## Contexto

A reprografia do câmpus imprime apostilas, provas e trabalhos dos alunos. O programa do balcão foi escrito às pressas por um bolsista que já se formou. Ele funciona, mas ninguém tem coragem de mexer nele.

Veja o que a turma encontrou ao abrir o projeto:

- Existe **uma classe só**, chamada `rep_dados`, com um método `processar()` de mais de 80 linhas. Ele calcula o preço, confere o papel, recebe o pagamento, escreve o recibo e avisa quando o papel está acabando.
- Os nomes não dizem nada:

| Nome no programa antigo | O que ele guarda ou faz |
|---|---|
| `rep_dados` | a classe que faz tudo |
| `processar()` | atende um aluno do começo ao fim |
| `c()` | calcula o valor de um trabalho |
| `validarEImprimir()` | confere o trabalho e manda imprimir |
| `t` | o total a pagar |
| `q` | a quantidade de páginas |
| `flag` | diz se a impressão é colorida |
| `fv` | diz se a impressão é frente e verso |
| `est` | as folhas que sobraram no estoque |
| `vlr` | o dinheiro que o aluno entregou |

- O número `0.05` aparece **duas vezes**: uma é o desconto por volume e a outra é a taxa da maquininha de cartão. No mês passado a coordenação aumentou o desconto para **10%**. Alguém trocou "todos os `0.05`" e a taxa do cartão também virou 10%. O caixa só fechou errado no fim do mês.
- O número `100` também aparece em dois sentidos: a quantidade de páginas para ganhar desconto e o estoque mínimo de papel.
- O desconto deveria valer "a partir de 100 páginas", mas o programa testa **mais que 100**: quem imprime exatamente 100 páginas fica sem desconto.
- Há comentários como "calcula o total" em cima do método `c()` e "t é o total" ao lado do atributo `t`. Há também um trecho antigo **comentado** com a taxa de cartão de 3% de 2023, que ninguém sabe se ainda serve.
- O código não tem recuo: vários `if` e `else` estão escritos numa linha só.

A coordenação pediu à turma de INF2 que **reescreva o programa do zero**, aplicando as boas práticas da aula. O resultado tem de ser o mesmo do programa antigo (com o desconto corrigido), mas um colega deve entender cada classe em 30 segundos.

---

## Regras do negócio

### O trabalho de impressão

1. Cada trabalho tem o **aluno**, a **quantidade de páginas**, a **cor** (preto e branco ou colorida) e se é **frente e verso**.
2. O preço por página é **R$ 0,25** no preto e branco e **R$ 1,00** na colorida.
3. Trabalhos **a partir de 100 páginas** (inclusive) ganham **10% de desconto**.
4. Cada folha gasta tem 1 página. Em frente e verso, cada folha tem 2 páginas e uma página sobrando ainda gasta uma folha inteira (99 páginas em frente e verso gastam 50 folhas).
5. A reprografia não aceita trabalhos com **mais de 300 páginas**.
6. Frente e verso é a exceção: um trabalho nasce como "só frente" e pode ser marcado como frente e verso.

### O papel

7. O dia começa com **uma resma de 500 folhas**.
8. Se não houver folhas suficientes, o trabalho é recusado e o estoque não muda.
9. Depois de cada impressão, se restarem **menos de 100 folhas**, o programa avisa que o estoque está baixo.

### O pagamento

10. O aluno paga em **dinheiro** ou no **cartão**.
11. Em dinheiro, o valor entregue tem de cobrir o total. Se não cobrir, o pagamento é recusado e **nada é impresso** (papel gasto não volta para o pacote). Se cobrir, o programa calcula o troco.
12. No cartão, o aluno paga o total, mas a maquininha fica com **5%** do valor. Essa taxa **não tem relação** com o desconto por volume.

### A ordem do atendimento

13. O programa confere, nesta ordem: o limite de páginas, o papel e o pagamento. A primeira regra que falhar recusa o trabalho com a mensagem dela.

### As mensagens

14. Trabalho atendido: `<aluno>: <páginas> págs <P&B ou coloridas>[ frente e verso] | <folhas> folhas | R$ <valor>[ (desconto 10%)] | <dinheiro, troco R$ <troco> ou cartão>`.
15. Recusas e avisos:
    - `Trabalho recusado: <páginas> páginas passa do limite de 300 (<aluno>)`
    - `Sem papel: o trabalho precisa de <folhas> folhas e restam <folhas> (<aluno>)`
    - `Pagamento recusado: R$ <entregue> não cobre R$ <total> (<aluno>)`
    - `Aviso: estoque baixo, restam <folhas> folhas`
16. Os percentuais que aparecem nas mensagens (10% e 5%) **saem das constantes**, e não de textos digitados à parte.
17. No fim do dia, o **fechamento do caixa** mostra: trabalhos atendidos, total cobrado, total de taxas do cartão, valor líquido (cobrado menos taxas), folhas usadas e folhas restantes.

### As boas práticas (valem para todas as classes)

18. Nenhum número mágico dentro dos métodos: todo valor de regra vira **constante** com nome que diz o que ela é.
19. Cada classe tem **um só motivo para mudar**. Nenhum nome de método com "E" (como `validarEImprimir`).
20. Métodos com até ~20 linhas e **no máximo 3 parâmetros**. Nada de chamadas cheias de `true` e `false` soltos que só quem escreveu entende.
21. Cada classe segue a **estrutura padrão** (atributos, construtores, métodos de negócio, getters e setters), com atributos `private`.
22. Comentários só onde explicam **o porquê** de uma regra. Métodos públicos que calculam algo importante têm **Javadoc**. Nada de código comentado.
23. Código formatado com o atalho da IDE.

Sugestão de classes: `TrabalhoDeImpressao`, `EstoqueDePapel`, `Caixa`, `ImpressoraDeRecibo` (tudo o que aparece na tela), `Reprografia` (coordena o atendimento) e `Main`. Se precisar, use um enum para a cor.

---

## Requisitos

### Parte A: análise do código antigo (antes de programar)

A.1. Para cada nome da tabela do contexto, escreva um **nome melhor** seguindo as convenções do Java e diga qual convenção o nome antigo quebrava.

A.2. Liste os **motivos para mudar** que o método `processar()` tem hoje. Para cada motivo, diga **em qual classe nova** ele vai ficar.

A.3. Liste os **números mágicos** do programa antigo (preços, desconto, taxa, limites, estoque) e dê um nome de constante a cada um. Explique por que os dois `0.05` e os dois `100` **não** podem virar uma constante só.

A.4. Para cada comentário do programa antigo (o "calcula o total", o "t é o total" e o trecho comentado da taxa de 2023), decida: **manter**, **apagar** ou **reescrever o código**. Justifique.

A.5. Desenhe o **diagrama de classes UML** do projeto novo, com atributos, constantes, métodos e associações.

### Parte B: implementação

B.1. Escreva as classes seguindo as regras de 1 a 23.

B.2. Na classe principal, comece o dia com uma resma e atenda os trabalhos abaixo, **nesta ordem**:

| Aluno | Páginas | Cor | Frente e verso | Pagamento |
|---|---|---|---|---|
| Ana | 40 | preto e branco | sim | dinheiro, entrega R$ 20,00 |
| Bruno | 100 | preto e branco | não | cartão |
| Carla | 12 | colorida | não | dinheiro, entrega R$ 10,00 |
| Diego | 350 | preto e branco | não | cartão |
| Elisa | 150 | colorida | sim | cartão |
| Felipe | 220 | preto e branco | não | dinheiro, entrega R$ 60,00 |
| Gabi | 99 | preto e branco | não | cartão |
| Gabi | 99 | preto e branco | sim | cartão |

B.3. No fim, mostre o **fechamento do caixa**.

B.4. Use nomes de variáveis que digam o que é cada trabalho (a apostila da Ana, as provas do Bruno...), e não `t1`, `t2`, `t3`.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Experimentos.** Faça cada alteração, compile, rode, anote o que aconteceu e **desfaça**:

| # | Alteração |
|---|---|
| a | A coordenação aumentou o desconto para 15%. Faça essa mudança. Quantas linhas você mudou? Quais recibos mudaram? A taxa do cartão mudou? |
| b | Na regra do desconto, troque "a partir de 100" por "mais que 100". Qual recibo muda? |
| c | Mova os getters de uma classe para antes dos atributos. O programa ainda compila? |
| d | Escreva a classe do estoque inteira numa linha só, sem recuo. Compila? Rode o atalho de formatação da IDE e veja o que acontece. |
| e | Renomeie o método que calcula o valor do trabalho para `c`. O que você precisou mudar além da própria classe? |

Para cada um, explique **por que** aconteceu.

**2. SRP.** Para cada classe que você criou, escreva em uma frase **o único motivo** que a faria mudar.

**3.** Por que um nome como `validarEImprimir` é sinal de alerta? Como ele ficou no seu código?

**4. Comentários.** Quais comentários sobraram no seu código? Para cada um, aplique o teste: se você apagar o comentário, alguém perde informação?

**5. Parâmetros.** Se o trabalho recebesse aluno, páginas, colorido e frente e verso, tudo no construtor, que regra da aula seria quebrada? Como uma chamada com dois booleanos soltos ficaria difícil de ler? Como você resolveu?

---

## Saída esperada

```
=== Atendimentos do dia ===
Ana: 40 págs P&B frente e verso | 20 folhas | R$ 10,00 | dinheiro, troco R$ 10,00
Bruno: 100 págs P&B | 100 folhas | R$ 22,50 (desconto 10%) | cartão
Pagamento recusado: R$ 10,00 não cobre R$ 12,00 (Carla)
Trabalho recusado: 350 páginas passa do limite de 300 (Diego)
Elisa: 150 págs coloridas frente e verso | 75 folhas | R$ 135,00 (desconto 10%) | cartão
Felipe: 220 págs P&B | 220 folhas | R$ 49,50 (desconto 10%) | dinheiro, troco R$ 10,50
Aviso: estoque baixo, restam 85 folhas
Sem papel: o trabalho precisa de 99 folhas e restam 85 (Gabi)
Gabi: 99 págs P&B frente e verso | 50 folhas | R$ 24,75 | cartão
Aviso: estoque baixo, restam 35 folhas

=== Fechamento do caixa ===
Trabalhos atendidos: 5
Total cobrado: R$ 241,75
Taxa do cartão (5%): R$ 9,11
Valor líquido: R$ 232,64
Folhas usadas: 465 | restantes: 35
```

> O Bruno imprimiu **exatamente** 100 páginas e ganhou o desconto: é a regra "a partir de 100" corrigida.
> A vírgula em `10,00` vem do `printf` em um computador configurado em português. Em outra configuração pode aparecer ponto.

---

## Desafio extra (opcional)

1. **Teste dos 30 segundos.** Troque uma das suas classes com um colega. Cronometre: em 30 segundos ele consegue dizer o que cada método faz? Anote os nomes que ele não entendeu e renomeie.
2. **Pix.** A reprografia passou a aceitar Pix, sem taxa e sem troco. Quantas classes você precisou mudar? Alguma classe que não tem nada a ver com pagamento precisou mudar? Se sim, o SRP foi respeitado?
3. **Desconto do professor.** Professores ganham 20% de desconto em qualquer quantidade de páginas, e o desconto não se soma ao de volume (vale o maior). Onde fica essa regra? Que constantes novas surgem?
