// IDENTIDADE dos itens: outra hierarquia, sem parentesco com Aventureiro
public abstract class Item {
    private String nome;
    private double pesoKg;

    public Item(String nome, double pesoKg) {
        this.nome = nome;
        this.pesoKg = pesoKg > 0 ? pesoKg : 0.1;
    }

    public abstract String efeito();

    public String getNome() {
        return nome;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public String descrever() {
        return String.format("%s (%.1f kg): %s", nome, pesoKg, efeito());
    }
}
