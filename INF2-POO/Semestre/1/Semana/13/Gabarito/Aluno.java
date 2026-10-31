/**
 * Um aluno inscrito (ou não) na visita técnica.
 */
public class Aluno {
    private String nome;
    private int matricula;

    public Aluno(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    // Usado quando o aluno é impresso (inclusive dentro de mensagens)
    public String toString() {
        return nome + " (" + matricula + ")";
    }
}
