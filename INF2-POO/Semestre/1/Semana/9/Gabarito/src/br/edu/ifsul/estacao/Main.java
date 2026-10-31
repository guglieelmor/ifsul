package br.edu.ifsul.estacao;

import br.edu.ifsul.estacao.controller.EstacaoController;
import br.edu.ifsul.estacao.model.Alerta;
import br.edu.ifsul.estacao.view.TelaPainel;

// Pacote raiz: só inicia o sistema
public class Main {
    public static void main(String[] args) {
        EstacaoController estacao = new EstacaoController();
        estacao.registrar("06h00", 2.5, 92, 0.0);
        estacao.registrar("09h00", 12.0, 75, 0.0);
        estacao.registrar("12h00", 24.5, 40, 0.0);
        estacao.registrar("15h00", 36.0, 28, 0.0);
        estacao.registrar("18h00", 22.0, 85, 42.0);
        estacao.registrar("21h00", 15.5, 90, 3.5);

        new TelaPainel().mostrar(estacao);

        System.out.println("\n=== Alertas ===");
        // Duas classes Alerta: a do model foi importada; a da view vai com o nome completo
        br.edu.ifsul.estacao.view.Alerta tela = new br.edu.ifsul.estacao.view.Alerta();
        for (Alerta a : estacao.getAlertas()) {
            tela.piscar(a);
        }
    }
}

/*
 * RESPOSTAS (uma solução possível)
 *
 * PARTE A
 *
 * A.1) Leitura ............ br.edu.ifsul.estacao.model (dados)
 *      Alerta (dados) ..... br.edu.ifsul.estacao.model (dados)
 *      Alerta (tela) ...... br.edu.ifsul.estacao.view (o que aparece)
 *      RegrasDeAlerta ..... br.edu.ifsul.estacao.controller (regra, usada
 *                           só pelo EstacaoController; classe sem public)
 *      EstacaoController .. br.edu.ifsul.estacao.controller (aplica regras)
 *      TelaPainel ......... br.edu.ifsul.estacao.view (o que aparece)
 *      Main ............... br.edu.ifsul.estacao (raiz: só inicia)
 *
 * A.2) src/br/edu/ifsul/estacao/Main.java
 *      src/br/edu/ifsul/estacao/model/Leitura.java
 *      src/br/edu/ifsul/estacao/model/Alerta.java
 *      src/br/edu/ifsul/estacao/controller/RegrasDeAlerta.java
 *      src/br/edu/ifsul/estacao/controller/EstacaoController.java
 *      src/br/edu/ifsul/estacao/view/TelaPainel.java
 *      src/br/edu/ifsul/estacao/view/Alerta.java
 *
 * A.3) RegrasDeAlerta -> Leitura, Alerta (model): import
 *      EstacaoController -> ArrayList (java.util), Leitura, Alerta: import;
 *                           RegrasDeAlerta: mesmo pacote, sem import
 *      TelaPainel -> EstacaoController, Leitura: import
 *      Alerta (view) -> Alerta (model): nome completo (mesmo nome simples)
 *      Main -> EstacaoController, TelaPainel, Alerta (model): import;
 *              Alerta (view): nome completo. String: java.lang, sem import
 *
 * A.4) br.edu.ifsul.estacao.controller ... válido
 *      br.edu.ifsul.Estacao.View ......... inválido: maiúsculas
 *                                          -> br.edu.ifsul.estacao.view
 *      br.edu.ifsul.estacao.previsão ..... inválido: acento
 *                                          -> br.edu.ifsul.estacao.previsao
 *      ifsul.edu.br.estacao.model ........ inválido: domínio não invertido
 *                                          -> br.edu.ifsul.estacao.model
 *      br.edu.ifsul.estacao.sensor-chuva . inválido: hífen
 *                                          -> br.edu.ifsul.estacao.sensorchuva
 *      br.edu.ifsul.estacao.3dias ........ inválido: começa com número
 *                                          -> br.edu.ifsul.estacao.tresdias
 *
 * PARTE C
 *
 * 1) a) "cannot find symbol": TelaPainel está em outro pacote e não foi
 *       importada.
 *    b) "RegrasDeAlerta is not public in br.edu.ifsul.estacao.controller;
 *       cannot be accessed from outside package": classe default.
 *    c) "cannot find symbol" (Leitura): o curinga não traz sub-pacotes.
 *    d) "a type with the same simple name is already defined by the
 *       single-type-import of Alerta": dois imports com o mesmo nome curto.
 *    e) "temperatura has private access in Leitura": a view só lê pelos
 *       getters (baixo acoplamento).
 *
 * 2) Para o Java, br.edu.ifsul.estacao e br.edu.ifsul.estacao.model são
 *    pacotes diferentes: o * traz só as classes do próprio pacote raiz
 *    (aqui, só a Main), não as dos sub-pacotes.
 *
 * 3) A do model foi importada; a da view foi escrita com o nome completo
 *    (br.edu.ifsul.estacao.view.Alerta). Outro jeito: importar a da view
 *    e escrever a do model com o nome completo, ou usar o nome completo
 *    nas duas.
 *
 * 4) Previsao ............ br.edu.ifsul.estacao.model
 *    TelaPrevisao ........ br.edu.ifsul.estacao.view
 *    PrevisaoController .. br.edu.ifsul.estacao.controller
 *    ConversorFahrenheit . br.edu.ifsul.estacao.view, sem public (só as
 *                          telas usam)
 *    Sensor .............. br.edu.ifsul.estacao.model
 *
 * 5) Por funcionalidade:
 *      br.edu.ifsul.estacao.leitura: Leitura, TelaPainel, EstacaoController
 *      br.edu.ifsul.estacao.alerta: Alerta (dados), TelaAlerta e
 *        RegrasDeAlerta. Aqui as duas classes Alerta cairiam no MESMO
 *        pacote, então a tela precisaria de outro nome.
 *      br.edu.ifsul.estacao.previsao: Previsao, TelaPrevisao,
 *        PrevisaoController
 *    Mudar a regra de ar seco: 1 pacote nas duas organizações (controller
 *    por camada; alerta por funcionalidade), porque a regra está isolada
 *    em RegrasDeAlerta. Se a mudança também alterasse a tela de alerta,
 *    seriam 2 pacotes por camada (controller e view) e 1 por
 *    funcionalidade.
 */
