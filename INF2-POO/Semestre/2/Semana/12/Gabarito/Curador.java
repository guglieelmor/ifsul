// CAPACIDADE ("é capaz de"): só alguns aventureiros curam
public interface Curador {
    int MAX_CURA = 30; // constante: public static final

    String getNome(); // Aventureiro já tem: o herdado cumpre o contrato
    int poderDeCura();

    // default: todo curador cura do mesmo jeito, respeitando o limite
    default int curar(Aventureiro alvo) {
        int cura = Math.min(poderDeCura(), MAX_CURA);
        alvo.receberCura(cura);
        return cura;
    }

    default String apresentacao() {
        return "cura até " + Math.min(poderDeCura(), MAX_CURA) + " de vida";
    }
}
