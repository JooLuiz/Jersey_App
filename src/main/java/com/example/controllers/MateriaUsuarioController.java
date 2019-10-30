package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.models.MateriaUsuario;
import com.example.state.IState;

public class MateriaUsuarioController implements IState<List<MateriaUsuario>> {

	public EntityManagerFactory emf;
	private EntityManager em;
	private List<MateriaUsuario> state;

	public MateriaUsuarioController(EntityManager Em) {
		em = Em;
		state = this.listar();
	}
	
	public MateriaUsuarioController(){
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
	}
	
	@Override
	public List<MateriaUsuario> setState(List<MateriaUsuario> listMateriaUsuario){
		try{
			this.state = listMateriaUsuario;
			return this.state;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	};
	
	@Override
	public List<MateriaUsuario> getState(){
    	return state;
    };
	
	public MateriaUsuario listarUm(long id) {
		try {
			MateriaUsuario materiaUsuario = em.find(MateriaUsuario.class, id);
			em.close();
			return materiaUsuario;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<MateriaUsuario> listar() {
		List<MateriaUsuario> materiaUsuario = new ArrayList<>();
		String query = "SELECT u FROM MateriaUsuario u WHERE u.id IS NOT NULL";
		TypedQuery<MateriaUsuario> tq = em.createQuery(query, MateriaUsuario.class);
		try {
			materiaUsuario = tq.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return materiaUsuario;
	}
	
	public void inserir(MateriaUsuario materiaUsuario) {
		try {
			em.persist(materiaUsuario);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(long id, MateriaUsuario matUser) {
		try {
			MateriaUsuario materiaUsuario = em.find(MateriaUsuario.class, id);
			materiaUsuario.setMateria(matUser.materia);
			materiaUsuario.setUsuario(matUser.usuario);
			materiaUsuario.setAno(matUser.ano);
			materiaUsuario.setSituacao(matUser.situacao);
			em.merge(materiaUsuario);
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
			MateriaUsuario materiaUsuario = em.find(MateriaUsuario.class, id);
			em.remove(materiaUsuario);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}