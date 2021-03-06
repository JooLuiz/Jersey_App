package com.example.models;

import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.strategies.MateriaStrategy;

@Entity
public class Materia implements MateriaStrategy {

	public Materia(String descricao, String ativa, Set<Aula> aulas) {
		this.descricao = descricao;
		this.ativa = ativa;
		this.aulas = aulas;
	}

	public Materia() {
	}

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	public long id;

	@Column(name = "descricao")
	public String descricao;

	@Column(name = "ativa")
	public String ativa = "S";

	@OneToMany(mappedBy = "materia")
	public Set<Aula> aulas;

	@OneToMany(mappedBy = "materia")
	public Set<MateriaUsuario> materiaUsuarios;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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
	public String getAtiva() {
		return ativa;
	}

	@Override
	public void setAtiva(String ativa) {
		this.ativa = ativa;
	}

	@Override
	@JsonbTransient
	public Set<Aula> getAulas() {
		return aulas;
	}

	@Override
	public void setAulas(Set<Aula> aulas) {
		this.aulas = aulas;
	}

	@JsonbTransient
	public Set<MateriaUsuario> getMateriaUsuarios() {
		return materiaUsuarios;
	}

	public void setMateriaUsuarios(Set<MateriaUsuario> materiaUsuarios) {
		this.materiaUsuarios = materiaUsuarios;
	}
}
