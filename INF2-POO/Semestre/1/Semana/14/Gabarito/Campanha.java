import java.util.ArrayList;

/**
 * A campanha de coleta seletiva: registra as entregas e calcula os resultados.
 */
public class Campanha {
    private ArrayList<Entrega> entregas = new ArrayList<>();

    // Recebe o resíduo como texto, do jeito que foi digitado na balança
    public void registrar(String turma, String residuoDigitado, double kg) {
        Residuo residuo = Residuo.deTexto(residuoDigitado);
        if (residuo == null) {
            System.out.println("Resíduo desconhecido: \"" + residuoDigitado + "\" (" + turma + ")");
            return;
        }
        if (kg <= 0) {
            System.out.printf("Peso inválido: %.1f kg de %s (%s)%n", kg, residuo, turma);
            return;
        }
        entregas.add(new Entrega(turma, residuo, kg));
        System.out.printf("%s: %.1f kg de %s -> lixeira %s%n", turma, kg, residuo,
                residuo.getCorDaLixeira());
    }

    public double pontosDaTurma(String turma) {
        double total = 0;
        for (Entrega e : entregas) {
            if (e.getTurma().equals(turma)) {
                total += e.getPontos();
            }
        }
        return total;
    }

    public double kgDoResiduo(Residuo residuo) {
        double total = 0;
        for (Entrega e : entregas) {
            if (e.getResiduo() == residuo) { // enum se compara com ==
                total += e.getKg();
            }
        }
        return total;
    }

    public double kgReciclavel() {
        double total = 0;
        for (Entrega e : entregas) {
            if (e.getResiduo().isReciclavel()) {
                total += e.getKg();
            }
        }
        return total;
    }
}
