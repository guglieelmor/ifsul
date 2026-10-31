public class Main {
    // O pacote da manhã: 1 resma = 500 folhas
    private static final int FOLHAS_DE_UMA_RESMA = 500;

    public static void main(String[] args) {
        Reprografia reprografia = new Reprografia(FOLHAS_DE_UMA_RESMA);

        System.out.println("=== Atendimentos do dia ===");

        TrabalhoDeImpressao apostilaDaAna = new TrabalhoDeImpressao("Ana", 40, Cor.PRETO_E_BRANCO);
        apostilaDaAna.marcarFrenteEVerso();
        reprografia.atenderEmDinheiro(apostilaDaAna, 20.00);

        TrabalhoDeImpressao provasDoBruno = new TrabalhoDeImpressao("Bruno", 100, Cor.PRETO_E_BRANCO);
        reprografia.atenderNoCartao(provasDoBruno);

        TrabalhoDeImpressao cartazesDaCarla = new TrabalhoDeImpressao("Carla", 12, Cor.COLORIDA);
        reprografia.atenderEmDinheiro(cartazesDaCarla, 10.00);

        TrabalhoDeImpressao livroDoDiego = new TrabalhoDeImpressao("Diego", 350, Cor.PRETO_E_BRANCO);
        reprografia.atenderNoCartao(livroDoDiego);

        TrabalhoDeImpressao relatorioDaElisa = new TrabalhoDeImpressao("Elisa", 150, Cor.COLORIDA);
        relatorioDaElisa.marcarFrenteEVerso();
        reprografia.atenderNoCartao(relatorioDaElisa);

        TrabalhoDeImpressao listasDoFelipe = new TrabalhoDeImpressao("Felipe", 220, Cor.PRETO_E_BRANCO);
        reprografia.atenderEmDinheiro(listasDoFelipe, 60.00);

        TrabalhoDeImpressao tccDaGabi = new TrabalhoDeImpressao("Gabi", 99, Cor.PRETO_E_BRANCO);
        reprografia.atenderNoCartao(tccDaGabi);

        // A Gabi volta e pede frente e verso: agora cabe no papel que sobrou
        TrabalhoDeImpressao tccDaGabiFrenteEVerso = new TrabalhoDeImpressao("Gabi", 99, Cor.PRETO_E_BRANCO);
        tccDaGabiFrenteEVerso.marcarFrenteEVerso();
        reprografia.atenderNoCartao(tccDaGabiFrenteEVerso);

        System.out.println();
        reprografia.fecharCaixa();
    }
}

/*
 * ===================== PARTE C: RESPOSTAS =====================
 *
 * 1. Experimentos
 *
 * a) Desconto passa para 15%: muda uma linha só, a constante DESCONTO_POR_VOLUME.
 *    Mudam Bruno (R$ 21,25), Elisa (R$ 127,50) e Felipe (R$ 46,75), e o texto
 *    "(desconto 15%)" acompanha sozinho, porque sai da constante. A taxa do cartão
 *    continua 5%: no programa antigo as duas eram o mesmo 0.05 solto, e trocar
 *    "todos os 0.05" mudaria as duas regras ao mesmo tempo.
 *
 * b) Trocar >= por > em temDireitoADesconto: só o Bruno muda (100 páginas não é
 *    "mais que 100"), e ele paga R$ 25,00. A regra "a partir de 100" inclui o 100.
 *    O erro fica visível porque o nome da constante e o comentário dizem a regra.
 *
 * c) Mover os getters para antes dos atributos compila e roda igual: a ordem padrão
 *    (atributos, construtores, métodos de negócio, getters) não é exigida pelo
 *    compilador. É uma combinação da equipe para quem lê achar tudo no mesmo lugar.
 *
 * d) Desformatar a classe EstoqueDePapel (tudo numa linha só) também compila e
 *    roda igual. Formatação é para pessoas; o atalho da IDE desfaz o estrago.
 *
 * e) Renomear calcularValor para c: compila, mas é preciso trocar em todos os
 *    lugares que chamam o método (a IDE ajuda com "Rename"). Quem lê
 *    trabalho.c() precisa abrir a classe para descobrir o que ele faz.
 *
 * 2. Motivos para mudar (SRP)
 *    TrabalhoDeImpressao: preço, desconto ou regra de folhas.
 *    EstoqueDePapel: estoque mínimo ou controle de folhas.
 *    Caixa: formas de pagamento e taxa da maquininha.
 *    ImpressoraDeRecibo: formato das mensagens na tela.
 *    Reprografia: a ordem do atendimento (o que se confere antes de imprimir).
 *    O antigo processar() mudava por todos esses motivos ao mesmo tempo.
 *
 * 3. Nomes com "E": validarEImprimir faz duas coisas. Separando em podeImprimir
 *    e no atendimento, cada parte tem um nome que diz exatamente o que faz.
 *
 * 4. Comentários: os que explicam o porquê ficaram (desconto inclui o 100,
 *    pagamento conferido antes de imprimir, taxa diferente do desconto).
 *    "// calcula o total" e "// t é o total" sumiram com nomes melhores.
 *    O código antigo comentado (a taxa de 3% de 2023) é apagado: o histórico
 *    fica no controle de versão, não no meio do código.
 *
 * 5. Os 4 parâmetros: aluno, páginas, colorido e frente e verso no construtor
 *    passariam do limite de 3 e deixariam chamadas como (..., false, true),
 *    que ninguém entende sem abrir a classe. A cor virou enum e o frente e verso
 *    virou o método marcarFrenteEVerso(), que se lê como uma frase.
 */
