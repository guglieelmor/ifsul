package br.edu.ifsul.interclasses.modelo;

// A súmula oficial: qualquer um pode LER, mas só o pacote modelo ESCREVE
public class Sumula {
    private String texto = "";
    private int linhas;

    // Construtor default (sem modificador): só classes do pacote modelo criam súmulas
    Sumula() {
    }

    // default: a Partida (mesmo pacote) anota; o Main (outro pacote) não enxerga
    void anotar(String evento) {
        linhas++;
        texto = texto + linhas + ". " + evento + "\n";
    }

    public String getTexto() {
        return texto;
    }
}
