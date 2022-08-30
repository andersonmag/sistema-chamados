package com.bwn.sistema.chamados.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.bwn.sistema.chamados.model.Chamado;

public class ConexaoDBTest {

	@Inject
	private ChamadoDAO chamadoDAO;

	@Before
	public void setUp(){}
	
	@Test
	public void deveRetornarListaNaoNula() {
		List<Chamado> chamados = chamadoDAO.buscarTodos();
		assertTrue(chamados.size() > 0);
	}

}
