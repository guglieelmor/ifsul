// Versão ORIENTADA A OBJETOS: dados e regras do aluno moram juntos
public class Aluno {
    // Dados (private: só os métodos daqui mexem neles)
    private String nome;
    private double[] notas = new double[3];
    private int qtd = 0;

    public Aluno(String nome) {
        this.nome = nome;
    }

    // Encapsulamento: a regra "nota de 0 a 10" mora num lugar só
    public boolean lancarNota(double n) {
        if (n < 0 || n > 10 || qtd == notas.length) {
            return false; // recusa
        }
        notas[qtd++] = n;
        return true;
    }

    public double calcularMedia() {
        if (qtd == 0) {
            return 0;
        }
        double soma = 0;
        for (int i = 0; i < qtd; i++) {
            soma += notas[i];
        }
        return soma / qtd;
    }

    // Abstração: quem pergunta não precisa saber a fórmula da média
    public boolean estaAprovado() {
        return calcularMedia() >= 6.0;
    }

    public String getNome() {
        return nome;
    }
}
