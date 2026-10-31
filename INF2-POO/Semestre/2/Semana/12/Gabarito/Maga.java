// É uma Aventureira e é capaz de curar
public class Maga extends Aventureiro implements Curador {
    public Maga(String nome, int nivel) {
        super(nome, nivel);
    }

    @Override
    public String getClasse() {
        return "Maga";
    }

    @Override
    public int calcularGolpe() {
        return 3 * getNivel();
    }

    @Override
    public int poderDeCura() {
        return 5 * getNivel();
    }
}
