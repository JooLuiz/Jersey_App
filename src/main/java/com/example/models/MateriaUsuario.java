package com.example.models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.strategies.MateriaUsuarioStrategy;

@Entity
@Table(name = "materia_usuario")
public class MateriaUsuario implements MateriaUsuarioStrategy{

	public MateriaUsuario(Materia materia, Usuario usuario, String ano, String situacao) {
		this.materia = materia;
		this.usuario = usuario;
		this.ano = ano;
		this.situacao = situacao;
	}
	
	public MateriaUsuario(Materia materia, Usuario usuario, String ano) {
		this.materia = materia;
		this.usuario = usuario;
		this.ano = ano;
	}
	
	public MateriaUsuario() {
	}
	
	@Id
    @Column(name = "id_materia_usuario", updatable = false, nullable = false)
	public long id;
	
	@JoinColumn(name = "id_materia", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    public Materia materia;
	
	@JoinColumn(name = "id_usuario", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
	public Usuario usuario;

	@Column(name = "ano")
	public String ano;

	@Column(name = "situacao")
	public String situacao = "A";

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
	public Materia getMateria() {
		return materia;
	}

	@Override
	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@Override
	@JsonbTransient
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String getAno() {
		return ano;
	}

	@Override
	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public String getSituacao() {
		return situacao;
	}

	@Override
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
