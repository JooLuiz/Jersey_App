package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioResourceClient {

	private Connection con;
	private EntityManagerFactory emf;
	private EntityManager em;

	public UsuarioResourceClient(Connection con) {
		this.con = con;
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Usuario usuarioPadrao = new Usuario();
	}

	public void inserir(Usuario usuario) {
		try {
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
			System.out.println("Dados inseridos com sucesso!");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public Usuario listarUm(long id) throws SQLException {
		try {
			Usuario user = em.find(Usuario.class, id);
			System.out.println("Lista um com Sucesso!");
			return user;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Usuario> getAll(){
		return (List<Usuario>) em.createQuery("Select t from usuarios t").getResultList();
	}

	public List<Usuario> listar() throws SQLException {
		try {
			List<Usuario> usuarios = getAll();
			return usuarios;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
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
			System.out.println("Atualizado com Sucesso!");
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public void excluir(int id) throws SQLException {
		if (id == 0) {
			throw new IllegalStateException("Id da conta nao deve ser nula.");
		}

		try {
			System.out.println(id);
			Usuario user = em.find(Usuario.class, id);
			System.out.println(user);
			em.remove(user);
			System.out.println(em.find(Usuario.class,  id));
			em.getTransaction().commit();
			em.close();
			System.out.println("Excluido com sucesso!");
		}catch(Exception e){
			throw new RuntimeException(e);
		}

	}

}