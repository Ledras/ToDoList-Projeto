package cliente;

import modelo.Tarefas;
import servico.ControleTarefas;
import java.util.Scanner;

//Classe Principal
public class Main {
    private static ControleTarefas tarefasControl = new ControleTarefas();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("---Gerenciador de Tarefas---");
        boolean continuar = true;
        
        while (continuar) {
            exibirMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    listarTodasTarefas();
                    break;
                case 3:
                    listarTarefasPendentes();
                    break;
                case 4:
                    marcarTarefaComoConcluida();
                    break;
                case 5:
                    deletarTarefa();
                    break;
                case 6:
                    exibirEstatisticas();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\nObrigado por usar o Gerenciador.");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
            
            if (continuar) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    // Exibe o menu principal
    private static void exibirMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Adicionar Nova Tarefa");
        System.out.println("2. Listar Todas as Tarefas");
        System.out.println("3. Listar Tarefas Pendentes");
        System.out.println("4. Marcar Tarefa como Concluída");
        System.out.println("5. Deletar Tarefa");
        System.out.println("6. Exibir Estatísticas");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }
    
    // Lê a opção do usuário
    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    // Adiciona uma nova tarefa
    private static void adicionarTarefa() {
        System.out.println("\n=== ADICIONAR NOVA TAREFA ===");
        
        System.out.print("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine().trim();
        
        if (descricao.isEmpty()) {
            System.out.println("Erro: A descrição não pode estar vazia!");
            return;
        }
        
        System.out.println("\nEscolha a prioridade:");
        System.out.println("1. Alta");
        System.out.println("2. Média");
        System.out.println("3. Baixa");
        System.out.print("Opção: ");
        
        String prioridade;
        int opcaoPrioridade = lerOpcao();
        
        switch (opcaoPrioridade) {
            case 1:
                prioridade = "Alta";
                break;
            case 2:
                prioridade = "Média";
                break;
            case 3:
                prioridade = "Baixa";
                break;
            default:
                prioridade = "Média";
                System.out.println("Prioridade inválida. Definindo como 'Média'.");
        }
        
        Tarefas novaTarefa = tarefasControl.armazenarTarefa(descricao, prioridade);
        System.out.println("\n✓ Tarefa adicionada com sucesso!");
        System.out.println("ID: " + novaTarefa.getId() + " | Descrição: " + novaTarefa.getDescricao() + " | Prioridade: " + novaTarefa.getPrioridade());
    }
    
    // Lista todas as tarefas
    private static void listarTodasTarefas() {
        tarefasControl.apresentarTarefas();
    }
    
    // Lista apenas as tarefas pendentes
    private static void listarTarefasPendentes() {
        tarefasControl.apresentarTarefasPendentes();
    }
    
    // Marca uma tarefa como concluída
    private static void marcarTarefaComoConcluida() {
        System.out.println("\n=== MARCAR TAREFA COMO CONCLUÍDA ===");
        
        if (tarefasControl.getTarefasPendentes() == 0) {
            System.out.println("Não há tarefas pendentes para marcar como concluídas!");
            return;
        }
        
        tarefasControl.apresentarTarefasPendentes();
        
        System.out.print("\nDigite o ID da tarefa a ser marcada como concluída: ");
        int id = lerOpcao();
        
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }
        
        Tarefas tarefa = tarefasControl.buscarTarefaPorId(id);
        if (tarefa == null) {
            System.out.println("Tarefa com ID " + id + " não encontrada!");
            return;
        }
        
        if (tarefa.isConcluida()) {
            System.out.println("Esta tarefa já está concluída!");
            return;
        }
        
        if (tarefasControl.marcarComoConcluida(id)) {
            System.out.println("\n✓ Tarefa marcada como concluída com sucesso!");
            System.out.println("Tarefa: " + tarefa.getDescricao());
        } else {
            System.out.println("Erro ao marcar tarefa como concluída!");
        }
    }
    
    // Deleta uma tarefa
    private static void deletarTarefa() {
        System.out.println("\n=== DELETAR TAREFA ===");
        
        if (tarefasControl.getTotalTarefas() == 0) {
            System.out.println("Não há tarefas para deletar!");
            return;
        }
        
        tarefasControl.apresentarTarefas();
        
        System.out.print("\nDigite o ID da tarefa a ser deletada: ");
        int id = lerOpcao();
        
        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }
        
        Tarefas tarefa = tarefasControl.buscarTarefaPorId(id);
        if (tarefa == null) {
            System.out.println("Tarefa com ID " + id + " não encontrada!");
            return;
        }
        
        System.out.println("\nTarefa a ser deletada:");
        System.out.println(tarefa.toString());
        System.out.print("\nTem certeza que deseja deletar esta tarefa? (s/n): ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();
        
        if (confirmacao.equals("s") || confirmacao.equals("sim")) {
            if (tarefasControl.deletarTarefa(id)) {
                System.out.println("\n✓ Tarefa deletada com sucesso!");
            } else {
                System.out.println("Erro ao deletar tarefa!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
    
    // Exibe estatísticas das tarefas
    private static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.println("Total de tarefas: " + tarefasControl.getTotalTarefas());
        System.out.println("Tarefas pendentes: " + tarefasControl.getTarefasPendentes());
        System.out.println("Tarefas concluídas: " + tarefasControl.getTarefasConcluidas());
        
        if (tarefasControl.getTotalTarefas() > 0) {
            double percentualConcluido = (double) tarefasControl.getTarefasConcluidas() / tarefasControl.getTotalTarefas() * 100;
            System.out.printf("Percentual de conclusão: %.1f%%\n", percentualConcluido);
        }
    }
}
