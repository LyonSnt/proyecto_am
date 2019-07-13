package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.model.entities.Horario;
import agendamedica.model.manager.ManagerHorario;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanHorario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerHorario managerHorario;
	private List<Horario> listaHorario;
	private Horario horario;
	private boolean panelColapsado;
	private Horario horarioSeleccionado;
	
	@PostConstruct
	private void inicializar() {
		listaHorario = managerHorario.findAllHorarios();
		horario = new Horario();
		panelColapsado = true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado =! panelColapsado;
	} 

	public void actionListerInsertarHorario() {
		try {
			
			managerHorario.insertarHorario(horario);
			listaHorario = managerHorario.findAllHorarios();
			horario = new Horario();
			JSFUtil.crearMensajeInfo("Datos de horario insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarHorario(int id) {
		managerHorario.eliminarHorario(id);
		listaHorario = managerHorario.findAllHorarios();
		JSFUtil.crearMensajeInfo("Horario Eliminado.");
	}

	public void actionListenerSeleccionarHorario(Horario horario) {
		horarioSeleccionado = horario;
	}

	public void actionListenerActualizarHorario() {
		try {
//			Rol r=new Rol();
//			r.setIdRol(idrol);
//			user.setRol(r);
//			user.setEmpresa(new Empresa());
//			user.getEmpresa().setIdEmpresa(idem);
			managerHorario.actualizarHorario(horarioSeleccionado);
			listaHorario = managerHorario.findAllHorarios();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Horario> getListaHorario() {
		return listaHorario;
	}

	public void setListaHorario(List<Horario> listaHorario) {
		this.listaHorario = listaHorario;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Horario getHorarioSeleccionado() {
		return horarioSeleccionado;
	}

	public void setHorarioSeleccionado(Horario horarioSeleccionado) {
		this.horarioSeleccionado = horarioSeleccionado;
	}
	
	

}
