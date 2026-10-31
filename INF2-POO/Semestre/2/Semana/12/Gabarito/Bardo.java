// Dois contratos com o mesmo default: o Java obriga o Bardo a decidir
public class Bardo extends Aventureiro implements Curador, Negociavel {
    public Bardo(String nome, int nivel) {
        super(nome, nivel);
    }

    @Override
    public String getClasse() {
        return "Bardo";
    }

    @Override
    public int calcularGolpe() {
        return getNivel();
    }

    @Override
    public int poderDeCura() {
        return 3 * getNivel();
    }

    @Override
    public int valorEmMoedas() {
        return 15 * getNivel();
    }

    // Usa as duas versões, chamando cada uma pela interface
    @Override
    public String apresentacao() {
        return Curador.super.apresentacao() + " | " + Negociavel.super.apresentacao();
    }
}
