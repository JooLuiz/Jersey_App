package com.example.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationController{
	public UsuarioController usuarioController;
	public EntityManagerFactory emf;
	public EntityManager em;
	
	public ApplicationController() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		em = emf.createEntityManager();
		usuarioController = new UsuarioController(em);
	}

}
