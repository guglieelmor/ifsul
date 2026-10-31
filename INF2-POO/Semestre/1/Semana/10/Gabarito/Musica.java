/**
 * Uma música da playlist: título, artista e duração em segundos.
 */
public class Musica {
    private String titulo;
    private String artista;
    private int segundos;

    public Musica(String titulo, String artista, int segundos) {
        this.titulo = titulo;
        this.artista = artista;
        this.segundos = segundos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getSegundos() {
        return segundos;
    }
}
