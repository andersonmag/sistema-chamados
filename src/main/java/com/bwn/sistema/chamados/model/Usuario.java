package com.bwn.sistema.chamados.model;

import com.bwn.sistema.chamados.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	private Long id;
	private String nome;
	private TipoUsuario tipoUsuario;
}
