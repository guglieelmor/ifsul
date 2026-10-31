import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Encomenda> encomendas = new ArrayList<>();
        encomendas.add(new Encomenda("Peças de foguete", "Estação Órbita-9", 300, 20));
        encomendas.add(new Encomenda("Carta da vó", "Lua Bolacha", 0.1, 2));
        encomendas.add(new Encomenda("Mudança completa", "Galáxia Distante", 50, 150));
        encomendas.add(new Encomenda("Pizza gigante", "Planeta Calabresa", 1.5, 8));
        encomendas.add(new Encomenda("Piano de cauda", "Nebulosa Sonata", 800, 40));

        // Collections.sort não conhece Encomenda: só confia no Comparable
        Collections.sort(encomendas);
        System.out.println("=== Encomendas por distância ===");
        for (Encomenda e : encomendas) {
            System.out.println(e);
        }

        // Tipo da variável: a interface. Objetos: classes sem parentesco
        PomboEstelar pipo = new PomboEstelar("Pipo");
        NaveCargueira andromeda = new NaveCargueira("NC-7", "Andrômeda");
        List<Entregador> frota = new ArrayList<>();
        frota.add(pipo);
        frota.add(andromeda);
        frota.add(new Teletransportador("Tele-X"));

        System.out.println("\n=== Central de entregas ===");
        for (Encomenda e : encomendas) {
            mostrarOfertas(frota, e);
            Entregador escolhido = escolherMaisBarato(frota, e);
            if (escolhido == null) {
                System.out.println("  -> nenhum entregador aceita (alcance máximo: "
                        + Entregador.ALCANCE_MAXIMO + " anos-luz)");
            } else {
                System.out.println("  -> " + escolhido.entregar(e));
            }
        }

        System.out.println("\n=== Entrega urgente ===");
        Entregador plantao = Entregador.padrao(); // método static da interface
        Encomenda remedio = new Encomenda("Remédio", "Lua Bolacha", 0.2, 2);
        System.out.println(plantao.entregarUrgente(remedio)); // método default

        System.out.println("\n=== Rastreamento ===");
        // O mesmo pipo, agora visto por outro contrato
        List<Rastreavel> rastreaveis = new ArrayList<>();
        rastreaveis.add(pipo);
        rastreaveis.add(andromeda);
        rastreaveis.add((Rastreavel) plantao);
        for (Rastreavel r : rastreaveis) {
            System.out.println(r.localizar());
        }
    }

    // Programe para a interface: estes métodos não sabem que existem pombos ou naves
    static void mostrarOfertas(List<Entregador> frota, Encomenda e) {
        System.out.print(e.getDescricao() + ":");
        String separador = " ";
        for (Entregador ent : frota) {
            if (ent.podeLevar(e)) {
                System.out.printf("%s%s R$ %.2f", separador, ent.getNome(), ent.calcularFrete(e));
            } else {
                System.out.printf("%s%s não leva", separador, ent.getNome());
            }
            separador = " | ";
        }
        System.out.println();
    }

    static Entregador escolherMaisBarato(List<Entregador> frota, Encomenda e) {
        Entregador melhor = null;
        for (Entregador ent : frota) {
            if (ent.podeLevar(e)) {
                if (melhor == null || ent.calcularFrete(e) < melhor.calcularFrete(e)) {
                    melhor = ent;
                }
            }
        }
        return melhor;
    }
}

/*
 * RESPOSTAS (uma solução possível)
 *
 * PARTE A - MODELAGEM
 *
 * A.1) Diagrama (linha cheia = extends; tracejada = implements), em Java:
 *      interface Entregador; interface Rastreavel
 *      abstract class Criatura; abstract class Veiculo implements Rastreavel
 *      class PomboEstelar extends Criatura implements Entregador, Rastreavel
 *      class NaveCargueira extends Veiculo implements Entregador
 *      class Teletransportador implements Entregador
 *      class Encomenda implements Comparable<Encomenda>
 *
 * A.2) PomboEstelar é uma Criatura; sabe entregar e sabe dizer onde está.
 *      NaveCargueira é um Veiculo; sabe entregar (e, por ser veículo,
 *      sabe dizer onde está). Teletransportador não "é" nada da galáxia;
 *      só sabe entregar.
 *
 * A.3) Regra 5 (100 anos-luz) ...... constante na interface Entregador
 *                                     (public static final implícito)
 *      Regra 6 (dentro do alcance) .. método default em Entregador
 *      Regra 7 (entrega urgente) .... método default em Entregador
 *      Regra 8 (plantão) ............ método static em Entregador
 *      Regra 10 (veículo rastreável)  classe abstrata Veiculo que
 *                                     implementa Rastreavel
 *
 * PARTE C - EXPERIMENTOS E PERGUNTAS
 *
 * 1) a) "Entregador is abstract; cannot be instantiated": interface é só
 *       o contrato, não existe objeto "só entregador".
 *    b) "Teletransportador is not abstract and does not override abstract
 *       method entregar(Encomenda) in Entregador": quem assina o contrato
 *       precisa cumprir todos os métodos.
 *    c) "calcularFrete(Encomenda) in Teletransportador cannot implement
 *       calcularFrete(Encomenda) in Entregador ... attempting to assign
 *       weaker access privileges; was public": métodos da interface são
 *       public, e a classe não pode diminuir o acesso.
 *    d) "cannot assign a value to static final variable ALCANCE_MAXIMO":
 *       variável em interface é constante.
 *    e) "cannot find symbol": o tipo da variável (Entregador) decide o
 *       que pode ser chamado, e ele não promete localizar(), mesmo que o
 *       objeto seja uma nave.
 *    f) "cannot find symbol": método static pertence à interface e não é
 *       herdado; chame Entregador.padrao().
 *
 * 2) Foi preciso um cast (Rastreavel), porque a variável do plantão tem o
 *    tipo Entregador, que não promete localizar(). Funciona porque o
 *    objeto real é um PomboEstelar. Se o plantão fosse um
 *    Teletransportador, compilaria, mas daria ClassCastException ao
 *    executar.
 *
 * 3) Pombo, nave e teletransportador não têm estado nem identidade em
 *    comum: um é Criatura, outro é Veiculo, outro não tem superclasse.
 *    Eles só dividem um comportamento: entregar. Além disso, Java só
 *    permite um extends, que o pombo e a nave já usam.
 *
 * 4) Nenhuma: mostrarOfertas() e escolherMaisBarato() só conhecem
 *    Entregador. Basta criar a classe nova e adicioná-la à frota.
 */
