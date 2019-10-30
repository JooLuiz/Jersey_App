package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.Conteudo;
import com.example.state.IState;

public class ConteudoController implements IState<List<Conteudo>> {

	public EntityManagerFactory emf;
	private EntityManager em;
	private List<Conteudo> state;

	public ConteudoController(EntityManager Em) {
		em = Em;
		state = this.listar();
	}
	
	public ConteudoController(){
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}
	
	@Override
	public List<Conteudo> setState(List<Conteudo> listConteudo){
		try{
			this.state = listConteudo;
			return this.state;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	};
	
	@Override
	public List<Conteudo> getState(){
    	return state;
    };
	
	public Conteudo listarUm(long id) {
		try {
			Conteudo conteudo = em.find(Conteudo.class, id);
			em.close();
			return conteudo;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Conteudo> listar() {
		List<Conteudo> conteudo = new ArrayList<>();
		String query = "SELECT u FROM Conteudo u WHERE u.id IS NOT NULL";
		TypedQuery<Conteudo> tq = em.createQuery(query, Conteudo.class);
		try {
			conteudo = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conteudo;
	}
	
	public void inserir(Conteudo conteudo) {
		try {
			em.persist(conteudo);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Conteudo cont) {
		try {
			Conteudo conteudo = em.find(Conteudo.class, id);
			conteudo.setAula(cont.aula);
			conteudo.setConteudo(cont.conteudo);
			conteudo.setExercicios(cont.exercicios);
			em.merge(conteudo);
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
			Conteudo conteudo = em.find(Conteudo.class, id);
			em.remove(conteudo);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}