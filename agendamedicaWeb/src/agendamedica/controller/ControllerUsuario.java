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
	private String estado;
	private String fecharegistro;
	private Integer responsable;
	private Integer medico;

	@EJB
	private ManagerUsuario managerUsuario;
	private List<Usuario> listaUsuarios;
	private Usuario usuario;
	private boolean panelColapsado;
	private Usuario usuarioSeleccionado;
	
	@PostConstruct
	private void inicializar() {
		listaUsuarios = managerUsuario.findAllUsuario();
		usuario = new Usuario();
//		panelColapsado = true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado =! panelColapsado;
	} 
	
	public void actionListerInsertarUsuario() {
		try {
			
			managerUsuario.insertarUsuario(usuario);
			listaUsuarios = managerUsuario.findAllUsuario();
			usuario = new Usuario();
			JSFUtil.crearMensajeInfo("Datos de usuario insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerSeleccionarTipo(Usuario usuario) {
		usuarioSeleccionado = usuario;
	}

	public String actionLogin() {
		try {
			boolean respuesta = managerUsuario.comprobarUsuario(idUsuario, clave);
			JSFUtil.crearMensajeInfo("Login correcto");
			// verificamos si el acceso es con admin:
			if (idUsuario.equals("admin")) {
				///JSFUtil.crearMensajeInfo("Login correcto");
				//return "/admin/menu.xhtml";
				return "/admin/menu?faces-redirect=true";
				
			}else if(idUsuario.equals("medico")) {
				return "medico/index";
			}
			return "clientes/index";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	

	public String getIdUsuario() {
		return idUsuario;
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

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Integer getResponsable() {
		return responsable;
	}

	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}

	public Integer getMedico() {
		return medico;
	}

	public void setMedico(Integer medico) {
		this.medico = medico;
	}

	public Integer getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(Integer tipousuario) {
		this.tipousuario = tipousuario;
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
