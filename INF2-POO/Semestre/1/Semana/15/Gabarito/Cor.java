/**
 * Cor da impressão. O preço por página fica junto da cor,
 * e não solto em vários lugares do programa.
 */
public enum Cor {
    PRETO_E_BRANCO(0.25, "P&B"),
    COLORIDA(1.00, "coloridas");

    // 1. Atributos
    private final double precoPorPagina;
    private final String descricao;

    // 2. Construtor
    Cor(double precoPorPagina, String descricao) {
        this.precoPorPagina = precoPorPagina;
        this.descricao = descricao;
    }

    // 4. Getters
    public double getPrecoPorPagina() {
        return precoPorPagina;
    }

    public String getDescricao() {
        return descricao;
    }
}
