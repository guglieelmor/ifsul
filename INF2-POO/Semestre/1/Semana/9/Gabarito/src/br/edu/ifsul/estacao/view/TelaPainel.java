package br.edu.ifsul.estacao.view;

import br.edu.ifsul.estacao.controller.EstacaoController;
import br.edu.ifsul.estacao.model.*; // curinga: Leitura e Alerta do model

// VIEW: só mostra; pede tudo ao controller
public class TelaPainel {

    public void mostrar(EstacaoController estacao) {
        System.out.println("=== Estação Meteorológica do Câmpus ===");
        System.out.println("Hora  | Temp (°C) | Umidade | Chuva (mm)");
        for (Leitura l : estacao.getLeituras()) {
            System.out.printf("%s | %9.1f | %6d%% | %10.1f%n",
                    l.getHora(), l.getTemperatura(), l.getUmidade(), l.getChuva());
        }
        System.out.println("\n=== Resumo do dia ===");
        System.out.printf("Temperatura média: %.2f °C%n", estacao.mediaTemperatura());
        System.out.printf("Chuva total: %.1f mm%n", estacao.chuvaTotal());
        Leitura q = estacao.maisQuente();
        System.out.printf("Mais quente: %s (%.1f °C)%n", q.getHora(), q.getTemperatura());
        System.out.println("Alertas emitidos: " + estacao.getAlertas().size());
    }
}
