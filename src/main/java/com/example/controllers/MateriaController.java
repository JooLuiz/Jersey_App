package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.Materia;

public class MateriaController {

	public EntityManagerFactory emf;
	private EntityManager em;

	public MateriaController(EntityManager Em) {
		em = Em;
	}

	public MateriaController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}

	public Materia listarUm(long id) {
		try {
			em.getTransaction().begin();
			Materia materia = em.find(Materia.class, id);
			em.close();
			return materia;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Materia> listar() {
		List<Materia> materia = new ArrayList<>();
		String query = "SELECT u FROM Materia u WHERE u.id IS NOT NULL";
		TypedQuery<Materia> tq = em.createQuery(query, Materia.class);
		try {
			em.getTransaction().begin();
			materia = tq.getResultList();
			em.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return materia;
	}

	public void inserir(Materia materia) {
		try {
			em.getTransaction().begin();
			em.persist(materia);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Materia mat) {
		try {
			em.getTransaction().begin();
			Materia materia = em.find(Materia.class, id);
			materia.setDescricao(mat.getDescricao());
			materia.setAtiva(mat.getAtiva());
			materia.setAulas(mat.getAulas());
			em.merge(materia);
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
			Materia materia = em.find(Materia.class, id);
			em.remove(materia);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}