// É uma Criatura (extends) e sabe entregar e ser rastreado (implements)
public class PomboEstelar extends Criatura implements Entregador, Rastreavel {
    private String ultimoLugar = "Pombal";

    public PomboEstelar(String nome) {
        super(nome, "pombo estelar");
    }

    // getNome() vem herdado de Criatura e já cumpre o contrato

    @Override
    public boolean podeLevar(Encomenda e) {
        return dentroDoAlcance(e) && e.getPesoKg() <= 2;
    }

    @Override
    public double calcularFrete(Encomenda e) {
        return 10 + 1.5 * e.getAnosLuz();
    }

    @Override
    public String entregar(Encomenda e) {
        ultimoLugar = e.getDestino();
        return getNome() + " bateu as asas e entregou " + e.getDescricao() + " em " + e.getDestino();
    }

    @Override
    public String localizar() {
        return getNome() + ": voando perto de " + ultimoLugar;
    }
}
