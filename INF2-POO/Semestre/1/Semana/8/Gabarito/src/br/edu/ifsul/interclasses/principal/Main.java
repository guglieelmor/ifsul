package br.edu.ifsul.interclasses.principal;

// Partida está em outro pacote: precisa de import
import br.edu.ifsul.interclasses.modelo.Partida;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Final do Interclasses ===");
        Partida fin = new Partida("INF2", "INF4");
        mostrar(fin);
        System.out.println("Gol do INF2 antes do apito: " + fin.marcarGol("INF2"));
        System.out.println("Começar sem árbitro: " + fin.iniciar());
        fin.setArbitro("");
        fin.setArbitro("Profa. Marta");
        System.out.println("Começar com árbitra: " + fin.iniciar());

        System.out.println("\n=== O jogo ===");
        System.out.println("Gol do INF2: " + fin.marcarGol("INF2"));
        System.out.println("Gol do INF4: " + fin.marcarGol("INF4"));
        System.out.println("Gol do INF2: " + fin.marcarGol("INF2"));
        System.out.println("Gol do TSI1: " + fin.marcarGol("TSI1"));
        fin.setArbitro("Prof. Jonas");
        System.out.println("VAR: " + fin.anularUltimoGol());
        System.out.println("VAR de novo: " + fin.anularUltimoGol());
        System.out.println("Gol do INF4: " + fin.marcarGol("INF4"));
        System.out.println("Encerrar: " + fin.encerrar());
        System.out.println("Gol do INF2 depois do apito: " + fin.marcarGol("INF2"));
        mostrar(fin);
        System.out.println("Vencedor: " + fin.vencedor());

        System.out.println("\n=== Súmula oficial ===");
        System.out.print(fin.getSumula());

        // Tentativas de fraude:
        // fin.golsCasa = 99;           // NÃO COMPILA: golsCasa has private access in Partida
        // fin.sumula.anotar("W.O.");   // NÃO COMPILA: sumula has private access in Partida
        // new Sumula();                // NÃO COMPILA: Sumula() is not public in Sumula; cannot be accessed from outside package

        System.out.println("\n=== Amistoso ===");
        Partida treino = new Partida("INF4", "INF4");
        mostrar(treino);
        System.out.println("Vencedor do treino: " + treino.vencedor());
    }

    static void mostrar(Partida p) {
        System.out.println(p.getPlacar() + " (" + p.getSituacao() + ")");
    }
}

/*
 * RESPOSTAS (uma solução possível)
 *
 * PARTE A
 *
 * A.1) Partida
 *      atributos (todos) .............. private: ninguém de fora mexe
 *      construtor ..................... public: o Main precisa criar partidas
 *      setArbitro, iniciar, marcarGol,
 *      anularUltimoGol, encerrar ...... public: ações que o Main usa
 *      participa(time) ................ private: detalhe interno
 *      getters, getPlacar, vencedor ... public: leitura liberada
 *    Sumula
 *      texto, linhas .................. private
 *      construtor ..................... default: só o pacote modelo cria
 *      anotar(evento) ................. default: só a Partida (mesmo pacote) escreve
 *      getTexto() ..................... public: qualquer um pode ler
 *
 * A.2) timeCasa/timeVisitante: getter sim, setter não (nunca mudam).
 *      golsCasa/golsVisitante: getter sim, setter não (mudam só por
 *      marcarGol e anularUltimoGol). situacao: getter sim, setter não
 *      (muda por iniciar e encerrar). arbitro: getter sim, setter sim,
 *      com validação. ultimoGol e sumula: sem getter nem setter (a súmula
 *      é lida por getSumula(), que devolve só o texto).
 *
 * A.3) src/br/edu/ifsul/interclasses/modelo/Partida.java
 *      src/br/edu/ifsul/interclasses/modelo/Sumula.java
 *      src/br/edu/ifsul/interclasses/principal/Main.java
 *
 * PARTE C
 *
 * 1) a) "golsCasa has private access in Partida": private só a classe.
 *    b) "participa(String) has private access in Partida": detalhe interno.
 *    c) "Sumula() is not public in Sumula; cannot be accessed from outside
 *       package": o construtor é default, só o pacote modelo usa.
 *    d) "cannot find symbol": Partida está em outro pacote; sem import, o
 *       compilador não sabe qual Partida é.
 *    e) "Partida is not public in br.edu.ifsul.interclasses.modelo; cannot
 *       be accessed from outside package": a classe vira default.
 *    f) "getPlacar() is not public in Partida; cannot be accessed from
 *       outside package": o método vira default.
 *
 * 2) O modificador da classe e o do construtor são independentes. Sumula
 *    é public (todos enxergam o tipo e podem ler o texto), mas o
 *    construtor e anotar() são default: só classes do pacote modelo, como
 *    a Partida, podem usá-los.
 *
 * 3) Porque os gols só podem mudar pelas regras do jogo (partida em
 *    andamento, time que está jogando, um gol por vez). Um setGols(0..30)
 *    validaria o número, mas ainda deixaria qualquer um escrever
 *    setGolsCasa(30) com o jogo 1 x 1. A fraude só some quando a única
 *    porta de entrada é marcarGol().
 *
 * 4) Default. Se fosse public, o Main poderia anotar gols falsos na
 *    súmula. Se fosse private, nem a Partida conseguiria anotar.
 *
 * 5) protected: a filha, mesmo em outro pacote, acessaria os gols. O
 *    risco é que protected também libera o acesso para todas as classes
 *    do pacote modelo, e qualquer classe nova poderia virar filha só para
 *    mexer no placar. Mais seguro: manter private e oferecer à filha um
 *    método protected com regra (por exemplo, registrar pênalti).
 */
