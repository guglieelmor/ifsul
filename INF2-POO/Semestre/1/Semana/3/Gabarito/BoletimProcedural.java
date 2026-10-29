// Versão PROCEDURAL (estilo C dos anos 1970): cada dado do aluno é uma
// variável solta e as regras ficam em funções separadas dos dados
public class BoletimProcedural {
    // Dados: um conjunto de variáveis para cada aluno
    static String nomeAna = "Ana Souza";
    static double n1Ana = 7.5, n2Ana = 8.0, n3Ana = 6.0;

    static String nomeJoao = "João Lima";
    static double n1Joao = 5.0, n2Joao = 6.5, n3Joao = 4.0;

    // Regras: em outro lugar, recebendo os dados por parâmetro
    static double media(double a, double b, double c) {
        return (a + b + c) / 3;
    }

    static boolean aprovado(double m) {
        return m >= 6.0;
    }

    static void mostrarBoletim(String nome, double a, double b, double c) {
        double m = media(a, b, c);
        String situacao = aprovado(m) ? "APROVADO" : "REPROVADO";
        System.out.printf("%s: média %.2f - %s%n", nome, m, situacao);
    }

    public static void main(String[] args) {
        System.out.println("=== Boletim procedural ===");
        mostrarBoletim(nomeAna, n1Ana, n2Ana, n3Ana);
        mostrarBoletim(nomeJoao, n1Joao, n2Joao, n3Joao);

        // Nada impede qualquer linha de colocar uma nota inválida
        n1Ana = 11;
        System.out.println("Depois de n1Ana = 11:");
        mostrarBoletim(nomeAna, n1Ana, n2Ana, n3Ana);
    }
}
