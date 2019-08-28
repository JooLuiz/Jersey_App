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
		try (Connection con = ConnectionDB.getConexaoMySQL()) {
			UsuarioResourceClient dao = new UsuarioResourceClient(con);
			Usuario cliente = dao.listarUm(id);
			
			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void create(String nome, String sobrenome, int idade) {
		try (Connection con = ConnectionDB.getConexaoMySQL()) {
			UsuarioResourceClient dao = new UsuarioResourceClient(con);
			Usuario usuario = new Usuario(nome, sobrenome, idade);
			dao.inserir(usuario);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
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