// Versão ORIENTADA A OBJETOS: os três vetores viraram uma classe,
// e cada produto é um objeto com os próprios dados
public class Produto {
    // O que o produto SABE (private: só os métodos daqui mexem)
    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    // O que o produto FAZ: vender(i, qtd) virou produto.vender(qtd)
    // A regra do estoque existe em um só lugar
    public boolean vender(int qtd) {
        if (qtd <= 0 || qtd > estoque) {
            return false;
        }
        estoque -= qtd;
        return true;
    }

    public void repor(int qtd) {
        if (qtd > 0) {
            estoque += qtd;
        }
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }
}
