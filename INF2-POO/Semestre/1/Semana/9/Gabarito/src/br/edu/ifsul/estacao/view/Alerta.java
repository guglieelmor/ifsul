package br.edu.ifsul.estacao.view;

// VIEW: a tela que pisca. Tem o mesmo nome da classe de dados do model,
// então o tipo do model é escrito com o nome completo
public class Alerta {

    public void piscar(br.edu.ifsul.estacao.model.Alerta dados) {
        System.out.println("[*** " + dados.getNivel() + " ***] " + dados.getHora() + " - " + dados.getMensagem());
    }
}
