/**
 * Atende os alunos: confere o trabalho, cobra e imprime.
 * Ela só coordena; cada regra fica na classe responsável por ela.
 */
public class Reprografia {
    // 1. Atributos
    private EstoqueDePapel estoque;
    private Caixa caixa = new Caixa();
    private ImpressoraDeRecibo impressora = new ImpressoraDeRecibo();

    // 2. Construtor
    public Reprografia(int folhasIniciais) {
        this.estoque = new EstoqueDePapel(folhasIniciais);
    }

    // 3. Métodos de negócio
    public void atenderEmDinheiro(TrabalhoDeImpressao trabalho, double valorEntregue) {
        if (!podeImprimir(trabalho)) {
            return;
        }
        double total = trabalho.calcularValor();
        // Confere o pagamento antes de imprimir: papel gasto não volta para o pacote
        if (!caixa.valorCobreOTotal(valorEntregue, total)) {
            impressora.imprimirPagamentoRecusado(trabalho, valorEntregue);
            return;
        }
        double troco = caixa.receberEmDinheiro(total, valorEntregue);
        estoque.retirarFolhas(trabalho.calcularFolhasNecessarias());
        impressora.imprimirReciboEmDinheiro(trabalho, troco);
        avisarSeEstoqueBaixo();
    }

    public void atenderNoCartao(TrabalhoDeImpressao trabalho) {
        if (!podeImprimir(trabalho)) {
            return;
        }
        caixa.receberNoCartao(trabalho.calcularValor());
        estoque.retirarFolhas(trabalho.calcularFolhasNecessarias());
        impressora.imprimirReciboNoCartao(trabalho);
        avisarSeEstoqueBaixo();
    }

    public void fecharCaixa() {
        impressora.imprimirFechamento(caixa, estoque);
    }

    private boolean podeImprimir(TrabalhoDeImpressao trabalho) {
        if (trabalho.passaDoLimiteDePaginas()) {
            impressora.imprimirLimiteExcedido(trabalho);
            return false;
        }
        if (!estoque.temFolhasPara(trabalho.calcularFolhasNecessarias())) {
            impressora.imprimirFaltaDePapel(trabalho, estoque.getFolhasDisponiveis());
            return false;
        }
        return true;
    }

    private void avisarSeEstoqueBaixo() {
        if (estoque.estaAbaixoDoMinimo()) {
            impressora.imprimirAvisoDeEstoqueBaixo(estoque.getFolhasDisponiveis());
        }
    }
}
