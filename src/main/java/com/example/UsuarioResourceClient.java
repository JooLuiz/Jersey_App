package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioResourceClient {

	private Connection con;

	public UsuarioResourceClient(Connection con) {
		this.con = con;

	}

	public void inserir(Usuario usuario) {
		String sql = "INSERT INTO usuarios (nome, sobrenome, idade) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSobrenome());
			stmt.setInt(3, usuario.getIdade());
			stmt.execute();
			stmt.close();
			this.con.close();
			System.out.println("Dados inseridos com sucesso!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public Usuario listarUm(long usuarioid) throws SQLException {
		Usuario usuarioRetorno = new Usuario();
		String sql = "SELECT * FROM usuarios where id = " + usuarioid;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String sobrenome = rs.getString("sobrenome");
					Usuario usuario = new Usuario(nome, sobrenome, 7);
					usuario.setId(id);
					usuarioRetorno = usuario;
				}
				rs.close();
			}
			stmt.close();
			this.con.close();
		}
		return usuarioRetorno;
	}

	public List<Usuario> listar() throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuarios";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String sobrenome = rs.getString("sobrenome");
					Usuario usuario = new Usuario(nome, sobrenome, 7);
					usuario.setId(id);
					usuarios.add(usuario);
				}
				rs.close();
			}
			stmt.close();
			this.con.close();
		}
		return usuarios;
	}

	public void atualizar(long id, Usuario usuario) throws SQLException {
		String sql = "UPDATE usuarios SET nome = ?, sobrenome = ?, idade = ? WHERE id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSobrenome());
			stmt.setInt(3, usuario.getIdade());
			stmt.setInt(4, (int)id);
			stmt.execute();
			stmt.close();
			this.con.close();
			System.out.println("Atualizado com Sucesso!");
		}
	}

	public void excluir(int id) throws SQLException {
		if (id == 0) {
			throw new IllegalStateException("Id da conta nao deve ser nula.");
		}

		String sql = "DELETE FROM usuarios WHERE id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
			this.con.close();
			System.out.println("Excluido com sucesso!");
		}

	}

}