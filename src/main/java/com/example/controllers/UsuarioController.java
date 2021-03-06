package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.example.models.Usuario;

public class UsuarioController {

	public EntityManagerFactory emf;
	private EntityManager em;

	public UsuarioController(EntityManager Em) {
		em = Em;
	}

	public UsuarioController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}

	public Usuario listarUm(long id) {
		try {
			em.getTransaction().begin();
			Usuario user = em.find(Usuario.class, id);
			em.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Usuario> listar() {
		List<Usuario> users = new ArrayList<>();
		String query = "SELECT u FROM Usuario u WHERE u.id IS NOT NULL";
		TypedQuery<Usuario> tq = em.createQuery(query, Usuario.class);
		try {
			em.getTransaction().begin();
			users = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	public void inserir(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Usuario usuario) {
		try {
			System.out.println("to aqui");

			
			em.getTransaction().begin();
			Usuario user = em.find(Usuario.class, id);
			user.setNome(usuario.getNome());
			user.setSobrenome(usuario.getSobrenome());
			user.setIdade(usuario.getIdade());
			user.setUsuario(usuario.getUsuario());
			user.setSenha(usuario.getSenha());
			em.merge(user);
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
			Usuario user = em.find(Usuario.class, id);
			em.remove(user);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}