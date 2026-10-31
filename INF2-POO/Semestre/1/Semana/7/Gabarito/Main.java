public class Main {
    public static void main(String[] args) {
        System.out.println("=== Abertura do forno ===");
        // Cada new: aloca, zera, roda o construtor e devolve o endereço
        Pizza p1 = new Pizza();
        Pizza p2 = new Pizza("Ana", "Calabresa");
        Pizza p3 = new Pizza("Bruno", "Quatro Queijos", 'G');
        Pizza p4 = new Pizza("Carla", "Portuguesa", 'P', true);
        Pizza p5 = new Pizza("Dani", "Frango", 'X');
        System.out.println(p1.descrever());
        System.out.println(p2.descrever());
        System.out.println(p3.descrever());
        System.out.println(p4.descrever());
        System.out.println(p5.descrever());

        System.out.println("\n=== O pedido trocado ===");
        // O Edu pediu Calabresa, mas os argumentos vieram na ordem errada
        Pizza p6 = new Pizza("Calabresa", "Edu");
        System.out.println(p6.descrever());

        System.out.println("\n=== Duas margheritas ===");
        Pizza m1 = new Pizza();
        Pizza m2 = new Pizza();
        System.out.println("m1 == m2? " + (m1 == m2));
        m1.comerFatia();
        m1.comerFatia();
        System.out.println("Depois de comer 2 fatias da m1:");
        System.out.println("m1: " + m1.getFatias() + " fatias | m2: " + m2.getFatias() + " fatias");

        System.out.println("\n=== O motoboy esfomeado ===");
        // Não cria pizza nova: copia o endereço da pizza do Bruno
        Pizza daEntrega = p3;
        daEntrega.comerFatia();
        daEntrega.comerFatia();
        System.out.println("p3 == daEntrega? " + (p3 == daEntrega));
        System.out.println("Pizza que chegou ao Bruno: " + p3.descrever());

        System.out.println("\n=== A Ana mudou de ideia ===");
        // A Calabresa antiga fica sem referência: vira trabalho do Garbage Collector
        p2 = new Pizza("Ana", "Margherita");
        System.out.println(p2.descrever());

        System.out.println("\n=== Rodízio ===");
        String[] sabores = {"Atum", "Milho", "Chocolate"};
        for (int i = 0; i < sabores.length; i++) {
            Pizza p = new Pizza("Rodízio", sabores[i], 'P');
            System.out.println(p.descrever());
        } // aqui a variável p deixa de existir
    }
}

/*
 * RESPOSTAS DA PARTE C (uma solução possível)
 *
 * 1) Experimentos:
 *    a) As descrições ficaram como "null M para Balcão | 0 fatias | R$ 42,00"
 *       e a da Carla perdeu a borda (R$ 30,00). São os valores padrão que o
 *       new coloca no passo "zera": String -> null, int -> 0,
 *       boolean -> false. O tamanho apareceu certo porque continuou sendo
 *       guardado.
 *    b) "no suitable constructor found for Pizza(char)": nenhum construtor
 *       recebe só um char. O Java escolhe o construtor pela quantidade,
 *       pelo tipo e pela ordem dos argumentos.
 *    c) Com void, o construtor vira um método comum chamado Pizza. O new
 *       com 4 argumentos não acha construtor ("no suitable constructor
 *       found for Pizza(String,String,char,boolean)"), e os outros
 *       construtores também quebram, porque o this(...) deles chamava
 *       justamente esse construtor.
 *    d) "variable px might not have been initialized": variável local não
 *       tem valor padrão, e o compilador percebe que ela nunca recebeu
 *       nada.
 *    e) Compila, mas ao rodar dá NullPointerException: null é um valor
 *       válido para o compilador, só que não aponta para nenhuma pizza.
 *
 * 2) new Pizza("Carla", "Portuguesa", 'P', true):
 *    1. aloca espaço no heap para uma pizza;
 *    2. zera os atributos (null, 0, false, '\u0000');
 *    3. roda o construtor completo: guarda cliente e sabor, valida o
 *       tamanho, guarda a borda e calcula 4 fatias;
 *    4. devolve o endereço, que o = guarda em p4.
 *    new Pizza(): roda Pizza() -> this("Balcão", "Margherita") ->
 *    Pizza(String, String) -> this(..., 'M') -> Pizza(String, String, char)
 *    -> this(..., false) -> construtor completo. Os quatro rodam, mas só
 *    um objeto é criado.
 *
 * 3) Os dois parâmetros são String: para o compilador, ("Calabresa", "Edu")
 *    e ("Edu", "Calabresa") são igualmente válidos. Ele confere os tipos,
 *    não o significado. Para evitar: seguir a ordem dos parâmetros
 *    (cliente, sabor), usar nomes claros e conferir a descrição do pedido.
 *
 * 4) m1 e m2 vieram de dois new: são dois objetos, cada um com as suas
 *    fatias (m1 == m2 é false). O motoboy não fez new: daEntrega = p3
 *    copiou o endereço, então as duas variáveis apontam para a mesma
 *    pizza.
 *
 *      STACK                HEAP
 *      p3        --------> Pizza { Quatro Queijos, G, Bruno, 8 fatias }
 *      daEntrega --------^
 *
 * 5) new executados: 5 (B.1) + 1 (B.2) + 2 (B.3) + 1 (B.5) + 3 (B.6) = 12.
 *    Com referência no fim: p1, p2 (a Margherita nova), p3, p4, p5, p6,
 *    m1 e m2 = 8 objetos (daEntrega aponta para o mesmo de p3).
 *    Sem referência: 4. A Calabresa antiga da Ana (p2 passou a apontar
 *    para a pizza nova) e as 3 pizzas do rodízio (a variável p só existe
 *    dentro do laço e cada volta aponta para uma pizza nova). O Garbage
 *    Collector pode remover essas 4.
 */
