public class Main {
    public static void main(String[] args) {
        // Uma classe Rover, dois objetos com estado próprio
        Rover curiosa = new Rover("RV-01", "Curiosa");
        Rover teimoso = new Rover("RV-02", "Teimoso");

        System.out.println("=== Pouso ===");
        System.out.println(curiosa.obterStatus());
        System.out.println(teimoso.obterStatus());

        // Missão da Curiosa (é o teste de mesa da Parte B)
        System.out.println("\n=== Missão da Curiosa ===");
        curiosa.andar(10);
        curiosa.girarDireita();
        curiosa.andar(5);
        System.out.println("Coletou amostra? " + curiosa.coletarAmostra());
        System.out.println(curiosa.obterStatus());
        // O retorno vira valor: guardar, decidir e mostrar
        double autonomia = curiosa.calcularAutonomia();
        System.out.printf("Autonomia: %.1f m%n", autonomia);
        System.out.println("Distância até a base: " + curiosa.calcularDistanciaBase() + " m");
        System.out.println("Pode voltar para a base? " + curiosa.podeVoltarParaBase());

        System.out.println("\n=== Missão do Teimoso ===");
        teimoso.setNome("");
        teimoso.setNome("Teimoso II");
        teimoso.andar(60);
        System.out.printf("Gasto para 60 m: %.1f%%%n", teimoso.calcularGasto(60));
        teimoso.setModoEconomia(true);
        System.out.printf("Gasto para 60 m em economia: %.1f%%%n", teimoso.calcularGasto(60));
        teimoso.andar(60);
        System.out.println(teimoso.obterStatus());
        System.out.println("Pode voltar para a base? " + teimoso.podeVoltarParaBase());
        teimoso.carregarPainelSolar(3);
        System.out.printf("Depois de 3 h de sol: %.1f%%%n", teimoso.getBateria());
        teimoso.carregarPainelSolar(5);
        System.out.printf("Depois de mais 5 h de sol: %.1f%%%n", teimoso.getBateria());

        System.out.println("\n=== Relatório final ===");
        System.out.println(curiosa.obterStatus());
        System.out.println(teimoso.obterStatus());

        // teimoso.bateria = 500;  // NÃO COMPILA: bateria has private access in Rover
    }
}

/*
 * RESPOSTAS DA PARTE C
 *
 * 1) Teste de mesa da Curiosa:
 *
 *    Linha                         bateria  direcao  x  y   amostras
 *    1 new Rover("RV-01", ...)     100.0    N        0  0   0
 *    2 curiosa.andar(10)            80.0    N        0  10  0
 *    3 curiosa.girarDireita()       80.0    L        0  10  0
 *    4 curiosa.andar(5)             70.0    L        5  10  0
 *    5 curiosa.coletarAmostra()     65.0    L        5  10  1
 *    6 curiosa.calcularAutonomia()  65.0    L        5  10  1  -> 32.5
 *
 * 2) Tipos de método:
 *    sem parâmetros, void ......... girarDireita()
 *    sem parâmetros, com retorno .. coletarAmostra(), calcularAutonomia(),
 *                                   calcularDistanciaBase(), podeVoltarParaBase()
 *    com parâmetros, void ......... andar(int), carregarPainelSolar(int), setNome(String)
 *    com parâmetros e retorno ..... calcularGasto(int)
 *
 * 3) bateria não tem setter porque só muda de jeitos definidos: gasta
 *    andando e coletando, e sobe pelo painel solar (até 100%). Um
 *    setBateria(500) deixaria o rover inválido. O mesmo vale para
 *    amostras (só aumenta de 1 em 1, em coletarAmostra()) e para codigo
 *    (identifica o rover, é definido no construtor).
 *
 * 4) Em calcularGasto(int metros): metros é parâmetro e gastoPorMetro é
 *    variável local (existem só durante a chamada, na stack); modoEconomia
 *    e GASTO_POR_METRO são atributos (vivem no objeto, no heap).
 *    x e y nunca recebem valor no construtor e valem 0 porque atributo
 *    ganha valor padrão. Uma variável local sem valor não compila.
 *
 * 5) Sem this, "nome = nome;" atribui o parâmetro a ele mesmo e o
 *    atributo não muda: setNome("Teimoso II") deixaria o nome antigo.
 *
 * 6) Encontre os erros (classe Antena):
 *    L2  Canal: atributo fora do camelCase -> canal (compila, mas foge
 *        da convenção)
 *    L3  public int sinal: qualquer código altera -> private int sinal
 *    L7  canal = canal: o parâmetro recebe ele mesmo e o atributo fica
 *        null -> this.canal = canal
 *    L11 setter sem validação -> if (s >= 0 && s <= 100) sinal = s;
 *    L15 int ruido; sem valor: variável local não tem valor padrão, não
 *        compila -> int ruido = 0; (ou receber por parâmetro)
 *    L16 método double sem return: não compila -> return sinal - ruido;
 *    L19 boolean ligada(): getter de boolean -> isLigada()
 */
