package br.edu.ifsul.interclasses.modelo;

public class Partida {
    // Estado escondido: ninguém de fora mexe no placar
    private String timeCasa;
    private String timeVisitante;
    private int golsCasa;
    private int golsVisitante;
    private String situacao;      // "agendada", "em andamento" ou "encerrada"
    private String arbitro;
    private String ultimoGol;     // time que marcou o último gol (para o VAR)
    private Sumula sumula;

    // Dados obrigatórios entram pelo construtor
    public Partida(String timeCasa, String timeVisitante) {
        this.timeCasa = timeCasa;
        if (timeVisitante.equals(timeCasa)) {
            System.out.println("Times iguais: visitante fica \"A definir\"");
            timeVisitante = "A definir";
        }
        this.timeVisitante = timeVisitante;
        this.situacao = "agendada";
        this.sumula = new Sumula(); // pode: mesmo pacote
    }

    // Setter com validação: árbitro não vazio, e só antes do apito inicial
    public void setArbitro(String arbitro) {
        if (arbitro == null || arbitro.isEmpty() || !situacao.equals("agendada")) {
            System.out.println("Árbitro recusado: " + (this.arbitro == null ? "ninguém" : this.arbitro) + " continua");
            return;
        }
        this.arbitro = arbitro;
    }

    public boolean iniciar() {
        if (!situacao.equals("agendada") || arbitro == null) {
            return false;
        }
        situacao = "em andamento";
        sumula.anotar("Início de jogo, árbitro(a) " + arbitro);
        return true;
    }

    // Os gols não têm setter: só mudam por esta ação, com as regras do jogo
    public boolean marcarGol(String time) {
        if (!situacao.equals("em andamento") || !participa(time)) {
            return false;
        }
        if (time.equals(timeCasa)) {
            golsCasa++;
        } else {
            golsVisitante++;
        }
        ultimoGol = time;
        sumula.anotar("Gol do " + time + " (" + getPlacar() + ")");
        return true;
    }

    public boolean anularUltimoGol() {
        if (!situacao.equals("em andamento") || ultimoGol == null) {
            return false;
        }
        if (ultimoGol.equals(timeCasa)) {
            golsCasa--;
        } else {
            golsVisitante--;
        }
        sumula.anotar("VAR anulou o gol do " + ultimoGol + " (" + getPlacar() + ")");
        ultimoGol = null;
        return true;
    }

    public boolean encerrar() {
        if (!situacao.equals("em andamento")) {
            return false;
        }
        situacao = "encerrada";
        sumula.anotar("Fim de jogo (" + getPlacar() + ")");
        return true;
    }

    // private: detalhe interno, só a própria classe usa
    private boolean participa(String time) {
        return time.equals(timeCasa) || time.equals(timeVisitante);
    }

    public String vencedor() {
        if (!situacao.equals("encerrada")) {
            return "jogo não terminou";
        }
        if (golsCasa > golsVisitante) {
            return timeCasa;
        }
        if (golsVisitante > golsCasa) {
            return timeVisitante;
        }
        return "empate";
    }

    // Getters: leitura liberada, escrita não
    public String getPlacar() {
        return timeCasa + " " + golsCasa + " x " + golsVisitante + " " + timeVisitante;
    }

    public String getTimeCasa() {
        return timeCasa;
    }

    public String getTimeVisitante() {
        return timeVisitante;
    }

    public int getGolsCasa() {
        return golsCasa;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getArbitro() {
        return arbitro;
    }

    public String getSumula() {
        return sumula.getTexto();
    }
}
