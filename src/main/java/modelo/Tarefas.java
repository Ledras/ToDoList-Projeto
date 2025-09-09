package modelo;

// Classe modelo para representar uma tarefa
public class Tarefas {
    private int id;
    private String descricao;
    private boolean concluida;
    private String prioridade;
    
    // Construtor padrão
    public Tarefas() {
    }
    
    // Construtor com parâmetros
    public Tarefas(int id, String descricao, boolean concluida, String prioridade) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = concluida;
        this.prioridade = prioridade;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public boolean isConcluida() {
        return concluida;
    }
    
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    
    public String getPrioridade() {
        return prioridade;
    }
    
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    
    // Método toString para exibição
    @Override
    public String toString() {
        String status = concluida ? "[CONCLUÍDA]" : "[PENDENTE]";
        return String.format("ID: %d | %s %s | Prioridade: %s | Descrição: %s", 
                           id, status, concluida ? "✓" : "○", prioridade, descricao);
    }
}
