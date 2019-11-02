package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.Exercicio;
import com.example.state.IState;

public class ExercicioController implements IState<List<Exercicio>> {

	public EntityManagerFactory emf;
	private EntityManager em;
	private List<Exercicio> state;

	public ExercicioController(EntityManager Em) {
		em = Em;
		state = this.listar();
	}

	public ExercicioController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}

	@Override
	public List<Exercicio> setState(List<Exercicio> listExercicio) {
		try {
			this.state = listExercicio;
			return this.state;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	};

	@Override
	public List<Exercicio> getState() {
		return state;
	};

	public Exercicio listarUm(long id) {
		try {
			em.getTransaction().begin();
			Exercicio exercicio = em.find(Exercicio.class, id);
			em.close();
			return exercicio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Exercicio> listar() {
		List<Exercicio> exercicio = new ArrayList<>();
		String query = "SELECT u FROM Exercicio u WHERE u.id IS NOT NULL";
		TypedQuery<Exercicio> tq = em.createQuery(query, Exercicio.class);
		try {
			em.getTransaction().begin();
			exercicio = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exercicio;
	}

	public void inserir(Exercicio exercicio) {
		try {
			em.getTransaction().begin();
			em.persist(exercicio);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Exercicio exerc) {
		try {
			em.getTransaction().begin();
			Exercicio exercicio = em.find(Exercicio.class, id);
			exercicio.setConteudo(exerc.conteudo);
			exercicio.setPergunta(exerc.pergunta);
			exercicio.setDificuldade(exerc.dificuldade);
			exercicio.setResposta(exerc.resposta);
			em.merge(exercicio);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(long id) {
		if (id == 0) {
			throw new IllegalStateException("Id da conta nao deve ser nula.");
		}

		try {
			em.getTransaction().begin();
			Exercicio exercicio = em.find(Exercicio.class, id);
			em.remove(exercicio);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}