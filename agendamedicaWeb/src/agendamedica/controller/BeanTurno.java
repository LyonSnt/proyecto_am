package agendamedica.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import agendamedica.model.entities.Paciente;
import agendamedica.model.entities.Turno;
import agendamedica.model.manager.ManagerMedico;
import agendamedica.model.manager.ManagerPaciente;
import agendamedica.model.manager.ManagerTurno;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanTurno implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cedulaPaciente;
	private String cedulaMedico;
	@EJB
	private ManagerMedico managerMedico;
	@EJB
	private ManagerPaciente managerPaciente;
	@EJB
	private ManagerTurno managerTurno;

	private Turno turnoTmp;
	private boolean turnoTmpGuardada;
	private Turno turno;

	// Inyeccion de beans manejados:
	@Inject
	private BeanLogin beanLogin;

	public BeanTurno() {

	}

	/**
	 * Action para la creacion de una turno temporal en memoria. Hace uso del
	 * componente {@link agendamedica.model.manager.ManagerTurno ManagerTurno} de la
	 * capa model.
	 * 
	 * @return outcome para la navegacion.
	 */
	public String crearNuevaTurno() {
		turnoTmp = managerTurno.crearTurnoTmp();
		cedulaPaciente = null;
		turnoTmpGuardada = false;
		return "";
	}

	/**
	 * Action para asignar un paciente a la turno temporal actual. Hace uso del
	 * componente {@link agendamedica.model.manager.ManagerTurno ManagerTurno} de la
	 * capa model.
	 * 
	 * @return outcome para la navegacion.
	 */
	public void asignarPaciente() {
		if (turnoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El Turno ya fue guardada.");
		}
		try {
			managerTurno.asignarPacienteTurnoTmp(turnoTmp, cedulaPaciente);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	/**
	 * Action que almacena en la base de datos una turno temporal creada en memoria.
	 * Hace uso del componente {@link agendamedica.model.manager.ManagerTurno
	 * ManagerTurno} de la capa model.
	 * 
	 * @return outcome para la navegacion.
	 */
	public String guardarTurno() {
		if (turnoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El Turno ya fue guardada.");
			return "";
		}
		try {
			managerTurno.guardarTurnoTemporal(beanLogin.getCodigoUsuario(), turnoTmp);
			turnoTmpGuardada = true;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

		return "";
	}

	public String getCedulaPaciente() {
		return cedulaPaciente;
	}

	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	/**
	 * Devuelve un listado de componentes SelectItem a partir de un listado de
	 * {@link agendamedica.model.dao.entities.Paciente Paciente}.
	 * 
	 * @return listado de SelectItems de pacientes.
	 */
	public List<SelectItem> getListaPacienteSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<Paciente> listadoPaciente = managerPaciente.findAllPaciente();

		for (Paciente p : listadoPaciente) {
			SelectItem item = new SelectItem(p.getCedulaPaciente(),
					p.getApellidoPaciente() + " " + p.getNombrePaciente());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public Turno getTurnoTmp() {
		return turnoTmp;
	}

	public void setTurnoTmp(Turno turnoTmp) {
		this.turnoTmp = turnoTmp;
	}

	public boolean isTurnoTmpGuardada() {
		return turnoTmpGuardada;
	}

	public void setTurnoTmpGuardada(boolean turnoTmpGuardada) {
		this.turnoTmpGuardada = turnoTmpGuardada;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}
