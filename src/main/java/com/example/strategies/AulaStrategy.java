package com.example.strategies;

import java.util.Set;

import com.example.models.Conteudo;
import com.example.models.Materia;

public interface AulaStrategy {
	public long getId();
	public void setId(long id);

	public Materia getMateria();
	public void setMateria(Materia materia);

	public String getDescricao();
	public void setDescricao(String descricao);

	public String getSituacao();
	public void setSituacao(String situacao);
	
	public Set<Conteudo> getConteudos();
	public void setConteudos(Set<Conteudo> conteudos);
}
