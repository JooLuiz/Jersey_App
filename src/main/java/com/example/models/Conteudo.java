package com.example.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.strategies.ConteudoStrategy;

public class Conteudo implements ConteudoStrategy{
	
	public Conteudo(Aula aula, String conteudo) {
		this.aula = aula;
		this.conteudo = conteudo;
	}

	@Id
    @Column(name = "id", updatable = false, nullable = false)
	public long id;
	
	@ManyToOne
	@JoinColumn(name = "id_aula", nullable = false)
    public Aula aula;
	
	@Column(name = "conteudo")
	public String conteudo;
	
	@OneToMany(mappedBy="conteudo")
    private Set<Exercicio> exercicios;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Aula getAula() {
		return aula;
	}

	@Override
	public void setAula(Aula aula) {
		this.aula = aula;
	}

	@Override
	public String getConteudo() {
		return conteudo;
	}

	@Override
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	@Override
	public Set<Exercicio> getExercicios() {
		return exercicios;
	}

	@Override
	public void setExercicios(Set<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}
}
