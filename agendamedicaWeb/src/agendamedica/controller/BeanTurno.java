package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import agendamedica.model.entities.Estado;
import agendamedica.model.entities.Medico;
import agendamedica.model.entities.Paciente;
import agendamedica.model.entities.Turno;
import agendamedica.model.entities.Usuario;
import agendamedica.model.manager.ManagerEstado;
import agendamedica.model.manager.ManagerMedico;
import agendamedica.model.manager.ManagerPaciente;
import agendamedica.model.manager.ManagerTurno;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private Integer idEstado;
	private Integer idTurno;
	private BigDecimal valorTurno;
	private String idUsuario;
	private List<Turno> listaTurno;
	private List<Paciente> listaPaciente;
	private List<Medico> listaMedico;
	private List<Estado> listaEstado;
	private List<Usuario> listaUsuario;

	@EJB
	private ManagerMedico managerMedico;
	@EJB
	private ManagerPaciente managerPaciente;
	@EJB
	private ManagerTurno managerTurno;
	@EJB
	private ManagerEstado managerEstado;

	private Turno turnoTmp;
	private Turno turno;
	private boolean turnoTmpGuardada;
	private Medico medico;
	private Paciente paciente;
	private Estado estado;
	private Usuario usuario;

	// Inyeccion de beans manejados:
	@Inject
	private BeanLogin beanLogin;

	public BeanTurno() {

	}

	@PostConstruct
	private void iniciar() {
		// TODO Auto-generated method stub
		listaEstado = managerTurno.findAllEstado();
		listaMedico = managerTurno.findAllMedico();
		listaPaciente = managerTurno.findAllPaciente();
		listaUsuario = managerTurno.findAllUsuario();

		turnoTmp = new Turno();
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
		idEstado = null;

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

	public void asignarEstado() {
		if (turnoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El Turno ya fue guardada.");
		}
		try {
			System.out.println("hola Estado" + turnoTmp);
			managerTurno.asignarEstadoTurnoTmp(turnoTmp, idEstado);
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

			managerTurno.guardarTurnoTemporal(beanLogin.getCodigoUsuario(), turnoTmp, idPaciente, idMedico, idEstado);
			turnoTmpGuardada = true;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

		return "";
	}

	public void actionListenerInsertarTurnos() {

		try {

			
			listaTurno = managerTurno.findAllTurnos();
			turno = new Turno();
			JSFUtil.crearMensajeInfo("Turno Guardado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
			System.out.println("error en bean" + e.getMessage());
		}
	}
	
	public void actionListenerInsertarTurno() {

		try {

			managerTurno.insertarTurno(beanLogin.getCodigoUsuario(), idPaciente, idMedico, idEstado, valorTurno, fecha);
			listaTurno = managerTurno.findAllTurnos();
			turno = new Turno();
			JSFUtil.crearMensajeInfo("Turno Guardado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
			System.out.println("error en bean" + e.getMessage());
		}
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

	/**
	 * Devuelve un listado de componentes SelectItem a partir de un listado de
	 * {@link agendamedica.model.dao.entities.Estado Estado}.
	 * 
	 * @return listado de SelectItems de estados.
	 */
	public List<SelectItem> getListaEstadoSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<Estado> listadoEstado = managerEstado.findAllEstado();

		for (Estado e : listadoEstado) {
			SelectItem item = new SelectItem(e.getIdEstado(), e.getNombreEstado());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public List<Turno> getListaTurno() {
		return listaTurno;
	}

	public void setListaTurno(List<Turno> listaTurno) {
		this.listaTurno = listaTurno;
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

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Integer idTurno) {
		this.idTurno = idTurno;
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

	public BigDecimal getValorTurno() {
		return valorTurno;
	}

	public void setValorTurno(BigDecimal valorTurno) {
		this.valorTurno = valorTurno;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public List<Medico> getListaMedico() {
		return listaMedico;
	}

	public void setListaMedico(List<Medico> listaMedico) {
		this.listaMedico = listaMedico;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
