public class Main {
    public static void main(String[] args) {
        System.out.println("=== Programas da semana ===");
        // Quatro construtores: o Java escolhe pela lista de argumentos
        Playlist p1 = new Playlist();
        Playlist p2 = new Playlist("Sexta Musical");
        Playlist p3 = new Playlist("Intervalo do Almoço", "Bia");
        Playlist p4 = new Playlist("Especial de Formatura", "Caio", 45);
        System.out.println(p1.resumo());
        System.out.println(p2.resumo());
        System.out.println(p3.resumo());
        System.out.println(p4.resumo());

        System.out.println();
        System.out.println("=== Montando o intervalo ===");
        p3.adicionarMusica("Abertura do Intervalo");                   // (String)
        p3.adicionarMusica("Pôr do Sol no Jacuí", "Banda Charqueada");  // (String, String)
        p3.adicionarMusica("Código Sem Bug", "Os Compiladores", 245);   // (String, String, int)
        p3.adicionarMusica("Recreio Eterno", "Trio Sineta", 3, 50);     // (String, String, int, int)
        p3.adicionarMusica("Saideira", "Os Compiladores", 2, 75);       // segundos inválidos
        p3.adicionarMusica("Chimarrão", "Mate Amargo", "Vento Minuano"); // varargs (String...)

        System.out.println();
        p3.mostrar();
    }
}

/*
 * RESPOSTAS DA PARTE C (uma solução possível)
 *
 * 1) Experimentos:
 *    a) Versão que só muda o retorno (boolean no lugar de void):
 *       "method adicionarMusica(String,String,int) is already defined in class Playlist".
 *       O retorno não faz parte da assinatura: para o Java são o mesmo método.
 *    b) Cópia da versão (título, artista) só com os nomes dos parâmetros trocados:
 *       o mesmo "is already defined". Nomes das variáveis também não fazem
 *       parte da assinatura; o que conta é nome do método + tipos e ordem.
 *    c) println antes do this(...) no construtor de um parâmetro:
 *       no JDK 24: "flexible constructors is a preview feature and is
 *       disabled by default" (em versões antigas: "call to this must be first
 *       statement in constructor"). Regra da aula: this(...) é a primeira
 *       linha. (A partir do Java 25 um comando simples antes do this(...) é
 *       permitido, desde que não use o objeto que ainda está sendo criado.)
 *    d) Duração 245L (long) na versão de segundos:
 *       "no suitable method found for adicionarMusica(String,String,long)".
 *       Ampliação só vai do menor para o maior (int -> long), nunca de long
 *       para int, e nenhuma versão serve.
 *    e) testar(int, double) e testar(double, int) chamados com testar(5, 10):
 *       "reference to testar is ambiguous". As duas servem com uma conversão
 *       cada. Resolve com testar(5.0, 10) ou testar(5, 10.0).
 *    f) Sem a versão de só título, ainda compila e a saída é igual: a chamada
 *       com um título cai no varargs (passo 4, último recurso), que monta
 *       {"Abertura do Intervalo"} e usa os mesmos valores padrão.
 *    g) Com volume(long) e volume(Integer), volume(10) escolhe long:
 *       ampliação (passo 2) vem antes de autoboxing (passo 3).
 *
 * 2) adicionarMusica("Chimarrão", "Mate Amargo") não chama o varargs:
 *    existe a versão (String, String), que é tipo exato. "Mate Amargo" vira o
 *    ARTISTA da música Chimarrão, e só uma música entra. O varargs só é usado
 *    quando nenhuma outra versão serve. Por isso misturar varargs de String com
 *    versões que também recebem String é perigoso; um nome diferente
 *    (ex.: adicionarVarias) deixaria a intenção clara.
 *
 * 3) "Músicas: " + 2 + 3 dá "Músicas: 23" (depois da String, o + junta texto).
 *    2 + 3 + " músicas" dá "5 músicas" (primeiro soma, depois junta).
 *    Para somar dentro do texto: "Músicas: " + (2 + 3).
 *
 * 4) A PlaylistAoVivo que calcula o tempo total de outro jeito seria
 *    SOBRESCRITA: mesma assinatura, numa subclasse, decidida na execução pelo
 *    objeto real. As várias versões de adicionarMusica são SOBRECARGA:
 *    mesma classe, parâmetros diferentes, decididas na compilação.
 *
 * 5) Se o limite mudar (ex.: músicas de no máximo 6 minutos), só a versão
 *    completa (String, String, int) muda: as outras repassam para ela.
 *    Se cada versão validasse sozinha, seriam 5 lugares para corrigir.
 */
