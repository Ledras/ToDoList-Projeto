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
    
