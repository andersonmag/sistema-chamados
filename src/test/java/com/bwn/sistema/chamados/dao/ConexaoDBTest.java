package com.bwn.sistema.chamados.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bwn.sistema.chamados.model.Chamado;

import static org.junit.Assert.assertTrue;


public class ConexaoDBTest {

	private Connection connection;
	private ChamadoDAO chamadoDAO;

	@Before
	public void setUp() throws ClassNotFoundException {
		this.connection = new ConexaoDB().getConnection();
		this.chamadoDAO = new ChamadoDAO();
	}

	@Test
	public void deveConectar() throws SQLException {
		assertTrue(this.connection.isValid(100));
	}
	
	@Test
	public void deveRetornarListaNaoNula() {
		List<Chamado> chamados = chamadoDAO.buscarTodos();
		System.out.println(chamados.size());
	}

}
