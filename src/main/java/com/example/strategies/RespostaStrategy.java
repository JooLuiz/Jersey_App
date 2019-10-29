package com.example.strategies;

import com.example.models.Exercicio;

public interface RespostaStrategy {
	public long getId();
	public void setId(long id);

	public Exercicio getExercicio();
	public void setExercicio(Exercicio exercicio);

	public String getDescricao();
	public void setDescricao(String descricao);

	public String getCorreta();
	public void setCorreta(String correta);
}
