package br.edu.ifsul.estacao.model;

// MODEL: os dados de uma medição
public class Leitura {
    private String hora;
    private double temperatura; // °C
    private int umidade;        // %
    private double chuva;       // mm

    public Leitura(String hora, double temperatura, int umidade, double chuva) {
        this.hora = hora;
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.chuva = chuva;
    }

    public String getHora() {
        return hora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public int getUmidade() {
        return umidade;
    }

    public double getChuva() {
        return chuva;
    }
}
