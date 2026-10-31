// Requisito novo: continua Guerreiro e só ganha o que paladino faz
public class Paladino extends Guerreiro implements Curador {
    public Paladino(String nome, int nivel) {
        super(nome, nivel);
    }

    @Override
    public String getClasse() {
        return "Paladino";
    }

    @Override
    public int poderDeCura() {
        return 4 * getNivel();
    }
}
