package agendamedica.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.model.entities.Tipousuario;
import agendamedica.model.manager.ManagerTipo;
import agendamedica.view.util.JSFUtil;


import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanTipo implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerTipo managerTipo;
	private List<Tipousuario> listaTipo;
	private Tipousuario tipo;
	private boolean panelColapsado;
	private Tipousuario tipoSeleccionado;
	
	@PostConstruct
	private void inicializar() {
		listaTipo = managerTipo.findAllTipousuarios();
		tipo = new Tipousuario();
		panelColapsado = true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado =! panelColapsado;
	} 

	public void actionListerInsertarTipo() {
		try {
			
			managerTipo.insertarTipo(tipo);
			listaTipo = managerTipo.findAllTipousuarios();
			tipo = new Tipousuario();
			JSFUtil.crearMensajeInfo("Datos de tipo usuario insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarTipousuario(int id) {
		managerTipo.eliminarTipousuario(id);
		listaTipo = managerTipo.findAllTipousuarios();
		JSFUtil.crearMensajeInfo("Tipo de usuario Eliminado.");
	}

	public void actionListenerSeleccionarTipousuario(Tipousuario tipousuario) {
		tipoSeleccionado = tipousuario;
	}

	public void actionListenerActualizarTipousuario() {
		try {
//			Rol r=new Rol();
//			r.setIdRol(idrol);
//			user.setRol(r);
//			user.setEmpresa(new Empresa());
//			user.getEmpresa().setIdEmpresa(idem);
			managerTipo.actualizarTipousuario(tipoSeleccionado);
			listaTipo = managerTipo.findAllTipousuarios();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<Tipousuario> getListaTipo() {
		return listaTipo;
	}

	public void setListaTipo(List<Tipousuario> listaTipo) {
		this.listaTipo = listaTipo;
	}

	public Tipousuario getTipo() {
		return tipo;
	}

	public void setTipo(Tipousuario tipo) {
		this.tipo = tipo;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Tipousuario getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(Tipousuario tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	
	

}
