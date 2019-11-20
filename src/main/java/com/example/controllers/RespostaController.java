package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.Resposta;

public class RespostaController {

	public EntityManagerFactory emf;
	private EntityManager em;

	public RespostaController(EntityManager Em) {
		em = Em;
	}

	public RespostaController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}

	public Resposta listarUm(long id) {
		try {
			em.getTransaction().begin();
			Resposta resposta = em.find(Resposta.class, id);
			em.close();
			return resposta;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Resposta> listar() {
		List<Resposta> resposta = new ArrayList<>();
		String query = "SELECT u FROM Resposta u WHERE u.id IS NOT NULL";
		TypedQuery<Resposta> tq = em.createQuery(query, Resposta.class);
		try {
			em.getTransaction().begin();
			resposta = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resposta;
	}

	public void inserir(Resposta resposta) {
		try {
			em.getTransaction().begin();
			em.persist(resposta);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Resposta resp) {
		try {
			em.getTransaction().begin();
			Resposta resposta = em.find(Resposta.class, id);
			resposta.setExercicio(resp.exercicio);
			resposta.setDescricao(resp.descricao);
			resposta.setCorreta(resp.correta);
			em.merge(resposta);
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
			Resposta resposta = em.find(Resposta.class, id);
			em.remove(resposta);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}