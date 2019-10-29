package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.models.Aula;
import com.example.state.IState;

public class AulaController implements IState<List<Aula>> {

	private EntityManager em;
	private List<Aula> state;

	public AulaController(EntityManager Em) {
		em = Em;
		state = this.listar();
	}
	
	@Override
	public List<Aula> setState(List<Aula> listAula){
		try{
			this.state = listAula;
			return this.state;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	};
	
	@Override
	public List<Aula> getState(){
    	return state;
    };
	
	public Aula listarUm(long id) {
		try {
			Aula aula = em.find(Aula.class, id);
			em.close();
			return aula;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Aula> listar() {
		List<Aula> aula = new ArrayList<>();
		String query = "SELECT u FROM Aula u WHERE u.id IS NOT NULL";
		TypedQuery<Aula> tq = em.createQuery(query, Aula.class);
		try {
			aula = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aula;
	}
	
	public void inserir(Aula aula) {
		try {
			em.persist(aula);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Aula al) {
		try {
			Aula aula = em.find(Aula.class, id);
			aula.setMateria(al.materia);
			aula.setDescricao(al.descricao);
			aula.setSituacao(al.situacao);
			aula.setConteudos(al.conteudos);
			em.merge(aula);
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
			Aula aula = em.find(Aula.class, id);
			em.remove(aula);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}