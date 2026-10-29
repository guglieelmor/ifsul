// Versão ORIENTADA A OBJETOS: o cartão é o "dono" do próprio saldo
public class CartaoCantina {
    // O que o cartão SABE (private: só os métodos daqui mexem)
    private String matricula;
    private String titular;
    private double saldo;

    public CartaoCantina(String matricula, String titular) {
        this.matricula = matricula;
        this.titular = titular;
    }

    // O que o cartão FAZ
    public boolean recarregar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    // A regra "saldo não fica negativo" mora num lugar só
    public boolean pagar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public String getMatricula() {
        return matricula;
    }
}
