package adriano.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import adriano.model.Tarefa;

public class TarefaDAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
	private EntityManager em;

	public TarefaDAO() {
		this.em = emf.createEntityManager();
	}

	public List<Tarefa> buscarTodasTarefas() {
		TypedQuery<Tarefa> query = em.createQuery("SELECT t FROM Tarefa t", Tarefa.class);
		return query.getResultList();
	}

	public void excluirTarefa(Tarefa tarefa) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Tarefa tarefaParaExcluir = em.find(Tarefa.class, tarefa.getId());
			if (tarefaParaExcluir != null) {
				em.remove(tarefaParaExcluir);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao excluir tarefa", e);
		} finally {
			em.close();
		}
	}

}
