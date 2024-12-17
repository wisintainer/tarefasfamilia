package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import adriano.dao.TarefaDAO;
import adriano.model.Tarefa;

@ManagedBean(name = "tarefasMB")
@ViewScoped
public class TarefasMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tarefa tarefa;
	private EntityManagerFactory emf;
	private List<Tarefa> listaTarefas;
	private TarefaDAO tarefaDAO;

	@PostConstruct
	public void inicializar() {
		tarefaDAO = new TarefaDAO();
		this.tarefa = new Tarefa();
		this.emf = Persistence.createEntityManagerFactory("meuPU");
		carregarTarefas();
	}
	
	public void excluirTarefa(Tarefa tarefa) {
	    try {
	        tarefaDAO.excluirTarefa(tarefa); // Chama o método DAO
	        carregarTarefas(); // Recarrega a lista após a exclusão
	        System.out.println("Tarefa excluída com sucesso: " + tarefa.getNome());
	    } catch (Exception e) {
	        System.err.println("Erro ao excluir tarefa: " + e.getMessage());
	    }
	}


	public void carregarTarefas() {
		listaTarefas = tarefaDAO.buscarTodasTarefas();
	}

	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}

	public void inserirTarefa() {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(this.tarefa);
			em.getTransaction().commit();
			System.out.println("Tarefa cadastrada com sucesso: " + this.tarefa.getNome());
			this.tarefa = new Tarefa(); // Limpa os campos após a inserção
			carregarTarefas();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao cadastrar tarefa: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
}
