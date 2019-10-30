package com.example.models;

import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.strategies.ExercicioStrategy;

@Entity
public class Exercicio implements ExercicioStrategy{
	
	public Exercicio(Conteudo conteudo, String pergunta, String dificuldade, Set<Resposta> resposta) {
		this.conteudo = conteudo;
		this.pergunta = pergunta;
		this.dificuldade = dificuldade;
		this.resposta = resposta;
	}

	public Exercicio(Conteudo conteudo, String pergunta, String dificuldade) {
		this.conteudo = conteudo;
		this.pergunta = pergunta;
		this.dificuldade = dificuldade;
	}

	public Exercicio(Conteudo conteudo, String pergunta) {
		this.conteudo = conteudo;
		this.pergunta = pergunta;
	}

	public Exercicio() {
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
    public Set<Resposta> resposta;

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

	@Override
	@JsonbTransient
	public Set<Resposta> getResposta() {
		return resposta;
	}

	@Override
	public void setResposta(Set<Resposta> resposta) {
		this.resposta = resposta;
	}
}
