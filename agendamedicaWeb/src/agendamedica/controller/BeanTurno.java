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
	private String cedulaPaciente;
	private String cedulaMedico;
	private Date fecha;
	private Integer cedulaPl;
	@EJB
	private ManagerMedico managerMedico;
	@EJB
	private ManagerPaciente managerPaciente;
	@EJB
	private ManagerTurno managerTurno;

	private Turno turnoTmp;
	private boolean turnoTmpGuardada;
	private Turno turno;
	private Medico medico;

	// Inyeccion de beans manejados:
	@Inject
	private BeanLogin beanLogin;

	public BeanTurno() {

	}
	@PostConstruct
	private void iniciar() {
		// TODO Auto-generated method stub
		turno=new Turno();
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
		//turnoTmp.setFechaTurno(new Date());
		cedulaPaciente = null;
		cedulaMedico= null;
		turnoTmpGuardada = false;
		return "";
	}
	
	public void asignarFecha() {
		fecha = managerTurno.fechaT();
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
	
	
	
	public String getCedulaMedico() {
		return cedulaMedico;
	}

	public void setCedulaMedico(String cedulaMedico) {
		this.cedulaMedico = cedulaMedico;
	}
	
	

	public Integer getCedulaPl() {
		return cedulaPl;
	}

	public void setCedulaPl(Integer cedulaPl) {
		this.cedulaPl = cedulaPl;
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
		List<Paciente> listadoPaciente = managerPaciente.findAllPaciente();

		for (Paciente p : listadoPaciente) {
			SelectItem item = new SelectItem(p.getCedulaPaciente(),
					p.getApellidoPaciente() + " " + p.getNombrePaciente());
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
		List<Medico> listadoMedico = managerMedico.findAllMedicos();

		for (Medico m : listadoMedico) {
			SelectItem item = new SelectItem(m.getCedulaMedico(),
					m.getApellidoMedico() + " " + m.getNombreMedico());
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}
