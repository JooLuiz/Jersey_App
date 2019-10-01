package com.example;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.NotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UsuarioService {
	
	private UsuarioResourceClient dao;
	private ObjectMapper mapper ;
	
	public UsuarioService(){
		dao = new UsuarioResourceClient();
		mapper = new ObjectMapper();
	}
	
	public List<Usuario> fetchAll() {
		try {
			List<Usuario> usuarios = dao.listar();
			
			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario fetchBy(long id) throws NotFoundException {
		try {
			Usuario usuario = dao.listarUm(id);
			
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void create(Usuario usuario) {
		try {
			dao.inserir(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(long id, Usuario usuario) {
		try {
			dao.atualizar(id, usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(long id) throws NotFoundException {
		try {
			dao.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}