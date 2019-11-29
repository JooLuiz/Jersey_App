package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.Conteudo;

public class ConteudoController {

	public EntityManagerFactory emf;
	private EntityManager em;

	public ConteudoController(EntityManager Em) {
		em = Em;
	}

	public ConteudoController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}

	public Conteudo listarUm(long id) {
		try {
			em.getTransaction().begin();
			Conteudo conteudo = em.find(Conteudo.class, id);
			em.close();
			return conteudo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Conteudo> listar() {
		List<Conteudo> conteudo = new ArrayList<>();
		String query = "SELECT u FROM Conteudo u WHERE u.id IS NOT NULL";
		TypedQuery<Conteudo> tq = em.createQuery(query, Conteudo.class);
		try {
			em.getTransaction().begin();
			conteudo = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conteudo;
	}

	public void inserir(Conteudo conteudo) {
		try {
			em.getTransaction().begin();
			em.persist(conteudo);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Conteudo cont) {
		try {
			em.getTransaction().begin();
			Conteudo conteudo = em.find(Conteudo.class, id);
			conteudo.setAula(cont.getAula());
			conteudo.setConteudo(cont.getConteudo());
			conteudo.setExercicios(cont.getExercicios());
			em.merge(conteudo);
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
			Conteudo conteudo = em.find(Conteudo.class, id);
			em.remove(conteudo);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}