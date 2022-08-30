package com.bwn.sistema.chamados.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.bwn.sistema.chamados.enums.StatusChamado;
import com.bwn.sistema.chamados.enums.TipoUsuario;
import com.bwn.sistema.chamados.model.Chamado;
import com.bwn.sistema.chamados.model.Usuario;

public class ChamadoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Connection connection;
	private String sql;
	
	@Inject
	public ChamadoDAO(Connection connection) {
        this.connection = connection;
    }

	public boolean salvar(Chamado chamado) {

		boolean salvou = false;
		sql = "INSERT INTO chamado(titulo, descricao, cliente_id, data, status)"
				+  " VALUES(?, ?, ?, ?, ?)";

		try(PreparedStatement statement = this.connection.prepareStatement(sql)) {

			statement.setString(1, chamado.getTitulo());
			statement.setString(2, chamado.getDescricao());
			statement.setLong(3, chamado.getCliente().getId());	
			statement.setDate(4, Date.valueOf(chamado.getData()));
			statement.setString(5, chamado.getStatus().toString());

			statement.executeUpdate();
			this.connection.commit();

			salvou = true;
		} catch (SQLException e) {
			try {
				this.connection.rollback();
			} catch (SQLException ex) {
				new RuntimeException(ex);
			}
			throw new RuntimeException(e);
		}

		return salvou;
	}

	public void atualizar(Chamado chamado) {

		sql = "UPDATE chamado SET funcionario_id = ?, observacao = ?, status = ?"
				+  " WHERE id = ?";

		try(PreparedStatement statement = this.connection.prepareStatement(sql)) {

			statement.setLong(1, chamado.getFuncionario().getId());
			statement.setString(2, chamado.getObservacao());
			statement.setString(3, chamado.getStatus().toString());	
			statement.setLong(4, chamado.getId());

			statement.executeUpdate();
			this.connection.commit();

		} catch (SQLException e) {
			try {
				this.connection.rollback();
			} catch (SQLException ex) {
				new RuntimeException(ex);
			}
			throw new RuntimeException(e);
		}

	}
		public List<Chamado> buscarTodos() {
			List<Chamado> chamados = new ArrayList<Chamado>();
			this.sql = "SELECT c.id, c.titulo, c.descricao, c.data, c.status, c.observacao, c.cliente_id,"
					+ " u.nome, u.tipo FROM chamado c"
					+ " INNER JOIN usuario u ON c.cliente_id = u.id";

			try(PreparedStatement statement = this.connection.prepareStatement(sql)) {
				this.connection.commit();

				try(ResultSet result = statement.executeQuery()) {
					
					while (result.next()) {
						chamados.add(
						        Chamado.builder().id(result.getLong("id"))
						                         .titulo(result.getString("titulo"))
						                         .descricao(result.getString("descricao"))
						                         .data(result.getDate("data").toLocalDate())
						                         .cliente(new Usuario(result.getLong("cliente_id"), result.getString("nome"), 
						                                                 TipoUsuario.valueOf(result.getString("tipo"))))
						                         .status(StatusChamado.valueOf(result.getString("status")))
						                         .observacao(result.getString("observacao"))
						               .build());
					}
				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			return chamados;
		}

		public List<Chamado> buscarTodosNaoFinalizados() {
			List<Chamado> chamados = new ArrayList<Chamado>();
			this.sql ="SELECT c.id, c.titulo, c.descricao, c.data, c.status, c.observacao, c.cliente_id,"
					+ " u.nome, u.tipo FROM chamado c"
					+ " INNER JOIN usuario u ON c.cliente_id = u.id where NOT cast(c.status as varchar) in (?)";

			try(PreparedStatement statement = this.connection.prepareStatement(sql)) {
				statement.setString(1, StatusChamado.FINALIZADO.toString());

				try(ResultSet result = statement.executeQuery()) {

					while (result.next()) {
						chamados.add(
		                        Chamado.builder().id(result.getLong("id"))
                                                .titulo(result.getString("titulo"))
                                                .descricao(result.getString("descricao"))
                                                .data(result.getDate("data").toLocalDate())
                                                .cliente(new Usuario(result.getLong("cliente_id"), result.getString("nome"), 
                                                                        TipoUsuario.valueOf(result.getString("tipo"))))
                                                .status(StatusChamado.valueOf(result.getString("status")))
                                        .build());
					}

				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			return chamados;
		}

	}
