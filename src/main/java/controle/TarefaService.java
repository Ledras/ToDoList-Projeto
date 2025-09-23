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
    
    public Tarefa criar(String titulo, String descricao, String prioridade) {
        Tarefa novaTarefa = new Tarefa(id++, titulo, descricao, false, prioridade);
        tarefas.add(novaTarefa);
        return novaTarefa;
    }
    
     public List<Tarefa> listar() {
        return tarefas;
    }

    public boolean atualizar(Long id, String novoTitulo, String novaDescricao) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId().equals(id)) {
                tarefa.setTitulo(novoTitulo);
                tarefa.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }
    
    public boolean remover(Long id) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId().equals(id)) {
                tarefas.remove(tarefa);
                return true;
            }
        }
        return false;
    }

