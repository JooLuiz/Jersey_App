package com.example.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.example.models.Usuario;

public class UsuarioController {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Client client;
	private String username;
	private String password;

	public UsuarioController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	public Usuario listarUm(long id) {
		try {
			Usuario user = em.find(Usuario.class, id);
			em.close();
			return user;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Usuario> listar() {
		List<Usuario> users = new ArrayList<>();
		String query = "SELECT u FROM Usuario u WHERE u.id IS NOT NULL";
		TypedQuery<Usuario> tq = em.createQuery(query, Usuario.class);
		try {
			users = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public void inserir(Usuario usuario) {
		try {
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Usuario usuario) {
		try {
			Usuario user = em.find(Usuario.class, id);
			user.setNome(usuario.nome);
			user.setSobrenome(usuario.sobrenome);
			user.setIdade(usuario.idade);
			user.setUsuario(usuario.usuario);
			user.setSenha(usuario.senha);
			em.merge(user);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void excluir(long id) {
		if (id == 0) {
			throw new IllegalStateException("Id da conta nao deve ser nula.");
		}

		try {
			Usuario user = em.find(Usuario.class, id);
			em.remove(user);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}