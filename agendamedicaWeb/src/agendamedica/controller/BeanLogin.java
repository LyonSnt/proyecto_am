package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import agendamedica.model.entities.Tipousuario;
import agendamedica.model.entities.Usuario;
import agendamedica.model.manager.ManagerLogin;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombreUsuario;
	private String clave;
	private String tipoUsuario;

	@EJB
	private ManagerLogin managerLogin;
	private List<Tipousuario> listaTipousuario;
	private Usuario usuario;

	@PostConstruct
	public void inicializar() {
		listaTipousuario = managerLogin.findAllTipoUsauarios();
	}

	public String actionLogin() {
		try {
			if (tipoUsuario.equals("1")) {
				System.out.println("Aqui esta el mensaje" + tipoUsuario);
				/// JSFUtil.crearMensajeInfo("Login correcto");
				// return "/admin/menu.xhtml";
				return "/admin/headerAdmin?faces-redirect=true";

				/*
				 * }else if(idUsua.equals("medico")) { return "medico/index"; } return
				 * "clientes/index";
				 */
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public String salirSistema() {
		System.out.println("salirSistema");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Tipousuario> getListaTipousuario() {
		return listaTipousuario;
	}

	public void setListaTipousuario(List<Tipousuario> listaTipousuario) {
		this.listaTipousuario = listaTipousuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
