import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private boolean completa;
    private LocalDateTime dataAgora;
    private String prioridade;
    
    public Tarefa() {
        this.dataAgora = LocalDateTime.now();
        this.completa = false;
    }
    
    public Tarefa(Long id, String titulo, String descricao, boolean completa) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.completa = completa;
        this.dataAgora = LocalDateTime.now();
        this.prioridade = "MEDIA";
    }
    
    public Tarefa(Long id, String titulo, String descricao, boolean completa, String prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.completa = completa;
        this.dataAgora = LocalDateTime.now();
        this.prioridade = prioridade;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public boolean isCompleta() {
        return completa;
    }
    
    public void setCompleta(boolean completa) {
        this.completa = completa;
    }
    
    public LocalDateTime getDataAgora() {
        return dataAgora;
    }
    
    public void setDataAgora(LocalDateTime dataAgora) {
        this.dataAgora = dataAgora;
    }
    
    public String getPrioridade() {
        return prioridade;
    }
    
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    
    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataAgora.format(formatter);
    }
    
    @Override
    public String toString() {
        String status = completa ? "Concluido" : "Pendente";
        String icone = completa ? "✓" : "○";
        return String.format("ID: %d | %s %s | Prioridade: %s\nTítulo: %s\nDescrição: %s\nData: %s\n%s", 
                           id, status, icone, prioridade, titulo, descricao, getDataFormatada(),
                           "─".repeat(60));
    }
    
    public String toStringResumo() {
        String status = completa ? "✓" : "○";
        return String.format("%d. %s %s - %s", id, status, titulo, prioridade);
    }
}
