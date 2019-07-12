package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.model.entities.Tipousuario;
import agendamedica.model.entities.Usuario;

import agendamedica.model.manager.ManagerUsuario;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ControllerUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idUsuario;
	private String nombreUsuario;
	private String clave;
	private Integer tipousuario;

	@EJB
	private ManagerUsuario managerUsuario;
	private List<Usuario> listaUsuarios;
	private List<Tipousuario> listaTipousuario;
	private Usuario usuario;
	private boolean panelColapsado;
	private Usuario usuarioSeleccionado;

	private String tipoUsuario;
	private Usuario user;

	@PostConstruct
	public void inicializar() {
		listaUsuarios = managerUsuario.findAllUsuarios();
		usuario = new Usuario();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

//	public void actionListerInsertarUsuarios() {
//		try {
//
//			managerUsuario.insertarUsuario(usuario);
//			listaUsuarios = managerUsuario.findAllUsuarios();
//			usuario = new Usuario();
//			JSFUtil.crearMensajeInfo("Datos de usuario insertados.");
//		} catch (Exception e) {
//			JSFUtil.crearMensajeError(e.getMessage());
//			e.printStackTrace();
//		}
//
//	}

	public void actionListenerInsertarUsuario() {
		try {

			Tipousuario tipo = new Tipousuario();
			tipo.setIdTipousuario(tipousuario);
			usuario.setTipousuario(tipo);
			managerUsuario.insertarUsuario(usuario);
			listaUsuarios = managerUsuario.findAllUsuarios();
			usuario = new Usuario();
			JSFUtil.crearMensajeInfo("Datos de usuario insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

//	public void actionListenerEliminarUsuario(String idUsuario) {
//		managerUsuario.eliminarUsuario(idUsuario);
//		listaUsuarios = managerUsuario.findAllUsuario();
//		JSFUtil.crearMensajeInfo("Usuario Eliminado");
//	}
//	
//	//ESTA PARTE ES PARA ACTUALIZAR
//	public void actionListenerSeleccionarUsuario(Usuario usuario) {
//		usuarioSeleccionado = usuario;
//	}
//
//	public void actionListenerActualizarUsuario() {
//		try {
//			managerUsuario.actualizarUsuario(usuarioSeleccionado);
//			listaUsuarios = managerUsuario.findAllUsuario();
//			JSFUtil.crearMensajeInfo("Datos actualizados");
//		} catch (Exception e) {
//			JSFUtil.crearMensajeError(e.getMessage());
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	

	public String getIdUsuario() {
		return idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	

//	public String getNombreUsuario() {
//		return nombreUsuario;
//	}
//
//	public void setNombreUsuario(String nombreUsuario) {
//		this.nombreUsuario = nombreUsuario;
//	}
//
//	public String getEstado() {
//		return estado;
//	}
//
//	public void setEstado(String estado) {
//		this.estado = estado;
//	}
//
//	public String getFecharegistro() {
//		return fecharegistro;
//	}
//
//	public void setFecharegistro(String fecharegistro) {
//		this.fecharegistro = fecharegistro;
//	}
//
//	public Integer getResponsable() {
//		return responsable;
//	}
//
//	public void setResponsable(Integer responsable) {
//		this.responsable = responsable;
//	}
//
//	public Integer getMedico() {
//		return medico;
//	}
//
//	public void setMedico(Integer medico) {
//		this.medico = medico;
//	}
//
//	public Integer getTipousuario() {
//		return tipousuario;
//	}
//
//	public void setTipousuario(Integer tipousuario) {
//		this.tipousuario = tipousuario;
//	}
//
//	

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Integer getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(Integer tipousuario) {
		this.tipousuario = tipousuario;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<Tipousuario> getListaTipousuario() {
		return listaTipousuario;
	}

	public void setListaTipousuario(List<Tipousuario> listaTipousuario) {
		this.listaTipousuario = listaTipousuario;
	}

	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

}
