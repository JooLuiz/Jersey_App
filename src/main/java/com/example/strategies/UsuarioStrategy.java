package com.example.strategies;

public interface UsuarioStrategy {
	
	public long getId();
	public void setId(long id);

	public String getNome();
	public void setNome(String nome);

	public int getIdade();
	public void setIdade(int idade);

	public String getSobrenome();
	public void setSobrenome(String sobrenome);
	
	public String getUsuario();
	public void setUsuario(String usuario);

	public String getSenha();
	public void setSenha(String senha);
}
