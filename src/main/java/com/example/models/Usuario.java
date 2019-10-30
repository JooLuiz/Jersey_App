package com.example.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.strategies.UsuarioStrategy;

@Entity
public class Usuario implements UsuarioStrategy{

	public Usuario(String nome, String sobrenome, int idade){
		this.nome = nome;
		this.idade = idade;
		this.sobrenome = sobrenome;
	}
	
	public Usuario() {
	}
	
	@Id
    @Column(name = "id", updatable = false, nullable = false)
	public long id;
	
	@Column(name = "nome")
	public String nome;

	@Column(name = "usuario")
	public String usuario;
	
	@Column(name = "senha")
	public String senha;
	
	@Column(name = "idade")
	public int idade;
	
	@Column(name = "sobrenome")
	public String sobrenome;
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	@JsonbTransient
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	@JsonbTransient
	public int getIdade() {
		return idade;
	}

	@Override
	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	@JsonbTransient
	public String getSobrenome() {
		return sobrenome;
	}

	@Override
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	@Override
	@JsonbTransient
	public String getUsuario() {
		return usuario;
	}

	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	@JsonbTransient
	public String getSenha() {
		return senha;
	}

	@Override
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
