package com.example.strategies;

import java.util.Set;

import com.example.models.Conteudo;
import com.example.models.Resposta;

public interface ExercicioStrategy {
	public long getId();
	public void setId(long id);

	public Conteudo getConteudo();
	public void setConteudo(Conteudo conteudo);

	public String getPergunta();
	public void setPergunta(String pergunta);

	public String getDificuldade();
	public void setDificuldade(String dificuldade);

	public Set<Resposta> getResposta();
	public void setResposta(Set<Resposta> resposta);
}
