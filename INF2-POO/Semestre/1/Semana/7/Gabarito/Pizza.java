public class Pizza {
    private String cliente;
    private String sabor;
    private char tamanho;      // 'P', 'M' ou 'G'
    private int fatias;
    private boolean bordaRecheada;

    // Construtor completo: a regra de montar a pizza mora só aqui
    public Pizza(String cliente, String sabor, char tamanho, boolean bordaRecheada) {
        this.cliente = cliente;
        this.sabor = sabor;
        if (tamanho != 'P' && tamanho != 'M' && tamanho != 'G') {
            tamanho = 'M'; // tamanho inválido vira média
        }
        this.tamanho = tamanho;
        this.bordaRecheada = bordaRecheada;
        if (tamanho == 'P') {
            fatias = 4;
        } else if (tamanho == 'M') {
            fatias = 6;
        } else {
            fatias = 8;
        }
    }

    // Sobrecarga: os outros construtores reaproveitam o completo com this(...)
    public Pizza(String cliente, String sabor, char tamanho) {
        this(cliente, sabor, tamanho, false);
    }

    public Pizza(String cliente, String sabor) {
        this(cliente, sabor, 'M');
    }

    // A pizza da casa, para quem pede no balcão
    public Pizza() {
        this("Balcão", "Margherita");
    }

    public boolean comerFatia() {
        if (fatias == 0) {
            return false;
        }
        fatias--;
        return true;
    }

    public double calcularPreco() {
        double preco;
        if (tamanho == 'P') {
            preco = 30;
        } else if (tamanho == 'M') {
            preco = 42;
        } else {
            preco = 55;
        }
        if (bordaRecheada) {
            preco = preco + 8;
        }
        return preco;
    }

    public int getFatias() {
        return fatias;
    }

    public String descrever() {
        String borda = bordaRecheada ? " com borda" : "";
        return String.format("%s %c%s para %s | %d fatias | R$ %.2f",
                sabor, tamanho, borda, cliente, fatias, calcularPreco());
    }
}
