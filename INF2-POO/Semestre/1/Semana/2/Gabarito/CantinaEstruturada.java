// Versão ESTRUTURADA: os dados ficam soltos em vetores globais
// e qualquer função (ou qualquer linha) pode alterá-los
public class CantinaEstruturada {
    static String[] titulares = {"Ana", "Bruno"};
    static double[] saldo = new double[2];

    static boolean recarregar(int aluno, double valor) {
        if (valor > 0) {
            saldo[aluno] += valor;
            return true;
        }
        return false;
    }

    static boolean pagar(int aluno, double valor) {
        if (valor > 0 && valor <= saldo[aluno]) {
            saldo[aluno] -= valor;
            return true;
        }
        return false;
    }

    static void mostrarSaldo(int aluno) {
        System.out.printf("Saldo de %s: R$ %.2f%n", titulares[aluno], saldo[aluno]);
    }

    public static void main(String[] args) {
        System.out.println("=== Versão estruturada ===");
        System.out.println("Recarregar R$ 20,00 para Ana: " + recarregar(0, 20.00));
        System.out.println("Ana paga pastel de R$ 6,00: " + pagar(0, 6.00));
        mostrarSaldo(0);
        System.out.println("Ana tenta pagar R$ 20,00: " + pagar(0, 20.00));
        mostrarSaldo(0);

        // O mistério do saldo: nada impede alterar o vetor direto
        saldo[0] = -50;
        System.out.println("Depois de saldo[0] = -50:");
        mostrarSaldo(0);
        mostrarSaldo(1);
    }
}
