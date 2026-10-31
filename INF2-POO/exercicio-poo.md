# Gerador de exercício – INF2-POO

Você vai criar um exercício da disciplina INF2-POO com base em um slide.

Link do slide: o que o usuário enviar junto com o pedido.

## Passos

1. **Pergunte o semestre** usando a ferramenta AskUserQuestion, com as opções `1` e `2`.

2. **Pergunte a semana** (número da semana, ex.: 1, 2, 3...). Use AskUserQuestion; o usuário pode digitar o número em "Other".

3. **Monte o caminho** da semana, relativo a este arquivo (raiz de `INF2-POO`):
   `INF2-POO/Semestre/<semestre>/Semana/<semana>/`
   - Se o diretório da semana não existir, crie-o.
   - Se já existir um arquivo `exercicio_*.md` ou um diretório `Gabarito/` com conteúdo, mostre o que existe e pergunte antes de sobrescrever.

4. **Obtenha o slide**: se o link não foi enviado, peça ao usuário. Leia o conteúdo:
   - Google Slides/Drive: use as ferramentas do Google Drive (`read_file_content` / `download_file_content`) ou exporte via `https://docs.google.com/presentation/d/<ID>/export/txt` com WebFetch.
   - Outros links (PDF, página web): use WebFetch.
   - Se não conseguir acessar, avise e peça ao usuário um link público ou o arquivo.

5. **Crie o enunciado** dentro do diretório da semana, num arquivo nomeado a partir do título: `exercicio_<titulo>.md` (título em minúsculas, sem acentos, palavras separadas por `_`; ex.: "Sistema da Cantina" → `exercicio_sistema_da_cantina.md`). O enunciado fica só em `.md` (não crie `.txt` nem `.html`). Escreva em português, contendo:
   - Título no formato `# Exercício - <título do exercício>` (ex.: `# Exercício - Sistema da Cantina`), seguido do tema da aula (conforme o slide)
   - Objetivos de aprendizagem (conceitos do slide que o exercício pratica)
   - Contexto/cenário do problema
   - Requisitos numerados: classes, atributos, métodos, regras de encapsulamento etc.
   - Exemplo de execução esperada (entrada/saída)
   - Desafio extra opcional
   O exercício deve cobrar exatamente os conceitos apresentados no slide, sem exigir conteúdo de aulas futuras.
   **Sem código pronto no enunciado:** o aluno deve fazer tudo. Descreva os requisitos como regras do negócio, em português (o que o programa deve fazer), e não como código: nada de declarações de classes/interfaces, cabeçalhos com `extends`/`implements`, assinaturas de métodos, tabelas de atributos com tipos ou trechos da solução. Pode sugerir os nomes das classes e mostrar a saída esperada. Nenhum trecho de código Java no enunciado, nem como material de análise (nada de "encontre os erros" com código ou de exemplos de sintaxe). As decisões de modelagem (o que é classe, interface, atributo, método) ficam para o aluno.

6. **Crie o diretório `Gabarito/`** no mesmo caminho e coloque nele a solução em `.java`:
   - Um arquivo por classe pública (nome do arquivo = nome da classe), mais uma classe `Main` com o método `main` demonstrando o uso.
   - Código limpo, com comentários didáticos curtos em português, nomes em português coerentes com o enunciado.
   - Sem `package` (para compilar direto com `javac *.java`), exceto quando o slide ensina pacotes: aí use a estrutura de pastas do slide dentro de `Gabarito/src/` (ex.: `src/br/edu/ifsul/<projeto>/modelo/`) e compile os pacotes juntos a partir de `src`.

7. **Valide o gabarito**: compile e execute numa pasta temporária fora do repositório e corrija erros. Não deixe arquivos `.class` no repositório.
   - O `javac` não está no Linux (WSL); use o JDK do Windows: `/mnt/c/Program Files/Java/jdk-24/bin/javac.exe` e `java.exe`.
   - Exemplo: `javac.exe -encoding UTF-8 -d 'C:\Users\Guilherme Oliveira\AppData\Local\Temp\gab' *.java` e `java.exe -Dstdout.encoding=UTF-8 -cp 'C:\Users\Guilherme Oliveira\AppData\Local\Temp\gab' Main`; apague a pasta temporária no final.
   - Use a saída real da execução como "saída esperada" no arquivo do enunciado.

8. **Crie o `Gabarito.zip`** no mesmo diretório onde está a pasta `Gabarito/` (o diretório da semana), contendo a pasta `Gabarito/` com os `.java`:
   - Faça isso só depois de validar o gabarito, para o zip ter a versão final.
   - O `zip` não está instalado no WSL; use o Python, a partir do diretório da semana: `python3 -m zipfile -c Gabarito.zip Gabarito/`
   - Confira o conteúdo com `python3 -m zipfile -l Gabarito.zip` (sem arquivos `.class`).
   - Se o gabarito for alterado depois, recrie o `Gabarito.zip`.

9. Ao final, liste os arquivos criados e resuma o exercício em 2–3 linhas.

Estrutura final esperada:
```
INF2-POO/Semestre/<semestre>/Semana/<semana>/
├── exercicio_<titulo>.md
├── Gabarito.zip
└── Gabarito/
    ├── <Classe>.java
    └── Main.java
```
