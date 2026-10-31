package br.edu.ifsul.estacao.controller;

import java.util.ArrayList;

import br.edu.ifsul.estacao.model.Alerta;
import br.edu.ifsul.estacao.model.Leitura;

// CONTROLLER: recebe as medições, aplica as regras e atualiza o model
public class EstacaoController {
    private ArrayList<Leitura> leituras = new ArrayList<>();
    private ArrayList<Alerta> alertas = new ArrayList<>();
    private RegrasDeAlerta regras = new RegrasDeAlerta(); // mesmo pacote: pode

    public void registrar(String hora, double temperatura, int umidade, double chuva) {
        Leitura l = new Leitura(hora, temperatura, umidade, chuva);
        leituras.add(l);
        Alerta a = regras.verificar(l);
        if (a != null) {
            alertas.add(a);
        }
    }

    public double mediaTemperatura() {
        double soma = 0;
        for (Leitura l : leituras) {
            soma += l.getTemperatura();
        }
        return leituras.isEmpty() ? 0 : soma / leituras.size();
    }

    public double chuvaTotal() {
        double total = 0;
        for (Leitura l : leituras) {
            total += l.getChuva();
        }
        return total;
    }

    public Leitura maisQuente() {
        Leitura maior = null;
        for (Leitura l : leituras) {
            if (maior == null || l.getTemperatura() > maior.getTemperatura()) {
                maior = l;
            }
        }
        return maior;
    }

    // Devolve cópias das listas: a view lê, mas não mexe nas originais
    public ArrayList<Leitura> getLeituras() {
        return new ArrayList<>(leituras);
    }

    public ArrayList<Alerta> getAlertas() {
        return new ArrayList<>(alertas);
    }
}
