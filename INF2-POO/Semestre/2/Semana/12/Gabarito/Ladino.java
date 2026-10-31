// É um Aventureiro e é capaz de ser contratado (negociado)
public class Ladino extends Aventureiro implements Negociavel {
    public Ladino(String nome, int nivel) {
        super(nome, nivel);
    }

    @Override
    public String getClasse() {
        return "Ladino";
    }

    @Override
    public int calcularGolpe() {
        return 5 + getNivel();
    }

    @Override
    public int valorEmMoedas() {
        return 20 * getNivel();
    }
}
