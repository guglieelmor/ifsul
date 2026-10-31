/**
 * Um trabalho que um aluno trouxe para imprimir.
 * Só muda se mudar a regra de preço, de desconto ou de folhas.
 */
public class TrabalhoDeImpressao {
    // Constantes: nenhum número mágico nos métodos
    public static final int LIMITE_DE_PAGINAS = 300;
    public static final double DESCONTO_POR_VOLUME = 0.10;
    private static final int PAGINAS_PARA_DESCONTO = 100;
    private static final int PAGINAS_POR_FOLHA_FRENTE_E_VERSO = 2;

    // 1. Atributos
    private String aluno;
    private int quantidadeDePaginas;
    private Cor cor;
    private boolean ehFrenteEVerso;

    // 2. Construtor (frente e verso começa desligado: é a exceção, não a regra)
    public TrabalhoDeImpressao(String aluno, int quantidadeDePaginas, Cor cor) {
        this.aluno = aluno;
        this.quantidadeDePaginas = quantidadeDePaginas;
        this.cor = cor;
    }

    // 3. Métodos de negócio

    /** Marca o trabalho para ser impresso dos dois lados da folha. */
    public void marcarFrenteEVerso() {
        ehFrenteEVerso = true;
    }

    /**
     * Calcula o valor a pagar, já com o desconto por volume.
     * @return valor em R$
     */
    public double calcularValor() {
        double valor = quantidadeDePaginas * cor.getPrecoPorPagina();
        if (temDireitoADesconto()) {
            return valor - valor * DESCONTO_POR_VOLUME;
        }
        return valor;
    }

    /** Regra da coordenação: o desconto vale "a partir de 100 páginas", inclusive. */
    public boolean temDireitoADesconto() {
        return quantidadeDePaginas >= PAGINAS_PARA_DESCONTO;
    }

    /** Folhas de papel gastas; em frente e verso, uma página ímpar ainda ocupa uma folha. */
    public int calcularFolhasNecessarias() {
        if (ehFrenteEVerso) {
            return (int) Math.ceil((double) quantidadeDePaginas / PAGINAS_POR_FOLHA_FRENTE_E_VERSO);
        }
        return quantidadeDePaginas;
    }

    public boolean passaDoLimiteDePaginas() {
        return quantidadeDePaginas > LIMITE_DE_PAGINAS;
    }

    // 4. Getters
    public String getAluno() {
        return aluno;
    }

    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public Cor getCor() {
        return cor;
    }

    public boolean isFrenteEVerso() {
        return ehFrenteEVerso;
    }
}
