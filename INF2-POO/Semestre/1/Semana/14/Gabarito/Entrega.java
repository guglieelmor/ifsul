/**
 * Uma entrega de resíduo feita por uma turma.
 */
public class Entrega {
    private String turma;
    private Residuo residuo; // o tipo é o enum: só aceita valores válidos
    private double kg;

    public Entrega(String turma, Residuo residuo, double kg) {
        this.turma = turma;
        this.residuo = residuo;
        this.kg = kg;
    }

    public String getTurma() {
        return turma;
    }

    public Residuo getResiduo() {
        return residuo;
    }

    public double getKg() {
        return kg;
    }

    public double getPontos() {
        return kg * residuo.getPontosPorKg();
    }
}
