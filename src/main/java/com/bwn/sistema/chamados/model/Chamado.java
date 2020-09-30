package com.bwn.sistema.chamados.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.bwn.sistema.chamados.enums.StatusChamado;

public class Chamado {

	private Long id;
	private String titulo;
	private String descricao;
	private Usuario cliente;

	private Usuario funcionario;
	private String observacao;
	private LocalDate data = LocalDate.now();
	private StatusChamado status = StatusChamado.PENDENTE;

	public Chamado() {}
	
	public Chamado(Long id, String titulo, String descricao, Usuario cliente, 
			LocalDate data, StatusChamado status, String observacao) {
		
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.cliente = cliente;
		this.data = data;
		this.status = status;
		this.observacao = observacao;
	}

	public Chamado(Long id, String titulo, String descricao, Usuario cliente, 
			String observacao, StatusChamado status) {

		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.cliente = cliente;
		this.observacao = observacao;
		this.status = status;
	}
	
	public String converterData() {
		return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
	
	public Usuario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Usuario funcionario) {
		this.funcionario = funcionario;
	}
	
	public LocalDate getData() {
		return data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public StatusChamado getStatus() {
		return status;
	}

	public void setStatus(StatusChamado status) {
		this.status = status;
	}

}
