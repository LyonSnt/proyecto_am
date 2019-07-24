package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import agendamedica.view.util.JSFUtil;

import agendamadica.model.dto.LoginDTO;
import agendamedica.model.manager.ManagerAuditoria;
import agendamedica.model.manager.ManagerSeguridad;
import agendamedica.model.util.ModelUtil;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoUsuario;
	private String clave;
	private String tipoUsuario;
	private boolean acceso;
	@EJB
	private ManagerSeguridad managerSeguridad;
	@EJB
	private ManagerAuditoria managerAuditoria;
	private LoginDTO loginDTO;

	@PostConstruct
	public void inicializar() {
		loginDTO = new LoginDTO();
	}

	/**
	 * Action que permite el acceso al sistema.
	 * 
	 * @return
	 */
	public String accederSistema() {
		acceso = false;
		try {
			loginDTO = managerSeguridad.accederSistema(codigoUsuario, clave);
			// verificamos el acceso del usuario:
			tipoUsuario = loginDTO.getTipoUsuario();
			// redireccion dependiendo del tipo de usuario:
			managerAuditoria.crearEvento(codigoUsuario, this.getClass(), "accederSistema", "Acceso a login");
			return loginDTO.getRutaAcceso() + "?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

	/**
	 * Finaliza la sesion web del usuario.
	 * 
	 * @return
	 */
	public String salirSistema() {
		System.out.println("salirSistema");
		try {
			managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(), this.getClass(), "salisSistema", "Cerrar sesion");
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.html?faces-redirect=true";
	}

	public void actionVerificarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath = ec.getRequestPathInfo();
		try {
			// si no paso por login:
			if (loginDTO == null || ModelUtil.isEmpty(loginDTO.getRutaAcceso())) {
				ec.redirect(ec.getRequestContextPath() + "/index.html");
			} else {
				// validar las rutas de acceso:
				if (requestPath.contains("/admin") && loginDTO.getRutaAcceso().startsWith("/admin"))
					return;
				if (requestPath.contains("/medico") && loginDTO.getRutaAcceso().startsWith("/medico"))
					return;
				if (requestPath.contains("/secretaria") && loginDTO.getRutaAcceso().startsWith("/secretaria"))
					return;
				// caso contrario significa que hizo login pero intenta acceder a ruta no
				// permitida:
				ec.redirect(ec.getRequestContextPath() + "/index.html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isAcceso() {
		return acceso;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
