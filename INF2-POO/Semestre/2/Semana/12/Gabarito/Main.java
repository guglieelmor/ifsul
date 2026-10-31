import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Guerreiro tora = new Guerreiro("Tora", 5);
        Maga lyra = new Maga("Lyra", 4);
        Ladino sombra = new Ladino("Sombra", 3);
        Bardo dede = new Bardo("Dedé", 2);
        Guerreiro novato = new Guerreiro("Novato", 25); // nível inválido vira 1

        // A mesma guilda, vista por três "lentes" diferentes
        List<Aventureiro> guilda = new ArrayList<>(List.of(tora, lyra, sombra, dede, novato));
        List<Curador> curandeiros = new ArrayList<>(List.of(lyra, dede));

        System.out.println("=== A guilda ===");
        mostrarGuilda(guilda);

        System.out.println("\n=== Dragão das Cinzas (120 de vida) ===");
        int vidaDragao = 120;
        vidaDragao = turnoDeAtaque(guilda, vidaDragao);
        System.out.println("O dragão cospe fogo: 35 de dano em todos!");
        for (Aventureiro a : guilda) {
            a.receberDano(35);
        }
        mostrarGuilda(guilda);

        System.out.println("\n=== Curandeiros ===");
        curarTodos(curandeiros, novato);
        System.out.println(novato.status());

        System.out.println("\n=== Mercado da guilda (orçamento: 70 moedas) ===");
        // Itens e um aventureiro na mesma lista: o contrato atravessa hierarquias
        List<Negociavel> mercado = List.of(new Pocao("Poção de vida"), new Espada("Espada Rúnica"), sombra, dede);
        for (Negociavel n : mercado) {
            System.out.println(n.getNome() + ": " + n.apresentacao()
                    + " | cabe no orçamento? " + Negociavel.cabeNoOrcamento(70, n));
        }

        System.out.println("\n=== Mochila ===");
        List<Item> mochila = List.of(new Pocao("Poção de vida"), new Espada("Espada Rúnica"),
                new MapaDoTesouro("Caverna Esquecida"));
        double peso = 0;
        for (Item i : mochila) {
            System.out.println(i.descrever());
            peso += i.getPesoKg();
        }
        System.out.printf("Peso total: %.1f kg%n", peso);

        System.out.println("\n=== Chegou a paladina ===");
        // 1 classe nova; turnoDeAtaque e curarTodos não mudam nenhuma linha
        Paladino iris = new Paladino("Íris", 3);
        guilda.add(iris);
        curandeiros.add(iris);
        System.out.println(iris.status());
        vidaDragao = turnoDeAtaque(guilda, vidaDragao);
        curarTodos(curandeiros, novato);
        System.out.println(novato.status());

        System.out.println("\n=== Descanso na taverna ===");
        for (Aventureiro a : guilda) {
            a.descansar();
        }
        mostrarGuilda(guilda);
    }

    static void mostrarGuilda(List<Aventureiro> guilda) {
        for (Aventureiro a : guilda) {
            System.out.println(a.status());
        }
    }

    // Polimorfismo pela classe abstrata: cada um golpeia do seu jeito
    static int turnoDeAtaque(List<Aventureiro> guilda, int vidaDragao) {
        int total = 0;
        for (Aventureiro a : guilda) {
            int golpe = a.calcularGolpe();
            System.out.println(a.getNome() + " golpeia: " + golpe + " de dano");
            total += golpe;
        }
        vidaDragao = Math.max(0, vidaDragao - total);
        System.out.println("Dano total: " + total + " | vida do dragão: " + vidaDragao);
        return vidaDragao;
    }

    // Polimorfismo pelo contrato: só conhece Curador
    static void curarTodos(List<Curador> curandeiros, Aventureiro ferido) {
        for (Curador c : curandeiros) {
            int cura = c.curar(ferido);
            System.out.println(c.getNome() + " (" + c.apresentacao() + ") cura " + ferido.getNome() + ": +" + cura);
        }
    }

}

