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

	public void inserir(Usuario cliente) {
		String sql = "INSERT INTO usuarios (nome, sobrenome) VALUES (?, ?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSobrenome());
			stmt.execute();
			stmt.close();
			this.con.close();
			System.out.println("Dados inseridos com sucesso!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Usuario> listar() throws SQLException {
		List<Usuario> clientes = new ArrayList<>();
		String sql = "SELECT * FROM usuarios";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String sobrenome = rs.getString("sobrenome");
					Usuario cliente = new Usuario(nome, 7, sobrenome, id);
					cliente.setId(id);
					clientes.add(cliente);

				}
				rs.close();
			}
			stmt.close();
			this.con.close();
		}
		return clientes;
	}

	public void atualizar(Usuario cliente) throws SQLException {
		String sql = "UPDATE usuarios SET nome = ?, sobrenome = ? WHERE id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSobrenome());
			stmt.setInt(3, (int) cliente.getId());
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