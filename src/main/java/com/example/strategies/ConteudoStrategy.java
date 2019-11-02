package com.example.strategies;

import java.util.Set;

import com.example.models.Aula;
import com.example.models.Exercicio;

public interface ConteudoStrategy {
	public long getId();
	public void setId(long id);

	public Aula getAula();
	public void setAula(Aula aula);

	public String getConteudo();
	public void setConteudo(String conteudo);
	
	public Set<Exercicio> getExercicios();
	public void setExercicios(Set<Exercicio> exercicios);
}
