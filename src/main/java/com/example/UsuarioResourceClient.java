package com.example;

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

public class UsuarioResourceClient {

	private EntityManagerFactory emf;
	private EntityManager em;
	private HttpAuthenticationFeature feature;
	private ClientConfig clientConfig;
	private Client client;
	private String username;
	private String password;

	public UsuarioResourceClient() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		clientConfig = new ClientConfig();
		clientConfig.register(Usuario.class);
		client = ClientBuilder.newClient(clientConfig);
		feature = HttpAuthenticationFeature.basicBuilder().build();
	}
	
	public void login(String username, String password){
		Response response = client.target("http://localhost:8080/users/login").request()
			    .property("username", username)
			    .property("senha", password).get();
		System.out.println(response);
		if(response.getStatus() == 200)
	    {
			this.password = password;
			this.username = username;
	    }
	}

	public void inserir(Usuario usuario) {
		Response response = client.target("http://localhost:8080/rest/homer/contact").request()
			    .property("username", this.username)
			    .property("senha", this.password).get();
		if(response.getStatus() == 200)
	    {
			try {
				em.persist(usuario);
				em.getTransaction().commit();
				em.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			user.setUsuario(usuario.usuario);
			user.setSenha(usuario.senha);
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