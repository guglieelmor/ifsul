/**
 * Selo que a turma recebe no fim da campanha, conforme os pontos.
 * As constantes estão em ordem crescente de pontos mínimos.
 */
public enum Selo {
    SEM_SELO(0),
    BRONZE(50),
    PRATA(100),
    OURO(200);

    private final int pontosMinimos;

    Selo(int pontosMinimos) {
        this.pontosMinimos = pontosMinimos;
    }

    public int getPontosMinimos() {
        return pontosMinimos;
    }

    // Percorre values() na ordem declarada e fica com o maior selo alcançado
    public static Selo paraPontos(double pontos) {
        Selo alcancado = SEM_SELO;
        for (Selo s : values()) {
            if (pontos >= s.pontosMinimos) {
                alcancado = s;
            }
        }
        return alcancado;
    }
}
