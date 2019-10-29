package com.example.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationController{
	public UsuarioController usuarioController;
	public MateriaController materiaController;
	public MateriaUsuarioController materiaUsuarioController;
	public AulaController aulaController;
	public EntityManagerFactory emf;
	public EntityManager em;
	
	public ApplicationController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
		usuarioController = new UsuarioController(em);
		materiaController = new MateriaController(em);
		materiaUsuarioController = new MateriaUsuarioController(em);
		aulaController = new AulaController(em);
	}

}
