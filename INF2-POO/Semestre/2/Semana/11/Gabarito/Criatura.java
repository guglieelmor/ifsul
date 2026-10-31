// Classe abstrata: estado em comum das criaturas (relação "é um")
public abstract class Criatura {
    private String nome;
    private String especie;

    public Criatura(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }
}
