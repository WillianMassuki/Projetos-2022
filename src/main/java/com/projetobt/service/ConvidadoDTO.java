package com.projetobt.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ConvidadoDTO {
	private Long codigo;
	private String nomeConvidado;
	private String rg;
	private Long eventoCodigo;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	public Long getEventoCodigo() {
		return eventoCodigo;
	}
	public void setEventoCodigo(Long eventoCodigo) {
		this.eventoCodigo = eventoCodigo;
	}
	
	

}
