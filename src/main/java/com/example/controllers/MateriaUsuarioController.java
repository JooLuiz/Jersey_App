package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.MateriaUsuario;

public class MateriaUsuarioController {

	public EntityManagerFactory emf;
	private EntityManager em;

	public MateriaUsuarioController(EntityManager Em) {
		em = Em;
	}

	public MateriaUsuarioController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}

	public MateriaUsuario listarUm(long id) {
		try {
			em.getTransaction().begin();
			MateriaUsuario materiaUsuario = em.find(MateriaUsuario.class, id);
			em.close();
			return materiaUsuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<MateriaUsuario> listar() {
		List<MateriaUsuario> materiaUsuario = new ArrayList<>();
		String query = "SELECT u FROM MateriaUsuario u WHERE u.id IS NOT NULL";
		TypedQuery<MateriaUsuario> tq = em.createQuery(query, MateriaUsuario.class);
		try {
			em.getTransaction().begin();
			materiaUsuario = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return materiaUsuario;
	}

	public void inserir(MateriaUsuario materiaUsuario) {
		try {
			em.getTransaction().begin();
			em.persist(materiaUsuario);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void atualizar(long id, MateriaUsuario matUser) {
		try {
			em.getTransaction().begin();
			MateriaUsuario materiaUsuario = em.find(MateriaUsuario.class, id);
			materiaUsuario.setMateria(matUser.getMateria());
			materiaUsuario.setUsuario(matUser.getUsuario());
			materiaUsuario.setAno(matUser.getAno());
			materiaUsuario.setSituacao(matUser.getSituacao());
			em.merge(materiaUsuario);
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
			MateriaUsuario materiaUsuario = em.find(MateriaUsuario.class, id);
			em.remove(materiaUsuario);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}