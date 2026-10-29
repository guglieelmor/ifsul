// A classe é o MOLDE: descreve todo bichinho uma vez só
public class Bichinho {
    // ESTADO (atributos): o que cada bichinho sabe sobre si
    private String nome;
    private String especie;
    private int fome;      // 0 = satisfeito, 10 = morrendo de fome
    private int energia;   // 0 = exausto, 10 = cheio de energia
    private int dias;      // quantas noites já dormiu

    // CONSTRUTOR: deixa o bichinho válido já no new
    public Bichinho(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
        this.fome = 5;
        this.energia = 10;
        this.dias = 0;
    }

    // Sobrecarga: um segundo construtor, sem parâmetros
    public Bichinho() {
        nome = "Sem nome";
        especie = "ovo";
        fome = 5;
        energia = 10;
        dias = 0;
    }

    // COMPORTAMENTO (métodos): o que cada bichinho faz

    // void + sem parâmetros: só altera o estado
    public void alimentar() {
        fome = fome - 3;
        if (fome < 0) {
            fome = 0;
        }
    }

    // Com retorno: avisa se conseguiu brincar
    public boolean brincar() {
        if (energia < 3) {
            return false; // cansado demais
        }
        energia = energia - 3;
        fome = fome + 2;
        if (fome > 10) {
            fome = 10;
        }
        return true;
    }

    // Uma noite de sono: recupera a energia e passa um dia
    public void dormir() {
        energia = 10;
        dias = dias + 1;
        fome = fome + 1;
        if (fome > 10) {
            fome = 10;
        }
    }

    // O humor não é atributo: é calculado a partir do estado
    public String humor() {
        if (fome >= 8) {
            return "faminto";
        }
        if (energia <= 2) {
            return "cansado";
        }
        return "feliz";
    }

    public String obterInfo() {
        return nome + " (" + especie + ") - dia " + dias
                + " | fome " + fome + "/10 | energia " + energia + "/10 | " + humor();
    }

    public String getNome() {
        return nome;
    }
}
