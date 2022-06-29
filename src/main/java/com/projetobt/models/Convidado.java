package com.projetobt.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projetobt.models.Evento;

@Entity
public class Convidado implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Convidadocodigo;
	
	@Column
	private String nomeConvidado;
	
	@Column
	private String rg;
	
	@ManyToOne
	@JoinColumn(name = "codigo")
	private Evento  evento;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Long getCodigo() {
		return Convidadocodigo;
	}

	public void setCodigo(Long codigo) {
		this.Convidadocodigo = codigo;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

}
