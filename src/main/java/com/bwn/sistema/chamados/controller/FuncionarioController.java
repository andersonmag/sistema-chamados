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
import com.bwn.sistema.chamados.enums.StatusChamado;
import com.bwn.sistema.chamados.enums.TipoUsuario;
import com.bwn.sistema.chamados.model.Chamado;
import com.bwn.sistema.chamados.model.Usuario;

@Named(value = "MBFunc")
@ViewScoped
public class FuncionarioController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private ChamadoDAO chamadoDAO;
	private List<Chamado> chamados;
	private Chamado chamadoSelecionado;
	private Chamado chamado = new Chamado();
	private Long funId;
	private Usuario funcionario = new Usuario();

	public Long getFunId() {
		return funId;
	}

	public void setFunId(Long funId) {
		this.funId = funId;
	}

	public void atualizar() {

		FacesContext context = FacesContext.getCurrentInstance();

		if(funId != null) {
			System.err.println(chamadoSelecionado.getStatus() + " || " + chamadoSelecionado.getObservacao());
			if(chamadoSelecionado.getStatus().equals(StatusChamado.FINALIZADO) && chamadoSelecionado.getObservacao() == "") {
				context.addMessage(null, new FacesMessage("Preencha a Observação!"));
				return;
			}
			
			if(chamadoSelecionado.getStatus().equals(StatusChamado.EM_ANDAMENTO) && chamadoSelecionado.getObservacao() != "") {
				context.addMessage(null, new FacesMessage("Com a Observação preenchida, o Chamado deve ser finalizado!!"));
				return;
			}
			

			funcionario.setId(funId);
			chamadoSelecionado.setFuncionario(funcionario);
			this.chamadoDAO.atualizar(chamadoSelecionado);

			context.addMessage(null,
					new FacesMessage("Chamado Alterado!"));
			PrimeFaces.current().executeScript("PF('dialogForm').hide()");			
		}

	}

	public List<Chamado> getChamados() {

		if(chamados == null) {
			chamados = this.chamadoDAO.buscarTodosNaoFinalizados();;
		}
		return chamados;
	}

	public StatusChamado[] getStatus() {
		return StatusChamado.values();
	}

	public List<Usuario> getFuncionarios() {
		return this.usuarioDAO.buscarTodos(TipoUsuario.FUNCIONARIO);
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
	}

	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
}
