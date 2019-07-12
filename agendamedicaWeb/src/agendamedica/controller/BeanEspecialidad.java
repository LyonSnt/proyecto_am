package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.model.entities.Especialidad;
import agendamedica.model.manager.ManagerEspecialidad;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanEspecialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerEspecialidad managerEspecialidad;
	private List<Especialidad> listaEspecialidad;
	private Especialidad especialidad;
	private boolean panelColapsado;
	private Especialidad especialidadSeleccionado;

	@PostConstruct
	public void inicializar() {
		listaEspecialidad = managerEspecialidad.findAllEspecialidades();
		especialidad = new Especialidad();
		panelColapsado = true;

	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarEspecialidad() {
		try {
			managerEspecialidad.insertarEspecialidad(especialidad);
			listaEspecialidad = managerEspecialidad.findAllEspecialidades();
			especialidad = new Especialidad();
			JSFUtil.crearMensajeInfo("Datos de especialidad insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarEspecialidad(int id) {
		managerEspecialidad.eliminarEspecialidad(id);
		listaEspecialidad = managerEspecialidad.findAllEspecialidades();
		JSFUtil.crearMensajeInfo("Especialidad Eliminado.");
	}

	public void actionListenerSeleccionarEspecialidad(Especialidad especialidad) {
		especialidadSeleccionado = especialidad;
	}

	public void actionListenerActualizarEspecialidad() {
		try {
//			Rol r=new Rol();
//			r.setIdRol(idrol);
//			user.setRol(r);
//			user.setEmpresa(new Empresa());
//			user.getEmpresa().setIdEmpresa(idem);
			managerEspecialidad.actualizarEspecialidad(especialidadSeleccionado);
			listaEspecialidad = managerEspecialidad.findAllEspecialidades();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Especialidad> getListaEspecialidad() {
		return listaEspecialidad;
	}

	public void setListaEspecialidad(List<Especialidad> listaEspecialidad) {
		this.listaEspecialidad = listaEspecialidad;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Especialidad getEspecialidadSeleccionado() {
		return especialidadSeleccionado;
	}

	public void setEspecialidadSeleccionado(Especialidad especialidadSeleccionado) {
		this.especialidadSeleccionado = especialidadSeleccionado;
	}



}
