import java.util.List;
import java.util.Scanner;

public class Main {
    private static TarefaService service = new TarefaService();
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== TODOLIST ===");
        
        while (true) {
            mostrarMenu();
            int opcao = lerInt();
            
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> editar();
                case 4 -> concluir();
                case 5 -> excluir();
                case 6 -> listarConcluidas();
                case 7 -> buscar();
                case 0 -> {
                    System.out.println("Até logo!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
            pausar();
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("\n1. Cadastrar  2. Listar  3. Editar  4. Concluir");
        System.out.println("5. Excluir  6. Concluídas  7. Buscar  0. Sair");
        System.out.printf("Total: %d | Pendentes: %d | Concluídas: %d\n", 
            service.contarTarefas(), service.contarTarefasPendentes(), service.contarTarefasConcluidas());
        System.out.print("Opção: ");
    }
    
    private static int lerInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void pausar() {
        System.out.print("\nPressione Enter...");
        sc.nextLine();
    }
    
    private static void cadastrar() {
        System.out.print("\nTítulo: ");
        String titulo = sc.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("Título obrigatório!");
            return;
        }
        
        System.out.print("Descrição: ");
        String descricao = sc.nextLine().trim();
        if (descricao.isEmpty()) {
            System.out.println("Descrição obrigatória!");
            return;
        }
        
        System.out.print("Prioridade (1-Alta, 2-Média, 3-Baixa): ");
        String[] prioridades = {"MEDIA", "ALTA", "MEDIA", "BAIXA"};
        int p = lerInt();
        String prioridade = (p >= 1 && p <= 3) ? prioridades[p] : "MEDIA";
        
        Tarefa tarefa = service.criar(titulo, descricao, prioridade);
        System.out.println("Tarefa criada: " + tarefa.getId());
    }
    
    private static void listar() {
        List<Tarefa> tarefas = service.listar();
        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada.");
            return;
        }
        System.out.println();
        tarefas.forEach(System.out::println);
    }
    
    private static void editar() {
        mostrarResumo();
        System.out.print("\nID para editar: ");
        Long id = (long) lerInt();
        
        Tarefa tarefa = service.pesquisar(id);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada!");
            return;
        }
        
        System.out.print("Novo título (" + tarefa.getTitulo() + "): ");
        String titulo = sc.nextLine().trim();
        if (titulo.isEmpty()) titulo = tarefa.getTitulo();
        
        System.out.print("Nova descrição (" + tarefa.getDescricao() + "): ");
        String descricao = sc.nextLine().trim();
        if (descricao.isEmpty()) descricao = tarefa.getDescricao();
        
        if (service.atualizar(id, titulo, descricao)) {
            System.out.println("Tarefa atualizada!");
        } else {
            System.out.println("Erro ao atualizar!");
        }
    }
    
    private static void concluir() {
        mostrarResumo();
        System.out.print("\nID para concluir: ");
        Long id = (long) lerInt();
        
        Tarefa tarefa = service.pesquisar(id);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada!");
            return;
        }
        
        if (tarefa.isCompleta()) {
            System.out.println("Já está concluída!");
            return;
        }
        
        if (service.marcar(id)) {
            System.out.println("Tarefa concluída!");
        } else {
            System.out.println("Erro ao concluir!");
        }
    }
    
    private static void excluir() {
        mostrarResumo();
        System.out.print("\nID para excluir: ");
        Long id = (long) lerInt();
        
        Tarefa tarefa = service.pesquisar(id);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada!");
            return;
        }
        
        System.out.println(tarefa.toStringResumo());
        System.out.print("Confirmar exclusão? (s/N): ");
        String resp = sc.nextLine().trim().toLowerCase();
        
        if (resp.equals("s") || resp.equals("sim")) {
            if (service.remover(id)) {
                System.out.println("Tarefa excluída!");
            } else {
                System.out.println("Erro ao excluir!");
            }
        } else {
            System.out.println("Cancelado.");
        }
    }
    
    private static void listarConcluidas() {
        List<Tarefa> concluidas = service.listarCompletas();
        if (concluidas.isEmpty()) {
            System.out.println("\nNenhuma tarefa concluída.");
            return;
        }
        System.out.println();
        concluidas.forEach(System.out::println);
    }
    
    private static void buscar() {
        System.out.print("\nID da tarefa: ");
        Long id = (long) lerInt();
        
        Tarefa tarefa = service.pesquisar(id);
        if (tarefa != null) {
            System.out.println(tarefa);
        } else {
            System.out.println("Tarefa não encontrada!");
        }
    }
    
    private static void mostrarResumo() {
        List<Tarefa> tarefas = service.listar();
        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa disponível.");
            return;
        }
        System.out.println();
        tarefas.forEach(t -> System.out.println(t.toStringResumo()));
    }
}
