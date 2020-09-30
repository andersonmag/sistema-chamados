package com.bwn.sistema.chamados.model;

import com.bwn.sistema.chamados.enums.TipoUsuario;

public class Usuario {

	private Long id;
	private String nome;
	private TipoUsuario tipoUsuario;

	public Usuario() {}

	public Usuario(Long id, String nome, TipoUsuario tipoUsuario) {
		this.id = id;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
