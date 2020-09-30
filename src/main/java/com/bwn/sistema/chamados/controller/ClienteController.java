package com.bwn.sistema.chamados.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.bwn.sistema.chamados.dao.ChamadoDAO;
import com.bwn.sistema.chamados.dao.UsuarioDAO;
import com.bwn.sistema.chamados.enums.TipoUsuario;
import com.bwn.sistema.chamados.model.Chamado;
import com.bwn.sistema.chamados.model.Usuario;

@Named(value = "MBCliente")
@ViewScoped
public class ClienteController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ChamadoDAO chamadoDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	private Chamado chamado = new Chamado();
	private List<Chamado> chamados;
	private Long clienteID;
	private Chamado chamadoSelecionado;
	private Usuario cliente = new Usuario();
	
	public Usuario getCliente() {
		return cliente;
	}
	
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	
	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}
	
	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
	}
	
	public Long getClienteID() {
		return clienteID;
	}

	public void setClienteID(Long clienteID) {
		this.clienteID = clienteID;
	}

	public List<Usuario> getClientes() {
		return this.usuarioDAO.buscarTodos(TipoUsuario.CLIENTE);
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(clienteID == null) {
			context.addMessage(null,
						new FacesMessage("Selecione um Usuario, por favor!"));
			return;
		}
		
		cliente.setId(clienteID);
		chamado.setCliente(cliente);
		this.chamadoDAO.salvar(chamado);
		
		context.addMessage(null, new FacesMessage("Chamado cadastrado!! Em breve será respondido"));
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");
		
		chamados = this.chamadoDAO.buscarTodos();
		chamado = new Chamado();
	}

	public List<Chamado> getChamados() {

		if(chamados == null) {
			chamados = this.chamadoDAO.buscarTodos();
		}

		return chamados;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

}
