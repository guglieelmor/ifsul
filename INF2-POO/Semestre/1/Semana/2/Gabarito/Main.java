public class Main {
    public static void main(String[] args) {
        // Uma classe (molde), dois objetos (cartões) com saldos próprios
        CartaoCantina ana = new CartaoCantina("2026101", "Ana");
        CartaoCantina bruno = new CartaoCantina("2026102", "Bruno");

        System.out.println("=== Versão orientada a objetos ===");
        // O caixa não mexe no saldo: ele manda uma mensagem ao cartão
        System.out.println("Recarregar R$ 20,00 para Ana: " + ana.recarregar(20.00));
        System.out.println("Ana paga pastel de R$ 6,00: " + ana.pagar(6.00));
        mostrarSaldo(ana);
        System.out.println("Ana tenta pagar R$ 20,00: " + ana.pagar(20.00));
        System.out.println("Ana tenta recarregar R$ -5,00: " + ana.recarregar(-5.00));
        mostrarSaldo(ana);

        System.out.println("Recarregar R$ 4,00 para Bruno: " + bruno.recarregar(4.00));
        System.out.println("Bruno tenta pagar pastel de R$ 6,00: " + bruno.pagar(6.00));
        mostrarSaldo(bruno);

        // ana.saldo = -50;  // NÃO COMPILA: saldo has private access in CartaoCantina
        mostrarSaldo(ana);
    }

    static void mostrarSaldo(CartaoCantina cartao) {
        System.out.printf("Saldo de %s: R$ %.2f%n", cartao.getTitular(), cartao.consultarSaldo());
    }
}

/*
 * RESPOSTAS DA PARTE C
 *
 * 1) Qual é o problema?
 *    01 gerarRelatorio() zerou totalVendas ............ Global
 *    02 desconto copiado em 5 funções ................ Duplicação
 *    03 estoque[7] = -3 aceito ....................... Sem proteção
 *    04 float -> int obrigou a mudar 38 funções ...... Acoplamento
 *    05 corrigiram pagar() e esqueceram estornar() ... Duplicação
 *    06 testar pagar() exige preparar as globais ..... Global (e Acoplamento)
 *
 * 2) Identificando classes em "Na biblioteca do câmpus, o aluno pega um
 *    livro emprestado. Cada livro tem título, autor e ISBN e pode ser
 *    emprestado e devolvido.":
 *    Classes (substantivos) ..... Biblioteca, Aluno, Livro
 *    Atributos (características)  titulo, autor, isbn
 *    Métodos (verbos) ........... emprestar(), devolver()
 *
 * 3) Na versão estruturada, saldo[0] = -50 compila e roda, porque o vetor
 *    é global e não tem dono. Na versão POO, ana.saldo = -50 não compila,
 *    porque saldo é private: só pagar() e recarregar() podem alterá-lo, e
 *    os dois recusam valores que deixariam o saldo inválido.
 *
 * 4) Com 10 funções que podem conversar entre si: 10 × 9 ÷ 2 = 45 pares.
 *    Com 20 funções: 20 × 19 ÷ 2 = 190 pares.
 */
