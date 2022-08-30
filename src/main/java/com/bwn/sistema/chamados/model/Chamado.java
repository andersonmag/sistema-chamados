package com.bwn.sistema.chamados.model;

import java.time.LocalDate;

import com.bwn.sistema.chamados.enums.StatusChamado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chamado {

	private Long id;
	private String titulo;
	private String descricao;
	private Usuario cliente;

	private Usuario funcionario;
	private String observacao;
	
	private LocalDate data;
	private StatusChamado status;
	
	{
	    data = LocalDate.now();
	    status = StatusChamado.PENDENTE;
	}

}
