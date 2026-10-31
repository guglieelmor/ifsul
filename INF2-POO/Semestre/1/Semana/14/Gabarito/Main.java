public class Main {
    public static void main(String[] args) {
        System.out.println("=== Guia das lixeiras ===");
        // values() devolve todas as constantes, na ordem declarada
        for (Residuo r : Residuo.values()) {
            System.out.printf("%-8s | lixeira %-8s | %d pts/kg | %s%n", r,
                    r.getCorDaLixeira(), r.getPontosPorKg(), r.getDica());
        }

        Campanha campanha = new Campanha();

        System.out.println();
        System.out.println("=== Entregas da semana ===");
        campanha.registrar("INF1", "papel", 12.0);
        campanha.registrar("INF1", "Plastico ", 20.0);
        campanha.registrar("INF1", "organico", 21.0);
        campanha.registrar("INF2", "METAL", 30.0);
        campanha.registrar("INF2", "vidro", 30.0);
        campanha.registrar("INF2", "isopor", 3.0);
        campanha.registrar("INF3", " plástico", 6.0);
        campanha.registrar("INF3", "papel", 18.0);
        campanha.registrar("INF3", "rejeito", 5.0);
        campanha.registrar("INF3", "metal", 4.0);
        campanha.registrar("INF3", "vidro", -2.0);

        System.out.println();
        System.out.println("=== Placar das turmas ===");
        String[] turmas = {"INF1", "INF2", "INF3"};
        String campea = turmas[0];
        for (String turma : turmas) {
            double pontos = campanha.pontosDaTurma(turma);
            System.out.printf("%s: %.1f pontos | selo %s%n", turma, pontos, Selo.paraPontos(pontos));
            if (pontos > campanha.pontosDaTurma(campea)) {
                campea = turma;
            }
        }
        System.out.println("Turma campeã: " + campea);

        System.out.println();
        System.out.println("=== Total por resíduo ===");
        for (Residuo r : Residuo.values()) {
            System.out.printf("%-8s %5.1f kg%n", r, campanha.kgDoResiduo(r));
        }
        System.out.printf("Reciclável: %.1f kg%n", campanha.kgReciclavel());
    }
}

/*
 * RESPOSTAS DA PARTE C (uma solução possível)
 *
 * 1) Experimentos:
 *    a) Guardar o texto "PAPEL" numa variável do tipo Residuo:
 *       "incompatible types: String cannot be converted to Residuo".
 *       O enum é um tipo novo: só aceita as suas constantes.
 *    b) new Residuo("rosa", 9): "enum classes may not be instantiated".
 *       Cada constante é criada uma única vez, pelo próprio enum; o
 *       construtor é privado.
 *    c) Constante PILHA (lixeira laranja, 4 pontos) sem mexer no switch:
 *       "the switch expression does not cover all possible input values".
 *       Sem default, o compilador obriga a tratar a constante nova. Com um
 *       default, compilaria e a pilha ganharia uma dica errada sem ninguém ver.
 *    d) Residuo.valueOf("plastico") compila, mas na execução dá
 *       IllegalArgumentException: No enum constant Residuo.plastico.
 *       O valueOf diferencia maiúsculas de minúsculas e não aceita valor que
 *       não existe. Por isso a campanha limpa o texto (trim + toUpperCase) e
 *       procura em values(), devolvendo null quando não acha.
 *    e) METAL.ordinal() = 3 e PAPEL.compareTo(METAL) = -3 (posições 0 e 3).
 *       Movendo METAL para o início, o ordinal vira 0 e o guia muda de ordem;
 *       os pontos não mudam, porque ficam no atributo. Se o sistema tivesse
 *       salvo o ordinal (3 = METAL), os dados antigos passariam a apontar
 *       para outro resíduo. Salve name(), nunca ordinal().
 *    f) Trocando a ordem de BRONZE e OURO no Selo, as três turmas ficam com
 *       BRONZE: o paraPontos percorre values() na ordem declarada e fica com
 *       o último selo alcançado. A regra depende da ordem das constantes;
 *       vale um comentário no enum avisando isso.
 *
 * 2) " plástico" foi recusado porque o trim e o toUpperCase dão "PLÁSTICO",
 *    com acento, e a constante é PLASTICO. Para o Java são textos diferentes.
 *    Constantes seguem a convenção: MAIÚSCULAS e sem acento.
 *
 * 3) Enum ou não?
 *    - tipo de resíduo: enum (lista fixa, conhecida ao escrever o código);
 *    - cor da lixeira: atributo do enum Residuo (anda junto com o resíduo);
 *    - selo: enum (4 níveis fixos);
 *    - turma: String/classe, pois turmas novas surgem todo ano;
 *    - peso da entrega: double, é uma medida e não uma lista;
 *    - dia da coleta: já existe o enum DayOfWeek no java.time.
 *
 * 4) Com constantes static final int (PAPEL = 1, PLASTICO = 2...), o
 *    registrar aceitaria o número 7, que não é resíduo nenhum, e imprimir o
 *    resíduo mostraria "2" em vez de PLASTICO. A cor e os pontos ficariam em
 *    outros lugares (arrays ou ifs), e cada tela repetiria a regra.
 *
 * 5) Com enum, quem usa só pergunta: residuo.isReciclavel(),
 *    residuo.getDica(). Se o orgânico passar a contar como reciclável, muda
 *    um método do enum e todas as telas acompanham.
 */
