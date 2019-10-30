package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.Resposta;
import com.example.state.IState;

public class RespostaController implements IState<List<Resposta>> {

	public EntityManagerFactory emf;
	private EntityManager em;
	private List<Resposta> state;

	public RespostaController(EntityManager Em) {
		em = Em;
		state = this.listar();
	}
	
	public RespostaController(){
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}
	
	@Override
	public List<Resposta> setState(List<Resposta> listResposta){
		try{
			this.state = listResposta;
			return this.state;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	};
	
	@Override
	public List<Resposta> getState(){
    	return state;
    };
	
	public Resposta listarUm(long id) {
		try {
			Resposta resposta = em.find(Resposta.class, id);
			em.close();
			return resposta;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Resposta> listar() {
		List<Resposta> resposta = new ArrayList<>();
		String query = "SELECT u FROM Resposta u WHERE u.id IS NOT NULL";
		TypedQuery<Resposta> tq = em.createQuery(query, Resposta.class);
		try {
			resposta = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resposta;
	}
	
	public void inserir(Resposta resposta) {
		try {
			em.persist(resposta);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Resposta resp) {
		try {
			Resposta resposta = em.find(Resposta.class, id);
			resposta.setExercicio(resp.exercicio);
			resposta.setDescricao(resp.descricao);
			resposta.setCorreta(resp.correta);
			em.merge(resposta);
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
			Resposta resposta = em.find(Resposta.class, id);
			em.remove(resposta);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}