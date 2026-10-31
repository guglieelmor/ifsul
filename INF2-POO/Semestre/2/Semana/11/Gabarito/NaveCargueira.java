// É um Veiculo (e por isso já é Rastreavel) e sabe entregar
public class NaveCargueira extends Veiculo implements Entregador {
    private String nome;

    public NaveCargueira(String codigo, String nome) {
        super(codigo);
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public boolean podeLevar(Encomenda e) {
        return dentroDoAlcance(e) && e.getPesoKg() <= 500;
    }

    @Override
    public double calcularFrete(Encomenda e) {
        return 40 + 0.1 * e.getPesoKg() + 2 * e.getAnosLuz();
    }

    @Override
    public String entregar(Encomenda e) {
        mudarLocalizacao(e.getDestino());
        return "Nave " + getCodigo() + " " + nome + " pousou em " + e.getDestino() + " com " + e.getDescricao();
    }
}
