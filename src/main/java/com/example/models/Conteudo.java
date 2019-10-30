package com.example.models;

import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.strategies.ConteudoStrategy;

@Entity
public class Conteudo implements ConteudoStrategy{
	
	public Conteudo(Aula aula, String conteudo, Set<Exercicio> exercicios) {
		this.aula = aula;
		this.conteudo = conteudo;
		this.exercicios = exercicios;
	}

	public Conteudo(Aula aula, String conteudo) {
		this.aula = aula;
		this.conteudo = conteudo;
	}
	
	public Conteudo() {
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
    public Set<Exercicio> exercicios;

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
	@JsonbTransient
	public String getConteudo() {
		return conteudo;
	}

	@Override
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	@Override
	@JsonbTransient
	public Set<Exercicio> getExercicios() {
		return exercicios;
	}

	@Override
	public void setExercicios(Set<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}
}
