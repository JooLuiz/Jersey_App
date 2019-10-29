package com.example.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.strategies.ExercicioStrategy;

public class Exercicio implements ExercicioStrategy{
	
	public Exercicio(Conteudo conteudo, String pergunta, String dificuldade) {
		this.conteudo = conteudo;
		this.pergunta = pergunta;
		this.dificuldade = dificuldade;
	}

	public Exercicio(Conteudo conteudo, String pergunta) {
		super();
		this.conteudo = conteudo;
		this.pergunta = pergunta;
	}

	@Id
    @Column(name = "id", updatable = false, nullable = false)
	public long id;
	
	@ManyToOne
	@JoinColumn(name = "id_conteudo", nullable = false)
    public Conteudo conteudo;
	
	@Column(name = "pergunta")
	public String pergunta;

	@Column(name = "dificuldade")
	public String dificuldade = "F";
	
	@OneToMany(mappedBy="exercicio")
    private Set<Resposta> resposta;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Conteudo getConteudo() {
		return conteudo;
	}

	@Override
	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public String getPergunta() {
		return pergunta;
	}

	@Override
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	@Override
	public String getDificuldade() {
		return dificuldade;
	}

	@Override
	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}
}
