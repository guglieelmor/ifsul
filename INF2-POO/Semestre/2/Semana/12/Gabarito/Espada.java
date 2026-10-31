public class Espada extends Item implements Negociavel {
    public Espada(String nome) {
        super(nome, 3.5);
    }

    @Override
    public String efeito() {
        return "+6 de dano";
    }

    @Override
    public int valorEmMoedas() {
        return 80;
    }
}
