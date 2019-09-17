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
			List<Usuario> usuarios = dao.listar();
			
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
}
	}

	public Usuario fetchBy(long id) throws NotFoundException {
		try (Connection con = ConnectionDB.getConexaoMySQL()) {
			UsuarioResourceClient dao = new UsuarioResourceClient(con);
			Usuario usuario = dao.listarUm(id);
			
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void create(Usuario usuario) {
		try (Connection con = ConnectionDB.getConexaoMySQL()) {
			UsuarioResourceClient dao = new UsuarioResourceClient(con);
			System.out.println(dao);
			dao.inserir(usuario);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void update(long id, Usuario usuario) {
		try (Connection con = ConnectionDB.getConexaoMySQL()) {
			UsuarioResourceClient dao = new UsuarioResourceClient(con);
			dao.atualizar(id, usuario);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(long id) throws NotFoundException {
		try (Connection con = ConnectionDB.getConexaoMySQL()) {
			UsuarioResourceClient dao = new UsuarioResourceClient(con);
			dao.excluir(id);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}