public class Main {
    public static void main(String[] args) {
        VisitaTecnica visita = new VisitaTecnica("Visita ao Polo Tecnológico", 5);

        System.out.println("=== Inscrições ===");
        visita.confirmar(new Aluno("Ana", 2026101));
        visita.confirmar(new Aluno("Beto", 2026102));
        visita.confirmar(new Aluno("Caio", 2026103));
        visita.confirmar(new Aluno("Duda", 2026104));
        visita.confirmar(new Aluno("Eva", 2026105));
        visita.confirmar(new Aluno("Ana", 2026101));  // repetida
        visita.confirmar(new Aluno("Fabi", 2026106));
        visita.confirmar(new Aluno("Gabi", 2026107));
        visita.confirmarComPrioridade(new Aluno("Hugo", 2026108));

        System.out.println();
        System.out.println("=== Mudanças da semana ===");
        visita.desistir(2026102);                              // Beto sai, Hugo sobe
        visita.transferirVaga(2026103, new Aluno("Iris", 2026109));
        visita.desistir(2026107);                              // Gabi sai da espera
        visita.confirmar(new Aluno("Beto", 2026102));          // bloqueado
        visita.liberar(2026102);
        visita.confirmar(new Aluno("Beto", 2026102));          // agora entra na espera
        visita.desistir(2026199);                              // não existe

        System.out.println();
        System.out.println("=== Conselho de classe ===");
        System.out.println("Caio vai? " + (visita.buscar(2026103) != null));
        System.out.println("Iris vai? " + (visita.buscar(2026109) != null));

        System.out.println();
        visita.mostrar();
    }
}

/*
 * RESPOSTAS DA PARTE C (uma solução possível)
 *
 * 1) Experimentos:
 *    a) Lista de matrículas declarada com int: "unexpected type /
 *       required: reference". Generics só aceitam objetos; para int existe
 *       o wrapper Integer. (Um int[] compila porque array aceita primitivo.)
 *    b) Liberação com remove(matricula), passando o int:
 *       IndexOutOfBoundsException: Index 2026102 out of bounds for length 2.
 *       Com int, o remove entende ÍNDICE. Para tirar o VALOR, é preciso
 *       passar um Integer: remove(Integer.valueOf(matricula)).
 *    c) Laço do relatório com <= size():
 *       IndexOutOfBoundsException: Index 5 out of bounds for length 5.
 *       Os índices vão de 0 a size() - 1; o laço deu uma volta a mais.
 *    d) Remover o confirmado dentro de um for-each na própria lista:
 *       ConcurrentModificationException. A lista mudou enquanto o for-each
 *       a percorria. Solução: remover fora do laço (remove(aluno)) ou guardar
 *       o índice e remover depois.
 *    e) Lista de espera sem o "= new ArrayList<>()":
 *       NullPointerException ("this.espera" is null). O atributo foi
 *       declarado, mas a lista nunca foi criada.
 *    f) confirmados.contains(new Aluno("Ana", 2026101)) dá false, mesmo com a
 *       Ana na lista. O contains compara objetos, e o new criou OUTRO objeto
 *       (mesmos dados, outro endereço). Por isso a busca é por matrícula.
 *       (Ensinar ao Java quando dois alunos são iguais é o equals, em breve.)
 *    g) Collections.sort(confirmados): "no suitable method found for
 *       sort(ArrayList<Aluno>)". O Java não sabe se ordena Aluno por nome ou
 *       por matrícula (isso é o Comparable, próxima aula). A lista de
 *       embarque copia os nomes para um ArrayList<String>, que sabe ordenar.
 *
 * 2) add(0, Hugo) insere no início e empurra Fabi e Gabi uma posição: a
 *    lista de espera continua com todos. set(i, Iris) substitui o Caio no
 *    mesmo lugar: ninguém é empurrado e o size() não muda.
 *
 * 3) Capacidade com 44 alunos (ônibus inteiro): 10 -> 15 -> 22 -> 33 -> 49.
 *    As cópias acontecem no 11º, 16º, 23º e 34º add: 4 cópias. O size()
 *    fica 44 e a capacidade 49 (5 lugares sobrando no array interno).
 *
 * 4) Array ou ArrayList?
 *    - poltronas numeradas do ônibus (44, fixas): array;
 *    - inscritos na visita: ArrayList (entra e sai gente até a véspera);
 *    - lista de espera: ArrayList (insere no início, remove do início);
 *    - os 3 horários de parada da viagem: array (quantidade fixa);
 *    - matrículas bloqueadas: ArrayList<Integer> (cresce e encolhe).
 *
 * 5) Com um array de 5 posições, a desistência do Beto deixaria um null no
 *    meio (ou exigiria deslocar todo mundo à mão), a subida do Hugo exigiria
 *    achar o buraco, e a prioridade na espera exigiria empurrar todos para
 *    trás num laço. Com ArrayList: remove(aluno), add(proximo), add(0, x).
 */
