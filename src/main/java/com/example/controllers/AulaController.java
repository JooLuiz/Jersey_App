package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.Aula;
import com.example.state.AulaState;

public class AulaController {

	public EntityManagerFactory emf;
	private EntityManager em;
	public AulaState aulaState;

	public AulaController(EntityManager Em) {
		em = Em;
		aulaState = new AulaState();
	}

	public AulaController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
		aulaState = new AulaState();
	}

	public Aula listarUm(long id) {
		try {
			em.getTransaction().begin();
			Aula aula = em.find(Aula.class, id);
			em.close();
			return aula;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Aula> listar() {
		List<Aula> aula = new ArrayList<>();
		String query = "SELECT u FROM Aula u WHERE u.id IS NOT NULL";
		TypedQuery<Aula> tq = em.createQuery(query, Aula.class);
		try {
			em.getTransaction().begin();
			aula = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aula;
	}

	public void inserir(Aula aula) {
		try {
			em.getTransaction().begin();
			em.persist(aula);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Aula al) {
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			Aula aula = em.find(Aula.class, id);
			aula.setMateria(al.getMateria());
			aula.setDescricao(al.getDescricao());
			aula.setSituacao(al.getSituacao());
			aula.setConteudos(al.getConteudos());
			em.merge(aula);
			em.getTransaction().commit();
			if (em.getTransaction().isActive()) {
				em.close();
			}
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
			Aula aula = em.find(Aula.class, id);
			em.remove(aula);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Aula iniciarAula(long id) {
		if (id == 0) {
			throw new IllegalStateException("Id da conta nao deve ser nula.");
		}

		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		Aula aula = em.find(Aula.class, id);
		try {
			Aula initiatedAula = aulaState.iniciarAula(aula);
			aula.setSituacao(initiatedAula.getSituacao());
			return aula;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (em.getTransaction().isActive()) {
			em.close();
		}
		return aula;
	}

	public Aula concluirAula(long id) {
		if (id == 0) {
			throw new IllegalStateException("Id da conta nao deve ser nula.");
		}

		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		Aula aula = em.find(Aula.class, id);
		try {
			Aula initiatedAula = aulaState.concluirAula(aula);
			aula.setSituacao(initiatedAula.getSituacao());
			return aula;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (em.getTransaction().isActive()) {
			em.close();
		}
		return aula;
	}

}