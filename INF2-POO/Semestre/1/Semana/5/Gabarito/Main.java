public class Main {
    public static void main(String[] args) {
        // Três new = três objetos independentes no heap
        Bichinho pipoca = new Bichinho("Pipoca", "capivara");
        Bichinho faisca = new Bichinho("Faísca", "gato");
        Bichinho ovo = new Bichinho(); // construtor sem parâmetros

        System.out.println("=== Dia da adoção ===");
        System.out.println(pipoca.obterInfo());
        System.out.println(faisca.obterInfo());
        System.out.println(ovo.obterInfo());

        System.out.println("\n=== Tarde no pátio ===");
        pipoca.alimentar();
        System.out.println("Ana brinca com Pipoca: " + pipoca.brincar());
        for (int i = 1; i <= 4; i++) {
            System.out.println("Bruno brinca com Faísca (" + i + "): " + faisca.brincar());
        }
        System.out.println(pipoca.obterInfo());
        System.out.println(faisca.obterInfo());

        System.out.println("\n=== A Carla \"adota\" um bichinho ===");
        // c2 = c1: copia a REFERÊNCIA, não cria bichinho novo
        Bichinho bichinhoDaCarla = pipoca;
        System.out.println("Carla brinca: " + bichinhoDaCarla.brincar());
        System.out.println("Carla brinca: " + bichinhoDaCarla.brincar());
        System.out.println("Bichinho da Carla: " + bichinhoDaCarla.obterInfo());
        System.out.println("Bichinho da Ana:   " + pipoca.obterInfo());
        System.out.println("pipoca == bichinhoDaCarla? " + (pipoca == bichinhoDaCarla));

        System.out.println("\n=== A Carla ganha o próprio bichinho ===");
        // Agora sim um new: mesmo nome e espécie, mas OUTRO objeto
        bichinhoDaCarla = new Bichinho("Pipoca", "capivara");
        System.out.println("Bichinho da Carla: " + bichinhoDaCarla.obterInfo());
        System.out.println("Bichinho da Ana:   " + pipoca.obterInfo());
        System.out.println("pipoca == bichinhoDaCarla? " + (pipoca == bichinhoDaCarla));

        System.out.println("\n=== Noite ===");
        pipoca.dormir();
        faisca.dormir();
        System.out.println(pipoca.obterInfo());
        System.out.println(faisca.obterInfo());
        System.out.println(bichinhoDaCarla.obterInfo());
        System.out.println(ovo.obterInfo());

        // Bichinho fantasma = null;
        // fantasma.brincar();  // NullPointerException: não existe objeto
    }
}

/*
 * RESPOSTAS DA PARTE C
 *
 * 1) Classe ou objeto?
 *    01 Bichinho .......................................... Classe
 *    02 a capivara Pipoca da Ana, com fome 8 .............. Objeto de Bichinho
 *    03 Especie ........................................... Classe
 *    04 o gato Faísca, no dia 1 ........................... Objeto de Bichinho
 *    05 Bichinho.java ..................................... arquivo da classe
 *       (o código-fonte, não um objeto)
 *
 * 2) Abstração: entram fome, energia, nome e espécie (mudam alguma regra
 *    ou aparecem na tela). Ficam de fora a cor dos olhos, a data em que o
 *    desenho foi feito e o tamanho da imagem em KB: não mudam nenhuma regra
 *    do jogo. Para um app que DESENHA o bichinho, a cor dos olhos entraria.
 *
 * 3) Memória logo depois de "Bichinho bichinhoDaCarla = pipoca;":
 *
 *      STACK                 HEAP
 *      pipoca          ---> @1 Bichinho { Pipoca, capivara }
 *      bichinhoDaCarla ---^
 *      faisca          ---> @2 Bichinho { Faísca, gato }
 *      ovo             ---> @3 Bichinho { Sem nome, ovo }
 *
 *    4 variáveis, 3 objetos. Quando a Carla brincou, o bichinho da Ana
 *    cansou porque as duas variáveis apontam para o MESMO objeto.
 *
 * 4) Depois do novo new, pipoca == bichinhoDaCarla dá false mesmo com
 *    nome e espécie iguais: == compara as referências (os endereços), e
 *    agora são dois objetos diferentes no heap.
 *
 * 5) fantasma vale null: não aponta para nenhum bichinho. Chamar
 *    fantasma.brincar() gera NullPointerException ao rodar (o programa
 *    compila, mas para com erro).
 *
 * 6) Com "public void Bichinho(String nome, String especie)", o void
 *    transforma o construtor num método comum. O new Bichinho("Pipoca",
 *    "capivara") deixa de compilar, porque o único construtor que sobra
 *    é o sem parâmetros (e, se ele também não existisse, o Java criaria
 *    o vazio e os atributos nasceriam null e 0).
 *
 * 7) Se, no fim do main, fizermos pipoca = null, o primeiro objeto Pipoca
 *    (@1) fica sem nenhuma referência: bichinhoDaCarla já aponta para o
 *    objeto novo. Então o Garbage Collector pode liberar essa memória.
 */
