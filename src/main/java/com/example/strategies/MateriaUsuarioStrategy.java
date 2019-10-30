package com.example.strategies;

import com.example.models.Materia;
import com.example.models.Usuario;

public interface MateriaUsuarioStrategy {

	public long getId();
	public void setId(long id);
	
	public Materia getMateria();
	public void setMateria(Materia materia);

	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);

	public String getAno();
	public void setAno(String ano);

	public String getSituacao();
	public void setSituacao(String situacao);
}
