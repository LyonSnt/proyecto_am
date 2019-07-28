package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import agendamedica.model.entities.Medico;
import agendamedica.model.entities.Paciente;
import agendamedica.model.entities.Turno;
import agendamedica.model.manager.ManagerMedico;
import agendamedica.model.manager.ManagerPaciente;
import agendamedica.model.manager.ManagerTurno;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class BeanTurno implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date fecha;
	private Integer idPaciente;
	private Integer idMedico;
	@EJB
	private ManagerMedico managerMedico;
	@EJB
	private ManagerPaciente managerPaciente;
	@EJB
	private ManagerTurno managerTurno;

	private Turno turnoTmp;
	private boolean turnoTmpGuardada;
	private Medico medico;
	private Paciente paciente;

	// Inyeccion de beans manejados:
	@Inject
	private BeanLogin beanLogin;

	public BeanTurno() {

	}

	@PostConstruct
	private void iniciar() {
		// TODO Auto-generated method stub
		turnoTmp = new Turno();
		paciente = new Paciente();
	}

	/**
	 * Action para la creacion de una turno temporal en memoria. Hace uso del
	 * componente {@link agendamedica.model.manager.ManagerTurno ManagerTurno} de la
	 * capa model.
	 * 
	 * @return outcome para la navegacion.
	 */
	public void crearNuevaTurno() {
		turnoTmp = managerTurno.crearTurnoTmp();
		idPaciente = null;
		idMedico = null;
		
		turnoTmpGuardada = false;

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
			System.out.println("hola paciente" + turnoTmp);
			managerTurno.asignarPacienteTurnoTmp(turnoTmp, idPaciente);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void asignarMedico() {
		if (turnoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El Turno ya fue guardada.");
		}
		try {
			System.out.println("hola medico" + turnoTmp);
			managerTurno.asignarMedicoTurnoTmp(turnoTmp, idMedico);
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
			System.out.println("examinar turno hhhhhhh->   " + turnoTmp.getUsuario());
			managerTurno.guardarTurnoTemporal(beanLogin.getCodigoUsuario(), turnoTmp);
			turnoTmpGuardada = true;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

		return "";
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve un listado de componentes SelectItem a partir de un listado de
	 * {@link agendamedica.model.dao.entities.Paciente Paciente}.
	 * 
	 * @return listado de SelectItems de pacientes.
	 */
	public List<SelectItem> getListaPacienteSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<Paciente> listadoPaciente = managerPaciente.findAllPacientes();

		for (Paciente p : listadoPaciente) {
			SelectItem item = new SelectItem(p.getIdPaciente(), p.getApellidoPaciente() + " " + p.getNombrePaciente());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	/**
	 * Devuelve un listado de componentes SelectItem a partir de un listado de
	 * {@link agendamedica.model.dao.entities.Medico Medico}.
	 * 
	 * @return listado de SelectItems de medicos.
	 */
	public List<SelectItem> getListaMedicoSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<Medico> listadoMedico = managerMedico.findAllMedicoss();

		for (Medico m : listadoMedico) {
			SelectItem item = new SelectItem(m.getIdMedico(), m.getApellidoMedico() + " " + m.getNombreMedico());
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
