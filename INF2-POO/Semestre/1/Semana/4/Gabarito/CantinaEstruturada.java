// Versão ESTRUTURADA: cada produto é um índice i espalhado em
// vetores paralelos, e as funções ficam fora dos dados
public class CantinaEstruturada {
    static String[] nomes = {"Pastel de carne", "Suco de laranja", "Café com leite"};
    static double[] precos = {6.00, 4.50, 3.00};
    static int[] estoques = {5, 15, 40};

    // A regra "não vender sem estoque" mora nesta função...
    // ...mas nada obriga o resto do programa a passar por ela
    static boolean vender(int i, int qtd) {
        if (qtd <= 0 || qtd > estoques[i]) {
            return false;
        }
        estoques[i] -= qtd;
        return true;
    }

    static void repor(int i, int qtd) {
        if (qtd > 0) {
            estoques[i] += qtd;
        }
    }

    static void mostrarEstoque(int i) {
        System.out.printf("%s: R$ %.2f - estoque %d%n", nomes[i], precos[i], estoques[i]);
    }

    public static void main(String[] args) {
        System.out.println("=== Cantina estruturada ===");
        System.out.println("Vender 3 x Pastel de carne: " + vender(0, 3));
        System.out.printf("Total: R$ %.2f%n", 3 * precos[0]);
        System.out.println("Vender 3 x Pastel de carne: " + vender(0, 3));
        System.out.println("Vender 2 x Suco de laranja: " + vender(1, 2));
        System.out.println("Vender 0 x Café com leite: " + vender(2, 0));
        repor(0, 10);
        System.out.println("Repor 10 x Pastel de carne");
        for (int i = 0; i < nomes.length; i++) {
            mostrarEstoque(i);
        }

        // Ninguém impede: o estoque fica negativo sem passar por vender()
        estoques[1] = -5;
        System.out.println("Depois de estoques[1] = -5:");
        mostrarEstoque(1);
    }
}
