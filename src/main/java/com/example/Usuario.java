package com.example;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {
	
	String nome;
	int idade;
	String sobrenome;
	
	Usuario(String nome, int idade, String sobrenome){
		this.nome = nome;
		this.idade = idade;
		this.sobrenome = sobrenome;
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
