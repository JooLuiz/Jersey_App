package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

public class UsuarioService {

	private List<Usuario> users = new ArrayList<Usuario>();

	public List<Usuario> fetchAll() {
		try (Connection con = ConnectionDB.getConexaoMySQL()) {
			UsuarioResourceClient dao = new UsuarioResourceClient(con);
			List<Usuario> clientes = dao.listar();
			
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
}
	}

	public Usuario fetchBy(long id) throws NotFoundException {
		for (Usuario user2 :  fetchAll()) {
			if (id == user2.getId()) {
				return user2;
			}else{
				throw new NotFoundException("Resource not found with Id :: " + id);
			}
		}
		return null;
	}

	public void create(Usuario user) {
		users.add(user);
	}

	public void update(Usuario user) {
		for (Usuario user2 : users) {
			if (user.getId() == user2.getId()) {
				users.remove(user2);
				users.add(user);
			}
		}
	}

	public void delete(long id) throws NotFoundException {
		// delete operation
	}
}