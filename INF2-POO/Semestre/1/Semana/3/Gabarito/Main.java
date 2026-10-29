public class Main {
    public static void main(String[] args) {
        // Uma classe Aluno, escrita uma vez; cada aluno é um objeto
        Aluno ana = new Aluno("Ana Souza");
        Aluno joao = new Aluno("João Lima");
        Aluno bia = new Aluno("Bia Rocha");

        System.out.println("=== Boletim orientado a objetos ===");
        // Ninguém mexe nas notas por fora: envia-se uma mensagem ao objeto
        ana.lancarNota(7.5);
        ana.lancarNota(8.0);
        System.out.println("Ana: lançar nota 11 -> " + ana.lancarNota(11));
        ana.lancarNota(6.0);
        System.out.println("Ana: lançar 4ª nota (9.0) -> " + ana.lancarNota(9.0));

        joao.lancarNota(5.0);
        joao.lancarNota(6.5);
        joao.lancarNota(4.0);

        bia.lancarNota(9.0);
        bia.lancarNota(10.0);
        bia.lancarNota(8.5);

        // ana.notas[0] = 11;  // NÃO COMPILA: notas has private access in Aluno

        // Mesma mensagem, objetos diferentes, respostas diferentes
        mostrarBoletim(ana);
        mostrarBoletim(joao);
        mostrarBoletim(bia);
    }

    // Faz o papel da "secretaria": só pergunta, não conhece a fórmula
    static void mostrarBoletim(Aluno aluno) {
        String situacao = aluno.estaAprovado() ? "APROVADO" : "REPROVADO";
        System.out.printf("%s: média %.2f - %s%n", aluno.getNome(), aluno.calcularMedia(), situacao);
    }
}

/*
 * RESPOSTAS DA PARTE C
 *
 * 1) Linha do tempo:
 *    1. Baixo nível (1940–1959) ..... Assembly / FORTRAN / COBOL
 *    2. Procedural (1960–1979) ...... BASIC / Pascal / C
 *    3. Nasce a POO (1967–1989) ..... Simula 67 / Smalltalk / C++
 *    4. Consolidação (1990–1999) .... Java / Python / JavaScript
 *    5. Multiparadigma (2000–hoje) .. Kotlin / Go / Rust
 *
 * 2) Procedural ou OO?
 *    01 vetor de preços + função calcularTotal() separada ..... Procedural
 *       (dados de um lado, função do outro)
 *    02 contaDaAna.sacar(50) recusa sem saldo ................. OO
 *       (a regra mora dentro do objeto)
 *    03 qualquer função altera a global estoqueTotal .......... Procedural
 *       (variável global sem dono)
 *    04 classe Livro com emprestar() usando o próprio estado .. OO
 *       (dados e ações juntos)
 *    05 main() chama lerDados(), processar() e imprimir() ..... Procedural
 *       (sequência de procedimentos)
 *
 * 3) Pilares:
 *    a) notas private, só mudam por lancarNota() ......... Encapsulamento
 *    b) a secretaria chama estaAprovado() sem a fórmula .. Abstração
 *    c) AlunoIntegrado "é um" Aluno e ganha o curso ...... Herança
 *    d) calcularMedia() simples em Aluno e com pesos
 *       em AlunoSuperior ................................. Polimorfismo
 *    No código deste exercício aparecem encapsulamento (a) e abstração (b).
 *
 * 4) Procedural: 4 variáveis por aluno (nome + 3 notas), então
 *    40 alunos = 160 variáveis. OO: 40 objetos e uma única classe Aluno.
 *
 * 5) n1Ana = 11 compila porque a variável é solta e qualquer linha pode
 *    alterá-la. ana.notas[0] = 11 não compila porque notas é private; e
 *    ana.lancarNota(11) devolve false, porque a regra de 0 a 10 está
 *    dentro da classe.
 *
 * 6) O javac gera o Aluno.class (bytecode). O mesmo .class roda no
 *    Windows, no Linux e no macOS porque quem executa é a JVM de cada
 *    sistema. Em C, seria preciso compilar de novo para cada sistema.
 */
