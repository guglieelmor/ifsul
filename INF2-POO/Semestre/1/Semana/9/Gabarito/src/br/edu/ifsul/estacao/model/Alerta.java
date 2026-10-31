package br.edu.ifsul.estacao.model;

// MODEL: os dados de um alerta (existe outra classe Alerta, a tela, no pacote view)
public class Alerta {
    private String nivel;     // AMARELO ou VERMELHO
    private String hora;
    private String mensagem;

    public Alerta(String nivel, String hora, String mensagem) {
        this.nivel = nivel;
        this.hora = hora;
        this.mensagem = mensagem;
    }

    public String getNivel() {
        return nivel;
    }

    public String getHora() {
        return hora;
    }

    public String getMensagem() {
        return mensagem;
    }
}
