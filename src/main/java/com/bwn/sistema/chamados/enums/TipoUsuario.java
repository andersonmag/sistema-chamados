package com.bwn.sistema.chamados.enums;

public enum TipoUsuario {

	CLIENTE("CLIENTE"), FUNCIONARIO("FUNCIONARIO");

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private TipoUsuario(String tipo) {
		this.tipo = tipo;
	}
}
