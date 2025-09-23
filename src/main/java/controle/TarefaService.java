import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private List<Tarefa> tarefas = new ArrayList<>();
    private Long id = 1L;
    
   public Tarefa criar(String titulo, String descricao) {
        Tarefa novaTarefa = new Tarefa(id++, titulo, descricao, false);
        tarefas.add(novaTarefa);
        return novaTarefa;
    }