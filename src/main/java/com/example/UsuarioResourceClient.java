package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UsuarioResourceClient {

	private Connection con;
	private EntityManagerFactory emf;
	private EntityManager em;

	public UsuarioResourceClient(Connection con) {
		this.con = con;
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
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
	
	public Usuario listarUm(long id) throws SQLException {
		try {
			Usuario user = em.find(Usuario.class, id);
			em.close();
			return user;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Usuario> listar() throws SQLException {
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

	public void atualizar(long id, Usuario usuario) throws SQLException {
		try {
			Usuario user = em.find(Usuario.class, id);
			user.setNome(usuario.nome);
			user.setSobrenome(usuario.sobrenome);
			user.setIdade(usuario.idade);
			em.merge(user);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void excluir(long id) throws SQLException {
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