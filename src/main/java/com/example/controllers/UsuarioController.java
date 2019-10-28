package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.example.models.Usuario;
import com.example.state.IState;

public class UsuarioController implements IState<List<Usuario>> {

	private EntityManager em;
	private List<Usuario> state;

	public UsuarioController(EntityManager Em) {
		em = Em;
		state = this.listar();
	}
	
	@Override
	public List<Usuario> setState(List<Usuario> listUsuario){
		try{
			this.state = listUsuario;
			return this.state;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	};
	
	@Override
	public List<Usuario> getState(){
    	return state;
    };
	
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