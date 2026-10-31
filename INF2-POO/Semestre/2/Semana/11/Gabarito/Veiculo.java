// Classe abstrata que já cumpre um contrato: as filhas herdam localizar()
public abstract class Veiculo implements Rastreavel {
    private String codigo;
    private String localizacao = "Hangar";

    public Veiculo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    protected void mudarLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String localizar() {
        return codigo + ": " + localizacao;
    }
}
