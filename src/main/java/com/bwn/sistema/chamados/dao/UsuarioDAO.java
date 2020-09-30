package com.bwn.sistema.chamados.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.bwn.sistema.chamados.enums.TipoUsuario;
import com.bwn.sistema.chamados.model.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Connection connection;
	private String sql;
	
	public List<Usuario> buscarTodos(TipoUsuario tipo) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		this.sql = "SELECT u.id, u.nome, u.tipo FROM usuario u where cast(u.tipo as varchar) in (?)";
		
		try(PreparedStatement statement = this.connection.prepareStatement(sql)) {		
			statement.setString(1, tipo.getTipo());
			
			try(ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					usuarios.add(
							new Usuario(result.getLong(1),
										result.getString(2),
										TipoUsuario.valueOf(result.getString(3))));
				}
			}
			
			this.connection.commit();
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return usuarios;
	}
	

}
