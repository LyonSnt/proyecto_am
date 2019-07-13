package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.model.entities.Medico;
import agendamedica.model.manager.ManagerMedico;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanMedico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerMedico managerMedico;
	private List<Medico> listaMedico;
	private Medico medico;
	private boolean panelColapsado;
	private Medico medicoSeleccionado;
	
	@PostConstruct
	private void inicializar() {
		listaMedico = managerMedico.findAllMedicos();
		medico = new Medico();
		panelColapsado = true;
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListerInsertarMedico() {
		try {

			managerMedico.insertarMedico(medico);
			listaMedico = managerMedico.findAllMedicos();
			medico = new Medico();
			JSFUtil.crearMensajeInfo("Datos del medico insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarMedico(int id) {
		managerMedico.eliminarMedico(id);
		listaMedico = managerMedico.findAllMedicos();
		JSFUtil.crearMensajeInfo("Medico Eliminado.");
	}

	public void actionListenerSeleccionarMedico(Medico medico) {
		medicoSeleccionado = medico;
	}

	public void actionListenerActualizarMedico() {
		try {
			managerMedico.actualizarMedico(medicoSeleccionado);
			listaMedico = managerMedico.findAllMedicos();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Medico> getListaMedico() {
		return listaMedico;
	}

	public void setListaMedico(List<Medico> listaMedico) {
		this.listaMedico = listaMedico;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Medico getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	public void setMedicoSeleccionado(Medico medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}
	
	

}
