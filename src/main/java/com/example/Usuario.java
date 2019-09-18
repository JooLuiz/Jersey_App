package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
    @Column(name = "id", updatable = false, nullable = false)
	long id;
	
	@Column(name = "nome")
	String nome;

	@Column(name = "usuario")
	String usuario;
	
	@Column(name = "senha")
	String senha;
	
	@Column(name = "idade")
	int idade;
	
	@Column(name = "sobrenome")
	String sobrenome;
	
	public Usuario(String nome, String sobrenome, int idade){
		this.nome = nome;
		this.idade = idade;
		this.sobrenome = sobrenome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario() {
		this.nome = null;
		this.idade = 0;
		this.sobrenome = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
