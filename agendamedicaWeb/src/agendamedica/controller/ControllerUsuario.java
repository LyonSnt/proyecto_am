package agendamedica.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.model.manager.ManagerUsuario;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;

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

	
}
