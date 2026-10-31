/**
 * Tipos de resíduo aceitos na campanha: uma lista fixa, por isso é um enum.
 * Cada constante guarda a cor da lixeira e quantos pontos vale cada kg.
 */
public enum Residuo {
    PAPEL("azul", 2),
    PLASTICO("vermelha", 3),
    VIDRO("verde", 2),
    METAL("amarela", 5),
    ORGANICO("marrom", 1),
    REJEITO("cinza", 0);

    private final String corDaLixeira;
    private final int pontosPorKg;

    // Construtor de enum é sempre privado: só as constantes acima o usam
    Residuo(String corDaLixeira, int pontosPorKg) {
        this.corDaLixeira = corDaLixeira;
        this.pontosPorKg = pontosPorKg;
    }

    public String getCorDaLixeira() {
        return corDaLixeira;
    }

    public int getPontosPorKg() {
        return pontosPorKg;
    }

    // Regra que usa this: orgânico vai para a composteira e rejeito não se recicla
    public boolean isReciclavel() {
        return this != ORGANICO && this != REJEITO;
    }

    // switch com seta e sem default: se surgir uma constante nova, não compila
    public String getDica() {
        return switch (this) {
            case PAPEL -> "seco e sem gordura";
            case PLASTICO -> "lavado e amassado";
            case VIDRO -> "inteiro ou embrulhado em jornal";
            case METAL -> "latinhas lavadas";
            case ORGANICO -> "vai para a composteira da horta";
            case REJEITO -> "vai para o lixo comum";
        };
    }

    /**
     * Converte o texto digitado na balança em constante.
     * Limpa espaços e maiúsculas e procura em values(): se não existir,
     * devolve null em vez de estourar a exceção do valueOf().
     */
    public static Residuo deTexto(String texto) {
        String limpo = texto.trim().toUpperCase();
        for (Residuo r : values()) {
            if (r.name().equals(limpo)) {
                return r;
            }
        }
        return null;
    }
}
