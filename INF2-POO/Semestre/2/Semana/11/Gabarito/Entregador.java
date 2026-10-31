// O CONTRATO: o que todo entregador precisa saber fazer (e não COMO)
public interface Entregador {
    // Variável em interface vira constante (public static final)
    int ALCANCE_MAXIMO = 100; // anos-luz

    // Métodos sem corpo: public abstract implícito
    String getNome();
    boolean podeLevar(Encomenda e);
    double calcularFrete(Encomenda e);
    String entregar(Encomenda e);

    // default: já vem pronto e todas as classes ganham de graça
    default boolean dentroDoAlcance(Encomenda e) {
        return e.getAnosLuz() <= ALCANCE_MAXIMO;
    }

    default String entregarUrgente(Encomenda e) {
        return "[URGENTE] " + entregar(e);
    }

    // static: pertence à interface; chame Entregador.padrao()
    static Entregador padrao() {
        return new PomboEstelar("Pombo de plantão");
    }
}
