# Exercício - A Estação Meteorológica

**Tema da aula:** Estrutura de Pacotes
**Disciplina:** INF2 – Programação Orientada a Objetos

---

## Objetivos

Ao resolver este exercício você vai:

- Organizar um projeto em **pacotes** seguindo o padrão **MVC** (model, view e controller)
- Dar **nomes de pacote** pela convenção Java: domínio invertido e só minúsculas
- Usar **`import` específico** e **`import` com curinga**, sabendo o que o curinga não traz
- Resolver um **conflito de nomes** entre duas classes com o mesmo nome em pacotes diferentes
- Usar o pacote como **fronteira de acesso**: uma classe sem `public` só é vista no próprio pacote
- Manter **alta coesão** e **baixo acoplamento**: a tela só lê os dados, nunca altera

---

## Contexto

O **clube de ciências** instalou uma estação meteorológica no telhado do bloco B. A cada três horas ela mede a **temperatura**, a **umidade do ar** e a **chuva**, e um programa em Java mostra tudo no painel da entrada.

O programa foi escrito às pressas por quatro pessoas, com todas as classes soltas na pasta `src`. Deu nisso:

- ninguém achava a classe que desenha o painel;
- a Bia criou `Alerta.java` para guardar os **dados** de um alerta. O Caio criou **outro** `Alerta.java` para a **tela** que pisca no painel. Um arquivo apagou o outro, e o alerta de geada sumiu numa madrugada de 2 °C;
- a tela do painel mudava a temperatura direto, para "arredondar" os valores.

A sua tarefa é **reorganizar o projeto em pacotes**. As duas classes `Alerta` devem continuar existindo, **com esse nome**.

---

## Regras do negócio

### O que o sistema faz

1. Cada **leitura** tem a **hora** (ex.: `06h00`), a **temperatura** em °C, a **umidade** em % e a **chuva** em mm.
2. A estação **registra** as leituras do dia, na ordem em que chegam.
3. A cada leitura registrada, o sistema confere as **regras de alerta**, nesta ordem de gravidade. A leitura gera **no máximo um** alerta: o primeiro que se aplicar.

| Condição | Nível | Mensagem |
|---|---|---|
| chuva de 30 mm ou mais | `VERMELHO` | `Temporal` |
| temperatura de 35 °C ou mais | `VERMELHO` | `Calor extremo` |
| temperatura de 3 °C ou menos | `AMARELO` | `Risco de geada` |
| umidade de 30% ou menos | `AMARELO` | `Ar seco` |

4. Cada **alerta** guarda o nível, a hora da leitura e a mensagem.
5. O sistema calcula a **temperatura média**, a **chuva total** e a **leitura mais quente** do dia.

### Como o projeto se organiza

6. Todos os pacotes começam por `br.edu.ifsul.estacao`.
7. O projeto segue o **MVC**:
   - **model**: os dados (a leitura e os dados do alerta);
   - **controller**: recebe as leituras, aplica as regras e guarda os resultados;
   - **view**: o que aparece no painel (a tabela, o resumo e a tela de alerta que pisca).
8. A classe principal fica no **pacote raiz** (`br.edu.ifsul.estacao`) e só inicia o sistema.
9. As **regras de alerta** ficam numa classe separada, **só para uso interno do seu pacote**. Nenhuma classe de outro pacote consegue usá-la.
10. Existem **duas classes chamadas `Alerta`**: a dos **dados** e a da **tela que pisca**. Cada uma fica no pacote do seu papel.
11. A **view nunca altera** dados: ela só pede informações ao controller. E o controller entrega as listas de um jeito que a view **não consiga mexer** nas listas originais.
12. Todos os atributos são `private`.

### Classes do projeto

Use estas classes e **decida em que pacote fica cada uma** (essa decisão é parte do exercício):

| Classe | Responsabilidade |
|---|---|
| `Leitura` | guarda os dados de uma medição |
| `Alerta` (1) | guarda os dados de um alerta |
| `Alerta` (2) | mostra um alerta piscando no painel |
| `RegrasDeAlerta` | decide se uma leitura gera alerta |
| `EstacaoController` | registra leituras, aplica as regras e calcula o resumo |
| `TelaPainel` | mostra a tabela de leituras e o resumo do dia |
| `Main` | registra as leituras do dia e abre as telas |

---

## Requisitos

### Parte A: planejamento (antes de programar)

A.1. Escreva o **nome completo do pacote** de cada classe da tabela acima e justifique em uma frase.

A.2. Desenhe a **árvore de pastas** do projeto, de `src` até cada arquivo `.java`.

A.3. Para cada classe, liste **de quais outras classes ela precisa** e se precisa de `import` para cada uma (lembre: mesmo pacote e `java.lang` não precisam).

