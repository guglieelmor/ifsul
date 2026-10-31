import java.util.ArrayList;
import java.util.Collections;

/**
 * Controle das vagas de uma visita técnica.
 * Três listas que crescem e encolhem durante as inscrições.
 */
public class VisitaTecnica {
    private String destino;
    private int vagas;
    // As listas já nascem criadas: um atributo sem new valeria null
    private ArrayList<Aluno> confirmados = new ArrayList<>();
    private ArrayList<Aluno> espera = new ArrayList<>();
    private ArrayList<Integer> desistentes = new ArrayList<>(); // Integer, não int

    public VisitaTecnica(String destino, int vagas) {
        this.destino = destino;
        this.vagas = vagas;
    }

    // ---------- Inscrição ----------

    public void confirmar(Aluno aluno) {
        if (podeInscrever(aluno)) {
            if (confirmados.size() < vagas) {
                confirmados.add(aluno); // entra no final
                System.out.println(aluno + ": vaga confirmada");
            } else {
                espera.add(aluno);
                System.out.println(aluno + ": lista de espera, posição " + espera.size());
            }
        }
    }

    // Quem perdeu a visita anterior entra no INÍCIO da lista de espera
    public void confirmarComPrioridade(Aluno aluno) {
        if (podeInscrever(aluno)) {
            if (confirmados.size() < vagas) {
                confirmados.add(aluno);
                System.out.println(aluno + ": vaga confirmada");
            } else {
                espera.add(0, aluno); // insere no índice 0 e empurra os outros
                System.out.println(aluno + ": lista de espera com prioridade, posição 1");
            }
        }
    }

    private boolean podeInscrever(Aluno aluno) {
        if (buscar(aluno.getMatricula()) != null) {
            System.out.println(aluno + ": já está inscrito na visita");
            return false;
        }
        // contains com Integer: o int da matrícula vira Integer sozinho (autoboxing)
        if (desistentes.contains(aluno.getMatricula())) {
            System.out.println(aluno + ": desistiu antes e não pode se inscrever de novo");
            return false;
        }
        return true;
    }

    // ---------- Busca ----------

    // Procura nas duas listas comparando a matrícula (int se compara com ==)
    public Aluno buscar(int matricula) {
        for (Aluno a : confirmados) {
            if (a.getMatricula() == matricula) {
                return a;
            }
        }
        for (Aluno a : espera) {
            if (a.getMatricula() == matricula) {
                return a;
            }
        }
        return null; // só depois de olhar todos
    }

    // ---------- Desistência e troca ----------

    public void desistir(int matricula) {
        Aluno aluno = buscar(matricula);
        if (aluno == null) {
            System.out.println("Matrícula " + matricula + " não encontrada");
            return;
        }
        desistentes.add(matricula); // autoboxing: int -> Integer

        if (espera.remove(aluno)) { // remove pelo objeto; devolve true se achou
            System.out.println(aluno + " saiu da lista de espera");
            return;
        }
        confirmados.remove(aluno);
        if (espera.isEmpty()) {
            System.out.println(aluno + " desistiu. A vaga ficou livre.");
        } else {
            Aluno proximo = espera.remove(0); // tira o primeiro da espera
            confirmados.add(proximo);
            System.out.println(aluno + " desistiu. " + proximo + " assumiu a vaga.");
        }
    }

    // Passa a vaga para um colega, no MESMO lugar da lista (set)
    public void transferirVaga(int matricula, Aluno novo) {
        if (!podeInscrever(novo)) {
            return;
        }
        // for tradicional: aqui o índice importa
        for (int i = 0; i < confirmados.size(); i++) {
            if (confirmados.get(i).getMatricula() == matricula) {
                Aluno antigo = confirmados.set(i, novo); // set devolve quem saiu
                System.out.println(antigo + " passou a vaga para " + novo);
                return;
            }
        }
        System.out.println("Matrícula " + matricula + " não tem vaga confirmada");
    }

    // A coordenação libera um desistente para se inscrever de novo
    public void liberar(int matricula) {
        // remove(matricula) tiraria o ÍNDICE 2026102! Precisa remover o VALOR:
        if (desistentes.remove(Integer.valueOf(matricula))) {
            System.out.println("Matrícula " + matricula + " liberada para nova inscrição");
        } else {
            System.out.println("Matrícula " + matricula + " não estava bloqueada");
        }
    }

    // ---------- Relatório ----------

    public void mostrar() {
        System.out.println("=== " + destino + " ===");
        System.out.println("Confirmados (" + confirmados.size() + "/" + vagas + "):");
        for (int i = 0; i < confirmados.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + confirmados.get(i));
        }
        if (espera.isEmpty()) {
            System.out.println("Lista de espera: vazia");
        } else {
            System.out.println("Lista de espera (" + espera.size() + "):");
            for (int i = 0; i < espera.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + espera.get(i));
            }
        }
        System.out.println("Matrículas bloqueadas: " + desistentes); // ArrayList já sabe se imprimir
        System.out.println("Lista de embarque: " + listaDeEmbarque());
    }

    // Nomes dos confirmados em ordem alfabética.
    // Collections.sort não ordena Aluno (por nome ou matrícula?), mas ordena String.
    public ArrayList<String> listaDeEmbarque() {
        ArrayList<String> nomes = new ArrayList<>();
        for (Aluno a : confirmados) {
            nomes.add(a.getNome());
        }
        Collections.sort(nomes);
        return nomes;
    }
}
