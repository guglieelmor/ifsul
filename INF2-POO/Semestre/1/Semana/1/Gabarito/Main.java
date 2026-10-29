import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Quantos alunos tem a turma? ");
        int quantidade = Integer.parseInt(entrada.nextLine().trim());

        // Vetores: uma posição para cada aluno
        String[] nomes = new String[quantidade];
        double[] medias = new double[quantidade];

        for (int i = 0; i < quantidade; i++) {
            System.out.println("\n--- Aluno " + (i + 1) + " ---");
            System.out.print("Nome: ");
            nomes[i] = entrada.nextLine().trim();

            double soma = 0;
            for (int n = 1; n <= 4; n++) {
                System.out.print("Nota " + n + ": ");
                double nota = Double.parseDouble(entrada.nextLine().trim());

                // Repete enquanto a nota estiver fora de 0 a 10
                while (nota < 0 || nota > 10) {
                    System.out.print("Nota inválida! Digite de 0 a 10: ");
                    nota = Double.parseDouble(entrada.nextLine().trim());
                }
                soma += nota;
            }
            medias[i] = soma / 4;
        }

        // Relatório: percorre os vetores acumulando e comparando
        System.out.println("\n=== Boletim da turma ===");
        double somaMedias = 0;
        int aprovados = 0;
        int maior = 0; // posição do aluno com a maior média

        for (int i = 0; i < quantidade; i++) {
            String situacao;
            if (medias[i] >= 6.0) {
                situacao = "Aprovado";
                aprovados++;
            } else {
                situacao = "Em recuperação";
            }
            System.out.printf("%s: média %.2f - %s%n", nomes[i], medias[i], situacao);

            somaMedias += medias[i];
            if (medias[i] > medias[maior]) {
                maior = i;
            }
        }

        System.out.println("\n=== Resumo ===");
        System.out.printf("Média da turma: %.2f%n", somaMedias / quantidade);
        System.out.printf("Maior média: %s (%.2f)%n", nomes[maior], medias[maior]);
        System.out.println("Aprovados: " + aprovados);
        System.out.println("Em recuperação: " + (quantidade - aprovados));

        entrada.close();
    }
}

/*
 * RESPOSTAS DA PARTE C
 *
 * 1) Com variáveis soltas, 35 alunos com 4 notas seriam 35 nomes + 140
 *    notas = 175 variáveis, e o cálculo da média seria repetido 35 vezes.
 *    Com vetores e laços, o mesmo código serve para qualquer quantidade.
 *
 * 2) O while repete a leitura enquanto a nota for inválida. Um if só
 *    testaria uma vez: se o usuário errasse de novo, a nota errada entraria.
 *
 * 3) A variável maior guarda a POSIÇÃO do aluno no vetor. Com a posição
 *    dá para buscar tanto o nome (nomes[maior]) quanto a média
 *    (medias[maior]).
 */
