package com.example.models;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.strategies.RespostaStrategy;

public class Resposta implements RespostaStrategy{
	
	public Resposta(Exercicio exercicio, String descricao, String correta) {
		super();
		this.exercicio = exercicio;
		this.descricao = descricao;
		this.correta = correta;
	}

	public Resposta(Exercicio exercicio, String descricao) {
		super();
		this.exercicio = exercicio;
		this.descricao = descricao;
	}

	@Id
    @Column(name = "id", updatable = false, nullable = false)
	public long id;
	
	@ManyToOne
	@JoinColumn(name = "id_exercicio", nullable = false)
    public Exercicio exercicio;
	
	@Column(name = "descricao")
	public String descricao;

	@Column(name = "correta")
	public String correta = "N";

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Exercicio getExercicio() {
		return exercicio;
	}

	@Override
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
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
	public String getCorreta() {
		return correta;
	}

	@Override
	public void setCorreta(String correta) {
		this.correta = correta;
	}
	
}
