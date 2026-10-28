import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Dados do formulário: tudo chega como String
        String[][] formulario = {
            {"Pastel", "6,50", "40"},
            {"Suco", "4,00", "25"},
            {"Coxinha", " 5,00 ", "0"}
        };

        // Parte A - do formulário ao objeto
        System.out.println("=== Cadastro ===");
        Produto[] produtos = new Produto[formulario.length];
        for (int i = 0; i < formulario.length; i++) {
            String nome = formulario[i][0];
            double preco = Conversor.textoParaPreco(formulario[i][1]);
            int estoque = Conversor.textoParaQuantidade(formulario[i][2]);
            produtos[i] = new Produto(nome, preco, estoque);
            System.out.println(produtos[i]);
        }

        Produto pastel = produtos[0];
        Produto suco = produtos[1];
        Produto coxinha = produtos[2];

        // Parte B - encapsulamento
        System.out.println("\n=== Encapsulamento ===");
        pastel.setPreco(-5);
        System.out.println("Depois de setPreco(-5): " + Produto.formatarPreco(pastel.getPreco()));
        System.out.println("Vender 3 pastéis: " + pastel.vender(3));
        System.out.println("Vender 50 pastéis: " + pastel.vender(50));
        System.out.println("Vender 1 coxinha: " + coxinha.vender(1));
        System.out.println("Repor 20 coxinhas: " + coxinha.repor(20));
        // Cada objeto guarda o seu estado: vender pastel não mexe no suco
        System.out.println(pastel);
        System.out.println(suco);
        System.out.println(coxinha);

        // Parte C - wrappers, autoboxing e unboxing
        System.out.println("\n=== Vendas do dia ===");
        Map<String, Integer> vendasDoDia = new HashMap<>();
        vendasDoDia.put("Pastel", 3);   // autoboxing: int -> Integer
        suco.vender(5);
        vendasDoDia.put("Suco", 5);

        int pasteisVendidos = vendasDoDia.get("Pastel");   // unboxing
        // get("Coxinha") devolveria null -> NullPointerException no unboxing
        int coxinhasVendidas = vendasDoDia.getOrDefault("Coxinha", 0);
        System.out.println("Pastéis vendidos: " + pasteisVendidos);
        System.out.println("Coxinhas vendidas: " + coxinhasVendidas);

        // Parte D - conversão entre primitivos
        System.out.println("\n=== Fechamento do caixa ===");
        double total = pastel.calcularTotal(pasteisVendidos) + suco.calcularTotal(vendasDoDia.get("Suco"));
        System.out.println("Total vendido: " + Produto.formatarPreco(total));
        int totalCortado = (int) total;            // narrowing: corta os centavos
        long totalArredondado = Math.round(total); // arredonda
        System.out.println("Com cast (int): " + totalCortado);
        System.out.println("Com Math.round: " + totalArredondado);
        double estoqueEmDouble = pastel.getEstoque(); // widening automático
        System.out.println("Estoque do pastel como double: " + estoqueEmDouble);

        // Parte E - estático x instância
        System.out.println("\n=== Valor em estoque ===");
        for (Produto p : produtos) {
            System.out.println(p.getNome() + ": " + Produto.formatarPreco(p.valorEmEstoque()));
        }
    }
}

/*
 * RESPOSTAS DA PARTE F (perguntas conceituais)
 *
 * 1) formatarPreco e os métodos de Conversor são static porque não usam
 *    nenhum atributo: o resultado depende só do parâmetro. vender, repor,
 *    calcularTotal e valorEmEstoque são de instância porque leem ou alteram
 *    o preço/estoque de um produto específico (this).
 *
 * 2) Se estoque fosse "private static int estoque", ele pertenceria à
 *    classe e todos os produtos dividiriam o mesmo valor: vender 3 pastéis
 *    baixaria também o estoque do suco (o bug 03 do slide).
 *
 * 3) Double.parseDouble("6,50") lança NumberFormatException, porque o Java
 *    espera ponto como separador decimal. Por isso o replace(",", ".").
 *
 * 4) Não existe setEstoque porque ninguém deve digitar o estoque direto:
 *    ele só muda por vender() (que recusa faltar estoque) e repor().
 *
 * 5) (int) 39.5 vale 39: o cast corta a parte decimal. Math.round(39.5)
 *    vale 40.
 */
