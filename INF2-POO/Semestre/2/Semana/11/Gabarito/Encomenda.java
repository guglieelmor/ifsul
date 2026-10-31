// Comparable: a "ordem natural" das encomendas é a distância
public class Encomenda implements Comparable<Encomenda> {
    private String descricao;
    private String destino;
    private double pesoKg;
    private int anosLuz;

    public Encomenda(String descricao, String destino, double pesoKg, int anosLuz) {
        this.descricao = descricao;
        this.destino = destino;
        this.pesoKg = pesoKg;
        this.anosLuz = anosLuz;
    }

    // negativo: esta vem antes · zero: empate · positivo: esta vem depois
    @Override
    public int compareTo(Encomenda outra) {
        return Integer.compare(anosLuz, outra.anosLuz);
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDestino() {
        return destino;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public int getAnosLuz() {
        return anosLuz;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s (%.1f kg, %d anos-luz)", descricao, destino, pesoKg, anosLuz);
    }
}
