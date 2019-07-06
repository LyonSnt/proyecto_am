package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.model.entities.Paciente;
import agendamedica.model.manager.ManagerPaciente;
import agendamedica.view.util.JSFUtil;
import jdk.nashorn.internal.runtime.JSONFunctions;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanPaciente implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPaciente managerPaciente;
	private List<Paciente> listaPacientes;
	private Paciente paciente;
	private boolean panelColapsado;
	private Paciente pacienteSeleccionado;
		
	@PostConstruct
	public void inicializar() {
		listaPacientes=managerPaciente.findAllPaciente();
		paciente=new Paciente();
		panelColapsado=true;
	}
	public void actionListenerColapsarPanel() {
		panelColapsado =! panelColapsado;
	} 
	public void actionListenerInsertarPaciente() {
		try {
			managerPaciente.insertarPaciente(paciente);
			listaPacientes=managerPaciente.findAllPaciente();
			paciente =new Paciente();
			JSFUtil.crearMensajeInfo("Datos del paciente insertada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarPaciente(String cedula) {
		managerPaciente.eliminarPaciente(cedula);
		listaPacientes=managerPaciente.findAllPaciente();
		JSFUtil.crearMensajeInfo("Paciente Eliminado");
		
	}
	
	public void actionListenerSeleccionarPaciente(Paciente paciente) {
		pacienteSeleccionado = paciente;
	}
	
	public void actionListetenerActualizarestudiante() {
		try {
			managerPaciente.actualizarPaciente(pacienteSeleccionado);
			listaPacientes=managerPaciente.findAllPaciente();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Paciente> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<Paciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	public Paciente getPacienteSeleccionado() {
		return pacienteSeleccionado;
	}
	public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
		this.pacienteSeleccionado = pacienteSeleccionado;
	}
	
	
	
}
