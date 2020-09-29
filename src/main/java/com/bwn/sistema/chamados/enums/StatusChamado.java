package com.bwn.sistema.chamados.enums;

public enum StatusChamado {

	PENDENTE("Pendente"), EM_ANDAMENTO("Em Andamento"), FINALIZADO("Finalizado");

	private String status;

	private StatusChamado(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}
