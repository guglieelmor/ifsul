public class Produto {
    // Atributos privados: só os métodos desta classe alteram o estado
    private String nome;
    private double preco;
    private int estoque;

    // Construtor: o objeto já nasce validado
    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        setPreco(preco);
        if (estoque > 0) {
            this.estoque = estoque;
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

    // Setter com regra de negócio: recusa preço zero ou negativo
    public void setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
        }
    }

    // Não existe setEstoque: o estoque só muda por vender() e repor()
    public boolean vender(int qtd) {
        if (qtd > 0 && qtd <= estoque) {
            this.estoque -= qtd;
            return true;
        }
        return false;
    }

    public boolean repor(int qtd) {
        if (qtd > 0) {
            this.estoque += qtd;
            return true;
        }
        return false;
    }

    // Instância: depende do preço DESTE objeto
    public double calcularTotal(int qtd) {
        return preco * qtd;
    }

    // Instância: depende do preço e do estoque DESTE objeto
    public double valorEmEstoque() {
        return preco * estoque;
    }

    // Estático: não usa nenhum atributo, só o valor recebido
    public static String formatarPreco(double valor) {
        return "R$ " + String.format("%.2f", valor);
    }

    public String toString() {
        return nome + " | " + formatarPreco(preco) + " | estoque: " + estoque;
    }
}
