public class Guerreiro extends Aventureiro {
    public Guerreiro(String nome, int nivel) {
        super(nome, nivel);
    }

    @Override
    public String getClasse() {
        return "Guerreiro";
    }

    @Override
    public int calcularGolpe() {
        return 8 + 2 * getNivel();
    }
}
