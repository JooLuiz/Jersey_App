package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.models.Materia;
import com.example.state.IState;

public class MateriaController implements IState<List<Materia>> {

	private EntityManager em;
	private List<Materia> state;

	public MateriaController(EntityManager Em) {
		em = Em;
		state = this.listar();
	}
	
	@Override
	public List<Materia> setState(List<Materia> listMateria){
		try{
			this.state = listMateria;
			return this.state;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	};
	
	@Override
	public List<Materia> getState(){
    	return state;
    };
	
	public Materia listarUm(long id) {
		try {
			Materia materia = em.find(Materia.class, id);
			em.close();
			return materia;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Materia> listar() {
		List<Materia> materia = new ArrayList<>();
		String query = "SELECT u FROM Materia u WHERE u.id IS NOT NULL";
		TypedQuery<Materia> tq = em.createQuery(query, Materia.class);
		try {
			materia = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return materia;
	}
	
	public void inserir(Materia materia) {
		try {
			em.persist(materia);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, Materia mat) {
		try {
			Materia materia = em.find(Materia.class, id);
			materia.setDescricao(mat.descricao);
			materia.setAtiva(mat.ativa);
			materia.setAulas(mat.aulas);
			em.merge(materia);
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
			Materia materia = em.find(Materia.class, id);
			em.remove(materia);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}