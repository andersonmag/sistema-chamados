package com.bwn.sistema.chamados.model;

import java.util.Date;
import com.bwn.sistema.chamados.enums.StatusChamado;

public class Chamado {

	private Long id;
	private String titulo;
	private String descricao;
	private Usuario cliente;

	private String observacao;
	private Date date;
	private StatusChamado status;

	public Chamado() {}

	public Chamado(Long id, String titulo, String descricao, Usuario cliente, String observacao, Date date,
			StatusChamado status) {

		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.cliente = cliente;
		this.observacao = observacao;
		this.date = date;
		this.status = status;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public StatusChamado getStatus() {
		return status;
	}
	public void setStatus(StatusChamado status) {
		this.status = status;
	}

}
