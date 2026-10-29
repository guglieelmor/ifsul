public class Main {
    public static void main(String[] args) {
        // Uma classe Produto (molde), três objetos (instâncias)
        Produto pastel = new Produto("Pastel de carne", 6.00, 5);
        Produto suco = new Produto("Suco de laranja", 4.50, 15);
        Produto cafe = new Produto("Café com leite", 3.00, 40);

        // Refatorar: mesmo cenário, mesmo comportamento da versão estruturada
        System.out.println("=== Cantina orientada a objetos ===");
        System.out.println("Vender 3 x Pastel de carne: " + pastel.vender(3));
        System.out.printf("Total: R$ %.2f%n", 3 * pastel.getPreco());
        System.out.println("Vender 3 x Pastel de carne: " + pastel.vender(3));
        System.out.println("Vender 2 x Suco de laranja: " + suco.vender(2));
        System.out.println("Vender 0 x Café com leite: " + cafe.vender(0));
        pastel.repor(10);
        System.out.println("Repor 10 x Pastel de carne");
        mostrarEstoque(pastel);
        mostrarEstoque(suco);
        mostrarEstoque(cafe);

        // suco.estoque = -5;  // NÃO COMPILA: estoque has private access in Produto
    }

    static void mostrarEstoque(Produto p) {
        System.out.printf("%s: R$ %.2f - estoque %d%n", p.getNome(), p.getPreco(), p.getEstoque());
    }
}

/*
 * RESPOSTAS DA PARTE C
 *
 * 1) Classe ou objeto?
 *    01 Produto ....................................... Classe
 *    02 o café com leite de R$ 3,00 com 40 no estoque . Objeto de Produto
 *    03 Notebook ...................................... Classe
 *    04 o notebook patrimônio 4471, marca Dell ........ Objeto de Notebook
 *    05 Lanche ........................................ Classe
 *    06 o aluno Bruno, matrícula 2026102 .............. Objeto de Aluno
 *
 * 2) Do enunciado às classes:
 *    Classes ..... Aluno, Notebook, Emprestimo
 *    Atributos ... Notebook: patrimonio, marca, disponivel;
 *                  Aluno: matricula, nome, totalEmprestimos
 *    Métodos ..... Notebook: emprestar(), devolver();
 *                  Aluno: contarEmprestimo()
 *    Erros: "Emprestar" é verbo (vira método, não classe); "patrimônio"
 *    é atributo de Notebook, não uma classe.
 *
 * 3) Estruturada ou POO?
 *    01 calcular uma vez o troco de uma compra ...... Estruturada
 *       (programa curto, sem mudanças)
 *    02 biblioteca do câmpus, anos de uso, 3 bolsistas  POO
 *       (sistema que cresce e muda, com equipe)
 *    03 sensor de nível da caixa d'água, 2 KB ....... Estruturada
 *       (memória muito limitada, regra simples)
 *    04 sistema de matrículas com cursos, turmas,
 *       professores e regras que mudam ............. POO
 *       (muitas entidades e regras ligadas entre si)
 *
 * 4) Estruturada: em vender(), mas qualquer linha pode alterar estoques[]
 *    sem passar por ela (estoques[1] = -5 funcionou). POO: só em
 *    Produto.vender(), e estoque é private (suco.estoque = -5 não
 *    compila). Se a regra mudar, na POO se altera um método só.
 *
 * 5) Não. CantinaEstruturada usa "class", mas tem só métodos static e
 *    dados em vetores que qualquer um altera: continua estruturada por
 *    dentro. Usar class não basta; POO é juntar dados e ações em objetos
 *    e proteger os dados.
 *
 * 6) Em Produto.vender(): é POO o fato de ser um método da classe que usa
 *    o atributo estoque do próprio objeto. É estruturada a parte de
 *    dentro: a seleção (if) e a sequência (estoque -= qtd; return true).
 */
