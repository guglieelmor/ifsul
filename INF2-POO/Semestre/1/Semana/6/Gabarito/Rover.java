public class Rover {
    // 1. ATRIBUTOS (private: só a própria classe mexe)
    private String codigo;            // no construtor: cada rover tem o seu
    private String nome;
    private double bateria = 100.0;   // na declaração: todos nascem com 100%
    private char direcao = 'N';       // N, L, S ou O
    private int x;                    // valor padrão: 0
    private int y;                    // valor padrão: 0
    private int amostras;             // valor padrão: 0
    private boolean modoEconomia;     // valor padrão: false

    // Constante: gasto de bateria (%) por metro, fora do modo economia
    private final double GASTO_POR_METRO = 2.0;

    // 2. CONSTRUTOR
    public Rover(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    // 3. GETTERS E SETTERS
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    // this.nome é o atributo; nome é o parâmetro
    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Nome inválido: o rover continua " + this.nome);
        }
    }

    public double getBateria() {
        return bateria;
    }

    public int getAmostras() {
        return amostras;
    }

    // Getter de boolean começa com "is"
    public boolean isModoEconomia() {
        return modoEconomia;
    }

    public void setModoEconomia(boolean modoEconomia) {
        this.modoEconomia = modoEconomia;
    }

    // 4. MÉTODOS DE NEGÓCIO

    // void, sem parâmetros: só muda o estado
    public void girarDireita() {
        if (direcao == 'N') {
            direcao = 'L';
        } else if (direcao == 'L') {
            direcao = 'S';
        } else if (direcao == 'S') {
            direcao = 'O';
        } else {
            direcao = 'N';
        }
    }

    // Com parâmetros e retorno: recebe os metros, devolve o gasto
    public double calcularGasto(int metros) {
        double gastoPorMetro = GASTO_POR_METRO; // variável local
        if (modoEconomia) {
            gastoPorMetro = gastoPorMetro / 2;
        }
        return metros * gastoPorMetro;
    }

    // void, com parâmetros: ação que precisa de dados
    public void andar(int metros) {
        double gasto = calcularGasto(metros); // reaproveita o método acima
        if (metros <= 0 || gasto > bateria) {
            System.out.println(nome + ": bateria insuficiente para andar " + metros + " m");
            return;
        }
        bateria = bateria - gasto;
        if (direcao == 'N') {
            y = y + metros;
        } else if (direcao == 'S') {
            y = y - metros;
        } else if (direcao == 'L') {
            x = x + metros;
        } else {
            x = x - metros;
        }
    }

    // Com retorno, sem parâmetros: avisa se conseguiu coletar
    public boolean coletarAmostra() {
        if (bateria < 5) {
            return false;
        }
        bateria = bateria - 5;
        amostras++;
        return true;
    }

    // A bateria não tem setter: só muda andando, coletando ou carregando
    public void carregarPainelSolar(int horas) {
        if (horas > 0) {
            bateria = bateria + horas * 10;
            if (bateria > 100) {
                bateria = 100;
            }
        }
    }

    public double calcularAutonomia() {
        return bateria / calcularGasto(1);
    }

    public int calcularDistanciaBase() {
        return Math.abs(x) + Math.abs(y);
    }

    public boolean podeVoltarParaBase() {
        return calcularGasto(calcularDistanciaBase()) <= bateria;
    }

    public String obterStatus() {
        return String.format("%s %s | (%d, %d) virado para %c | bateria %.1f%% | amostras %d | economia: %s",
                codigo, nome, x, y, direcao, bateria, amostras, modoEconomia ? "sim" : "não");
    }
}
