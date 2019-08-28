package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	public static String status = "Não conectou...";
	        public ConnectionDB() {
	 
	    }
	public static java.sql.Connection getConexaoMySQL() {
		Connection connection = null;
		try {
			String driverName = "com.mysql.cj.jdbc.Driver";                        
	 
			Class.forName(driverName);

	        String serverName = "localhost";
	 
	        String mydatabase = "crud";
	 
	        String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?serverTimezone=UTC";
	 
	        String username = "root";
	 
	        String password = "";
	 
	        connection = DriverManager.getConnection(url, username, password);
	 
	        if (connection != null) {
	 
	            status = ("STATUS--->Conectado com sucesso!");
	 
	        } else {
	 
	            status = ("STATUS--->Não foi possivel realizar conexão");
	 
	        }

	        return connection;
	 
	        } catch (ClassNotFoundException e) {

	            System.out.println("O driver expecificado nao foi encontrado.");
	 
	            return null;
	 
	        } catch (SQLException e) {
	        	System.out.println(e);
	            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
	 
	            return null;
	        }
	
	    }

	    public static String statusConection() {
	        return status;
	    }
	 
	    public static boolean FecharConexao() {
	        try {
	        	ConnectionDB.getConexaoMySQL().close();
	            return true;
	        } catch (SQLException e) {
	            return false;
	        }
	    }
	 
	    public static java.sql.Connection ReiniciarConexao() {
	        FecharConexao();
	        return ConnectionDB.getConexaoMySQL();
	    }
}