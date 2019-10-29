package com.example.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.strategies.AulaStrategy;

@Entity
public class Aula implements AulaStrategy{
	
	public Aula(Materia materia, String descricao, String situacao, Set<Conteudo> conteudos) {
		this.materia = materia;
		this.descricao = descricao;
		this.situacao = situacao;
		this.conteudos = conteudos;
	}

	public Aula(Materia materia, String descricao, String situacao) {
		this.materia = materia;
		this.descricao = descricao;
		this.situacao = situacao;
	}

	public Aula(Materia materia, String descricao) {
		this.materia = materia;
		this.descricao = descricao;
	}

	@Id
    @Column(name = "id", updatable = false, nullable = false)
	public long id;
	
	@ManyToOne
	@JoinColumn(name = "id_materia", nullable = false)
    public Materia materia;
	
	@Column(name = "descricao")
	public String descricao;

	@Column(name = "situacao")
	public String situacao = "P";
	
	@OneToMany(mappedBy="aula")
    private Set<Conteudo> conteudos;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Materia getMateria() {
		return materia;
	}

	@Override
	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getSituacao() {
		return situacao;
	}

	@Override
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public Set<Conteudo> getConteudos() {
		return conteudos;
	}

	@Override
	public void setConteudos(Set<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}
}
