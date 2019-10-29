package com.example.strategies;

import java.util.Set;

import com.example.models.Aula;

public interface MateriaStrategy {

	public long getId();
	public void setId(long id);

	public String getDescricao();
	public void setDescricao(String descricao);

	public String getAtiva();
	public void setAtiva(String ativa);
	
	public Set<Aula> getAulas();
	public void setAulas(Set<Aula> aulas);
}
