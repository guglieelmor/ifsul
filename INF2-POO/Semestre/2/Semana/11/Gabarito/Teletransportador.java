// Sem superclasse e sem parentesco com o pombo ou a nave: só cumpre o contrato
public class Teletransportador implements Entregador {
    private String nome;

    public Teletransportador(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    // Leva qualquer peso, mas só dentro do alcance
    @Override
    public boolean podeLevar(Encomenda e) {
        return dentroDoAlcance(e);
    }

    // Preço fixo: a distância não importa
    @Override
    public double calcularFrete(Encomenda e) {
        return 120;
    }

    @Override
    public String entregar(Encomenda e) {
        return nome + " teletransportou " + e.getDescricao() + " para " + e.getDestino() + " em 0 segundos";
    }
}
