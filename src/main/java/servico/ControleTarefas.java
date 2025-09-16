package servico;

import modelo.Tarefas;
import java.util.ArrayList;
import java.util.List;

public class ControleTarefas {
    private List<Tarefas> listaTarefas = new ArrayList<>();
    private int proximoId = 1;

    public Tarefas armazenarTarefa(String descricao, String prioridade) {
        Tarefas novaTarefa = new Tarefas(proximoId++, descricao, false, prioridade);
        listaTarefas.add(novaTarefa);
        return novaTarefa;
    }

    public List<Tarefas> listarTarefas() {
        return listaTarefas;
    }

    public boolean atualizarTarefa(int id, String novaDescricao, String novaPrioridade) {
        for (Tarefas tarefa : listaTarefas) {
            if (tarefa.getId() == id) {
                tarefa.setDescricao(novaDescricao);
                tarefa.setPrioridade(novaPrioridade);
                tarefa.setConcluida(false);
                return true;
            }
        }
        return false;
    }

    public boolean deletarTarefa(int id) {
        for (Tarefas tarefa : listaTarefas) {
            if (tarefa.getId() == id) {
                listaTarefas.remove(tarefa);
                return true;
            }
        }
        return false;
    }

    public boolean marcarComoConcluida(int id) {
        for (Tarefas tarefa : listaTarefas) {
            if (tarefa.getId() == id) {
                tarefa.setConcluida(true);
                return true;
            }
        }
        return false;
    }

    public Tarefas buscarTarefaPorId(int id) {
        for (Tarefas tarefa : listaTarefas) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }
        return null;
    }

    public List<Tarefas> listarTarefasConcluidas() {
        List<Tarefas> tarefasConcluidas = new ArrayList<>();

        for (Tarefas tarefa : listaTarefas) {
            if (tarefa.isConcluida() == true) {
                tarefasConcluidas.add(tarefa);
            }
        }
        return tarefasConcluidas;
    }
}
