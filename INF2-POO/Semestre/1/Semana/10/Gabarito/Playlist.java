import java.util.ArrayList;

/**
 * Playlist de um programa da Rádio IF.
 * Exemplo de sobrecarga de construtores e de métodos:
 * as versões simples sempre repassam para a versão completa.
 */
public class Playlist {
    // Valores padrão usados quando a informação não é passada
    private static final String NOME_PADRAO = "Playlist sem nome";
    private static final String LOCUTOR_PADRAO = "Rádio IF";
    private static final int MINUTOS_PADRAO = 20;
    private static final String ARTISTA_PADRAO = "Desconhecido";
    private static final int DURACAO_PADRAO = 180; // 3:00

    private String nome;
    private String locutor;
    private int limiteSegundos;
    private ArrayList<Musica> musicas = new ArrayList<>();

    // ---------- Construtores sobrecarregados ----------

    public Playlist() {
        this(NOME_PADRAO); // this(...) precisa ser a primeira linha
    }

    public Playlist(String nome) {
        this(nome, LOCUTOR_PADRAO);
    }

    public Playlist(String nome, String locutor) {
        this(nome, locutor, MINUTOS_PADRAO);
    }

    // Construtor completo: as atribuições ficam num lugar só
    public Playlist(String nome, String locutor, int minutos) {
        this.nome = nome;
        this.locutor = locutor;
        this.limiteSegundos = minutos * 60;
    }

    // ---------- adicionarMusica sobrecarregado ----------

    // Só o título: artista desconhecido e duração padrão
    public void adicionarMusica(String titulo) {
        adicionarMusica(titulo, ARTISTA_PADRAO);
    }

    // Título e artista: duração padrão
    public void adicionarMusica(String titulo, String artista) {
        adicionarMusica(titulo, artista, DURACAO_PADRAO);
    }

    // Versão completa: a única que valida e guarda a música
    public void adicionarMusica(String titulo, String artista, int segundos) {
        if (segundos <= 0) {
            System.out.println("Duração inválida: " + titulo);
            return;
        }
        if (getTempoTotal() + segundos > limiteSegundos) {
            System.out.println("Não cabe: " + titulo + " (" + formatar(segundos) + ")");
            return;
        }
        musicas.add(new Musica(titulo, artista, segundos));
    }

    // Minutos e segundos separados: converte e repassa para a completa
    public void adicionarMusica(String titulo, String artista, int minutos, int segundos) {
        if (minutos < 0 || segundos < 0 || segundos > 59) {
            System.out.println("Duração inválida: " + titulo);
            return;
        }
        adicionarMusica(titulo, artista, minutos * 60 + segundos);
    }

    // Varargs: vários títulos de uma vez (último recurso na escolha do Java)
    public void adicionarMusica(String... titulos) {
        for (String titulo : titulos) {
            adicionarMusica(titulo, ARTISTA_PADRAO);
        }
    }

    // ---------- Consultas ----------

    public int getTempoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.getSegundos();
        }
        return total;
    }

    public int getTempoRestante() {
        return limiteSegundos - getTempoTotal();
    }

    public int getQuantidade() {
        return musicas.size();
    }

    public String resumo() {
        return nome + " | locutor: " + locutor + " | limite: " + formatar(limiteSegundos);
    }

    public void mostrar() {
        System.out.println("=== " + nome + " (" + locutor + ") ===");
        for (int i = 0; i < musicas.size(); i++) {
            Musica m = musicas.get(i);
            System.out.printf("%2d. %-22s %-18s %6s%n", i + 1, m.getTitulo(),
                    m.getArtista(), formatar(m.getSegundos()));
        }
        System.out.println("Músicas: " + getQuantidade());
        System.out.println("Tempo total: " + formatar(getTempoTotal())
                + " de " + formatar(limiteSegundos));
        System.out.println("Sobra: " + formatar(getTempoRestante()));
    }

    // Converte segundos para o formato m:ss
    private String formatar(int segundos) {
        return String.format("%d:%02d", segundos / 60, segundos % 60);
    }
}
