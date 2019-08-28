package com.example;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {
	
	long id;
	String nome;
	int idade;
	String sobrenome;
	
	public Usuario(String nome, int idade, String sobrenome, long id){
		this.id = id;
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
	
}
