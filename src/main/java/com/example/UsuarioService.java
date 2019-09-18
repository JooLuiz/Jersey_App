package com.example;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.NotFoundException;

public class UsuarioService {
	
	public List<Usuario> fetchAll() {
		try {
			UsuarioResourceClient dao = new UsuarioResourceClient();
			List<Usuario> usuarios = dao.listar();
			
			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario fetchBy(long id) throws NotFoundException {
		try {
			UsuarioResourceClient dao = new UsuarioResourceClient();
			Usuario usuario = dao.listarUm(id);
			
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void create(Usuario usuario) {
		try {
			UsuarioResourceClient dao = new UsuarioResourceClient();
			System.out.println(dao);
			dao.inserir(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(long id, Usuario usuario) {
		try {
			UsuarioResourceClient dao = new UsuarioResourceClient();
			dao.atualizar(id, usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(long id) throws NotFoundException {
		try {
			UsuarioResourceClient dao = new UsuarioResourceClient();
			dao.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}