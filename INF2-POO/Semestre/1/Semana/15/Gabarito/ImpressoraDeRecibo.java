/**
 * Tudo o que aparece na tela da reprografia.
 * Só muda se mudar o formato das mensagens.
 */
public class ImpressoraDeRecibo {

    public void imprimirReciboEmDinheiro(TrabalhoDeImpressao trabalho, double troco) {
        System.out.printf("%s | dinheiro, troco R$ %.2f%n", descrever(trabalho), troco);
    }

    public void imprimirReciboNoCartao(TrabalhoDeImpressao trabalho) {
        System.out.printf("%s | cartão%n", descrever(trabalho));
    }

    public void imprimirLimiteExcedido(TrabalhoDeImpressao trabalho) {
        System.out.printf("Trabalho recusado: %d páginas passa do limite de %d (%s)%n",
                trabalho.getQuantidadeDePaginas(), TrabalhoDeImpressao.LIMITE_DE_PAGINAS,
                trabalho.getAluno());
    }

    public void imprimirFaltaDePapel(TrabalhoDeImpressao trabalho, int folhasDisponiveis) {
        System.out.printf("Sem papel: o trabalho precisa de %d folhas e restam %d (%s)%n",
                trabalho.calcularFolhasNecessarias(), folhasDisponiveis, trabalho.getAluno());
    }

    public void imprimirPagamentoRecusado(TrabalhoDeImpressao trabalho, double valorEntregue) {
        System.out.printf("Pagamento recusado: R$ %.2f não cobre R$ %.2f (%s)%n",
                valorEntregue, trabalho.calcularValor(), trabalho.getAluno());
    }

    public void imprimirAvisoDeEstoqueBaixo(int folhasDisponiveis) {
        System.out.printf("Aviso: estoque baixo, restam %d folhas%n", folhasDisponiveis);
    }

    public void imprimirFechamento(Caixa caixa, EstoqueDePapel estoque) {
        System.out.println("=== Fechamento do caixa ===");
        System.out.printf("Trabalhos atendidos: %d%n", caixa.getQuantidadeDePagamentos());
        System.out.printf("Total cobrado: R$ %.2f%n", caixa.getTotalCobrado());
        System.out.printf("Taxa do cartão (%s): R$ %.2f%n",
                emPorcentagem(Caixa.TAXA_CARTAO), caixa.getTotalDeTaxas());
        System.out.printf("Valor líquido: R$ %.2f%n", caixa.calcularValorLiquido());
        System.out.printf("Folhas usadas: %d | restantes: %d%n",
                estoque.getFolhasUsadas(), estoque.getFolhasDisponiveis());
    }

    // Monta a parte comum dos recibos: "Ana: 40 págs P&B frente e verso | 20 folhas | R$ 10,00"
    private String descrever(TrabalhoDeImpressao trabalho) {
        String texto = String.format("%s: %d págs %s", trabalho.getAluno(),
                trabalho.getQuantidadeDePaginas(), trabalho.getCor().getDescricao());
        if (trabalho.isFrenteEVerso()) {
            texto += " frente e verso";
        }
        texto += String.format(" | %d folhas | R$ %.2f",
                trabalho.calcularFolhasNecessarias(), trabalho.calcularValor());
        if (trabalho.temDireitoADesconto()) {
            texto += " (desconto " + emPorcentagem(TrabalhoDeImpressao.DESCONTO_POR_VOLUME) + ")";
        }
        return texto;
    }

    // 0.10 -> "10%": o texto sai da constante, então acompanha qualquer mudança nela
    private String emPorcentagem(double taxa) {
        return Math.round(taxa * 100) + "%";
    }
}
