package com.example.strategies;

import com.example.models.Conteudo;

public interface ExercicioStrategy {
	public long getId();
	public void setId(long id);

	public Conteudo getConteudo();
	public void setConteudo(Conteudo conteudo);

	public String getPergunta();
	public void setPergunta(String pergunta);

	public String getDificuldade();
	public void setDificuldade(String dificuldade);
}
