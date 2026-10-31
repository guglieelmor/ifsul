public class Pocao extends Item implements Negociavel {
    public Pocao(String nome) {
        super(nome, 0.3);
    }

    @Override
    public String efeito() {
        return "recupera 20 de vida";
    }

    @Override
    public int valorEmMoedas() {
        return 12;
    }
}
