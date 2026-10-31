package br.edu.ifsul.estacao.controller;

import br.edu.ifsul.estacao.model.Alerta;
import br.edu.ifsul.estacao.model.Leitura;

// Sem public (default): só o pacote controller usa as regras
class RegrasDeAlerta {

    // Devolve o alerta mais grave da leitura, ou null se estiver tudo bem
    Alerta verificar(Leitura l) {
        if (l.getChuva() >= 30) {
            return new Alerta("VERMELHO", l.getHora(), "Temporal");
        }
        if (l.getTemperatura() >= 35) {
            return new Alerta("VERMELHO", l.getHora(), "Calor extremo");
        }
        if (l.getTemperatura() <= 3) {
            return new Alerta("AMARELO", l.getHora(), "Risco de geada");
        }
        if (l.getUmidade() <= 30) {
            return new Alerta("AMARELO", l.getHora(), "Ar seco");
        }
        return null;
    }
}
