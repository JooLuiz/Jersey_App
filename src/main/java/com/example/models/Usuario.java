package com.example.models;

import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.strategies.UsuarioStrategy;

@Entity
public class Usuario implements UsuarioStrategy {

	public Usuario(String nome, String sobrenome, int idade) {
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

	@OneToMany(mappedBy = "materia")
	public Set<MateriaUsuario> materiaUsuarios;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int getIdade() {
		return idade;
	}

	@Override
	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String getSobrenome() {
		return sobrenome;
	}

	@Override
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Override
	public String getUsuario() {
		return usuario;
	}

	@Override
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String getSenha() {
		return senha;
	}

	@Override
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@JsonbTransient
	public Set<MateriaUsuario> getMateriaUsuarios() {
		return materiaUsuarios;
	}

	public void setMateriaUsuarios(Set<MateriaUsuario> materiaUsuarios) {
		this.materiaUsuarios = materiaUsuarios;
	}

}
