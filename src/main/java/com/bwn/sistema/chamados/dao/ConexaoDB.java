package com.bwn.sistema.chamados.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

public class ConexaoDB {

	private Connection connection;
	private String url = "jdbc:postgresql://localhost:5432/call_chamados";
	private String user = "postgres";
	private String password = "admin";

	@Produces
	@RequestScoped
	public Connection getConnection() throws ClassNotFoundException {

		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(url, user, password);
			this.connection.setAutoCommit(false);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.connection;
	}

	public void desconectar(@Disposes Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
