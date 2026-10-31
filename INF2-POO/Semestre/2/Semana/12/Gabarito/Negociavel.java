// CAPACIDADE que atravessa hierarquias: itens e aventureiros podem ter preço
public interface Negociavel {
    String getNome(); // Aventureiro e Item já têm: o herdado cumpre o contrato
    int valorEmMoedas();

    // A guilda cobra 10% de taxa (arredondando para baixo)
    default int precoNaGuilda() {
        return valorEmMoedas() * 110 / 100;
    }

    default String apresentacao() {
        return "custa " + precoNaGuilda() + " moedas na guilda";
    }

    // static: utilitário do contrato, chamado como Negociavel.cabeNoOrcamento(...)
    static boolean cabeNoOrcamento(int moedas, Negociavel n) {
        return n.precoNaGuilda() <= moedas;
    }
}
