/**
 * O caixa da reprografia: recebe os pagamentos e soma o dia.
 * Só muda se mudar a forma de pagamento.
 */
public class Caixa {
    // A maquininha fica com 5% de cada venda no cartão.
    // Não confundir com o desconto por volume: são regras diferentes.
    public static final double TAXA_CARTAO = 0.05;

    // 1. Atributos
    private double totalCobrado;
    private double totalDeTaxas;
    private int quantidadeDePagamentos;

    // 2. Métodos de negócio
    public boolean valorCobreOTotal(double valorEntregue, double total) {
        return valorEntregue >= total;
    }

    /**
     * Recebe em dinheiro. Confira antes com valorCobreOTotal.
     * @return troco em R$
     */
    public double receberEmDinheiro(double total, double valorEntregue) {
        registrarPagamento(total);
        return valorEntregue - total;
    }

    /** Recebe no cartão; a taxa da maquininha sai do valor da reprografia. */
    public void receberNoCartao(double total) {
        registrarPagamento(total);
        totalDeTaxas += total * TAXA_CARTAO;
    }

    public double calcularValorLiquido() {
        return totalCobrado - totalDeTaxas;
    }

    private void registrarPagamento(double total) {
        totalCobrado += total;
        quantidadeDePagamentos++;
    }

    // 3. Getters
    public double getTotalCobrado() {
        return totalCobrado;
    }

    public double getTotalDeTaxas() {
        return totalDeTaxas;
    }

    public int getQuantidadeDePagamentos() {
        return quantidadeDePagamentos;
    }
}