A.4. **Nome válido?** Diga se cada nome segue a convenção. Corrija os inválidos.
- `br.edu.ifsul.estacao.controller`
- `br.edu.ifsul.Estacao.View`
- `br.edu.ifsul.estacao.previsão`
- `ifsul.edu.br.estacao.model`
- `br.edu.ifsul.estacao.sensor-chuva`
- `br.edu.ifsul.estacao.3dias`

### Parte B: implementação

B.1. Crie os pacotes e as classes conforme o seu planejamento, seguindo as regras de 1 a 12.

B.2. Na classe principal, registre as leituras do dia:

| Hora | Temperatura (°C) | Umidade (%) | Chuva (mm) |
|---|---|---|---|
| 06h00 | 2,5 | 92 | 0,0 |
| 09h00 | 12,0 | 75 | 0,0 |
| 12h00 | 24,5 | 40 | 0,0 |
| 15h00 | 36,0 | 28 | 0,0 |
| 18h00 | 22,0 | 85 | 42,0 |
| 21h00 | 15,5 | 90 | 3,5 |

B.3. Mostre o painel (tabela e resumo, com a quantidade de alertas emitidos). Depois mostre, um por um, todos os alertas na **tela que pisca**.

**Atenção:** a classe principal precisa usar as **duas** classes `Alerta`. Descubra como fazer isso.

### Parte C: experimentos e perguntas

Responda como comentário no final do `Main.java`.

**1. Experimentos.** Faça cada alteração, compile, anote a mensagem de erro e **desfaça**:

| # | Alteração |
|---|---|
| a | Apague, na classe principal, o `import` da tela do painel. |
| b | Na classe principal, importe a classe de regras de alerta. |
| c | Na tela do painel, troque o `import` das classes do model por um curinga do **pacote raiz** (`br.edu.ifsul.estacao` com `*`). |
| d | Na classe principal, importe as **duas** classes `Alerta` pelo nome curto. |
| e | Na tela do painel, tente mudar a temperatura de uma leitura diretamente. |

Para cada um, explique **por que** o compilador recusou.

**2.** No experimento **c**, o curinga do pacote raiz não trouxe as classes do model. Por quê?

**3.** Como você resolveu o uso das duas classes `Alerta` na classe principal? Existe outro jeito?

**4. Em que pacote fica?** A estação vai crescer. Diga o pacote completo de cada classe nova:
- `Previsao`: guarda a previsão de amanhã (mínima, máxima e chance de chuva);
- `TelaPrevisao`: mostra a previsão no painel;
- `PrevisaoController`: calcula a previsão a partir das leituras dos últimos dias;
- `ConversorFahrenheit`: converte °C para °F, usado só pelas telas;
- `Sensor`: guarda o código e a localização de cada sensor.

**5. Por camada × por funcionalidade.** Redesenhe a árvore de pacotes organizando **por funcionalidade** (por exemplo: `leitura`, `alerta`, `previsao`). Se a regra de "ar seco" mudar para umidade de 25%, quantos pacotes você abre em cada organização?

---

## Saída esperada

```
=== Estação Meteorológica do Câmpus ===
Hora  | Temp (°C) | Umidade | Chuva (mm)
06h00 |       2,5 |     92% |        0,0
09h00 |      12,0 |     75% |        0,0
12h00 |      24,5 |     40% |        0,0
15h00 |      36,0 |     28% |        0,0
18h00 |      22,0 |     85% |       42,0
21h00 |      15,5 |     90% |        3,5

=== Resumo do dia ===
Temperatura média: 18,75 °C
Chuva total: 45,5 mm
Mais quente: 15h00 (36,0 °C)
Alertas emitidos: 3

=== Alertas ===
[*** AMARELO ***] 06h00 - Risco de geada
[*** VERMELHO ***] 15h00 - Calor extremo
[*** VERMELHO ***] 18h00 - Temporal
```

> Repare que às 15h00 a umidade estava em 28% (ar seco), mas o alerta foi de calor extremo: só vale o mais grave.
> A vírgula em `2,5` vem do `printf` em um computador configurado em português. Em outra configuração pode aparecer ponto.
> **Como compilar com pacotes:** de dentro da pasta `src`, compile os arquivos de todos os pacotes juntos. Para rodar, use o nome completo da classe principal. Na IDE, basta rodar a classe principal.

---

## Desafio extra (opcional)

1. **Fahrenheit.** Crie o `ConversorFahrenheit` no pacote que você escolheu na pergunta C.4. Ele só pode ser usado pelas telas. Mostre a coluna de temperatura também em °F na tabela (°F = °C × 1,8 + 32). Que modificador a classe recebeu? Por quê?
2. **Relatório do zelador.** O zelador quer só uma linha: `Hoje: mín. 2,5 °C, máx. 36,0 °C, 45,5 mm de chuva`. Em quais pacotes você precisou mexer para criar essa tela? E se não pudesse mudar o controller?
3. **Dois alertas por leitura.** A coordenação quer que a leitura das 15h00 gere os **dois** alertas (calor extremo e ar seco). Que classes mudam? A view precisou mudar? Por quê?
