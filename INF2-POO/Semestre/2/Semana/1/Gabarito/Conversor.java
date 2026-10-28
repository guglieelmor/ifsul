// Classe utilitária: todos os métodos são static porque nenhum
// depende do estado de um objeto, só do texto recebido
public class Conversor {

    // "6,50" -> 6.5 (troca a vírgula antes do parse)
    public static double textoParaPreco(String texto) {
        return Double.parseDouble(texto.trim().replace(",", "."));
    }

    // "40" -> 40
    public static int textoParaQuantidade(String texto) {
        return Integer.parseInt(texto.trim());
    }
}