/*
 * RESPOSTAS (uma solução possível)
 *
 * PARTE A - MODELAGEM
 *
 * A.1) Diagrama (linha cheia = extends; tracejada = implements), em Java:
 *      abstract class Aventureiro
 *      class Guerreiro extends Aventureiro
 *      class Maga extends Aventureiro implements Curador
 *      class Ladino extends Aventureiro implements Negociavel
 *      class Bardo extends Aventureiro implements Curador, Negociavel
 *      abstract class Item
 *      class Pocao extends Item implements Negociavel
 *      class Espada extends Item implements Negociavel
 *      class MapaDoTesouro extends Item
 *      interface Curador; interface Negociavel
 *      (B.7) class Paladino extends Guerreiro implements Curador
 *
 * A.2) 1  nome, nível e vida: atributos de Aventureiro (abstrata)
 *      2  validação do nível: construtor de Aventureiro
 *      3  vida máxima: método concreto de Aventureiro
 *      4  dano, cura, descanso e status: métodos concretos de Aventureiro
 *      5  golpe: método abstrato em Aventureiro, sobrescrito em cada classe
 *      6  nome e peso: atributos de Item; efeito: método abstrato de Item;
 *         descrever(): método concreto de Item
 *      7  limite de 30: constante em Curador; curar() e apresentacao():
 *         métodos default em Curador; poder de cura: método abstrato
 *      8  valor: método abstrato em Negociavel; taxa e apresentacao():
 *         default; cabeNoOrcamento(): static em Negociavel
 *      9  getNome(): método abstrato nos dois contratos, cumprido pelo
 *         getNome() herdado de Aventureiro e de Item
 *      10 apresentacao() sobrescrita no Bardo, chamando
 *         Curador.super.apresentacao() e Negociavel.super.apresentacao()
 *
 * A.3) "A maga é uma aventureira" -> extends Aventureiro.
 *      "A poção é capaz de ser negociada" -> implements Negociavel.
 *      "O mapa é negociável?" Não: não está à venda, então não assina o
 *      contrato (assinar e deixar o método vazio seria um contrato falso).
 *
 * PARTE C - EXPERIMENTOS E PERGUNTAS
 *
 * 1) a) "Aventureiro is abstract; cannot be instantiated": classe abstrata
 *       é só molde; todo aventureiro é de uma classe concreta.
 *    b) "incompatible types": o mapa é um Item, mas não é Negociavel.
 *    c) "types Curador and Negociavel are incompatible; class Bardo inherits
 *       unrelated defaults for apresentacao()": com dois defaults iguais,
 *       o Java não escolhe sozinho; o Bardo precisa sobrescrever.
 *    d) "cannot find symbol": o tipo da variável (Negociavel) decide o que
 *       pode ser chamado, e o contrato não promete calcularGolpe().
 *    e) "'{' expected": uma classe só tem um extends.
 *    f) "= expected": em interface toda variável é constante (precisa de
 *       valor e não muda). Estado de cada objeto mora numa classe.
 *
 * 2) 01 Abstrata: todo monstro é um Monstro (vida + ataque abstrato).
 *    02 Interface: baú, porta e prisioneiro não têm parentesco, mas são
 *       capazes de ser destrancados.
 *    03 Os dois: todo animal é uma Montaria (abstrata); só o grifo é capaz
 *       de voar (interface Voador).
 *    04 Interface: feitiço, poção e pergaminho são capazes de ser usados
 *       em batalha, sem identidade em comum.
 *    05 Abstrata: toda construção é uma Construcao (nível, custo).
 *    06 Os dois: Aventureiro (abstrata) + interface VisaoNoEscuro só para
 *       elfos e anões.
 *
 * 3) Porque só alguns aventureiros curam: colocar curar() em Aventureiro
 *    daria cura ao guerreiro e ao ladino. Um método de cura vazio no
 *    ladino seria um contrato falso: ele diria que cura e não curaria.
 *
 * 4) A paladina é uma guerreira (golpeia como guerreiro), não uma maga:
 *    herdar de Maga traria o golpe e o poder de cura errados. Foi 1 classe
 *    nova (Paladino extends Guerreiro implements Curador) e 0 linhas
 *    alteradas no turno de ataque e na cura.
 *
 * 5) Poção e espada já gastaram o extends com Item, e o ladino e o bardo
 *    com Aventureiro. Uma classe abstrata "negociável" exigiria um segundo
 *    extends. A interface atravessa as duas hierarquias.
 *
 * 6) Os dois contratos declaram getNome(). Aventureiro e Item já têm esse
 *    método, então as classes concretas cumprem o contrato com o método
 *    herdado, sem escrever nada novo.
 */
