package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.model.entities.Responsableturno;
import agendamedica.model.manager.ManagerResponsable;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanResponsable implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cedula;
	
	@EJB
	private ManagerResponsable managerResponsable;
	private List<Responsableturno> listaResponsable;
	private Responsableturno responsable;
	private boolean panelColapsado;
	private Responsableturno responsableSeleccionado;

	@PostConstruct
	private void inicializar() {
		listaResponsable = managerResponsable.findAllResponsableturno();
		responsable = new Responsableturno();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListerInsertarResponsable() {
		try {

			managerResponsable.insertarResponsableturno(responsable);
			listaResponsable = managerResponsable.findAllResponsableturno();
			responsable = new Responsableturno();
			JSFUtil.crearMensajeInfo("Datos de responsable turno insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarResponsableturno(int id) {
		managerResponsable.eliminarResponsableturno(id);
		listaResponsable = managerResponsable.findAllResponsableturno();
		JSFUtil.crearMensajeInfo("Responsable Eliminado.");
	}

	public void actionListenerSeleccionarResponsableturno(Responsableturno responsable) {
		responsableSeleccionado = responsable;
	}

	public void actionListenerActualizarResponsableturno() {
		try {
			managerResponsable.actualizarResponsableturno(responsableSeleccionado);
			listaResponsable = managerResponsable.findAllResponsableturno();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public List<Responsableturno> getListaResponsable() {
		return listaResponsable;
	}

	public void setListaResponsable(List<Responsableturno> listaResponsable) {
		this.listaResponsable = listaResponsable;
	}

	public Responsableturno getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsableturno responsable) {
		this.responsable = responsable;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Responsableturno getResponsableSeleccionado() {
		return responsableSeleccionado;
	}

	public void setResponsableSeleccionado(Responsableturno responsableSeleccionado) {
		this.responsableSeleccionado = responsableSeleccionado;
	}
	
	

}
