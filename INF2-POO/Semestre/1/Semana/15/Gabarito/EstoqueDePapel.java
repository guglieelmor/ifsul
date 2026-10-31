/**
 * O papel guardado na reprografia.
 * Só muda se mudar a regra do estoque.
 */
public class EstoqueDePapel {
    // Abaixo disso não dá para atender uma turma inteira: hora de pedir mais papel
    private static final int ESTOQUE_MINIMO = 100;

    // 1. Atributos
    private int folhasDisponiveis;
    private int folhasUsadas;

    // 2. Construtor
    public EstoqueDePapel(int folhasIniciais) {
        this.folhasDisponiveis = folhasIniciais;
    }

    // 3. Métodos de negócio
    public boolean temFolhasPara(int folhasNecessarias) {
        return folhasNecessarias <= folhasDisponiveis;
    }

    public void retirarFolhas(int quantidade) {
        folhasDisponiveis -= quantidade;
        folhasUsadas += quantidade;
    }

    public boolean estaAbaixoDoMinimo() {
        return folhasDisponiveis < ESTOQUE_MINIMO;
    }

    // 4. Getters
    public int getFolhasDisponiveis() {
        return folhasDisponiveis;
    }

    public int getFolhasUsadas() {
        return folhasUsadas;
    }
}
