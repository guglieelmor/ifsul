// IDENTIDADE ("é um"): estado, construtor e código comuns a todo aventureiro
public abstract class Aventureiro {
    private String nome;
    private int nivel;
    private int vida;

    // O construtor valida o nível num lugar só; as filhas chamam super(...)
    public Aventureiro(String nome, int nivel) {
        this.nome = nome;
        if (nivel < 1 || nivel > 20) {
            nivel = 1;
        }
        this.nivel = nivel;
        this.vida = getVidaMaxima();
    }

    // Métodos abstratos: cada classe diz o seu jeito
    public abstract String getClasse();
    public abstract int calcularGolpe();

    // Métodos concretos: iguais para todos, escritos uma vez
    public int getVidaMaxima() {
        return 50 + 10 * nivel;
    }

    public void receberDano(int dano) {
        vida = Math.max(0, vida - dano);
    }

    public void receberCura(int cura) {
        vida = Math.min(getVidaMaxima(), vida + cura);
    }

    public void descansar() {
        vida = getVidaMaxima();
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVida() {
        return vida;
    }

    public String status() {
        return nome + " (" + getClasse() + ", nível " + nivel + ") - vida " + vida + "/" + getVidaMaxima();
    }
}
