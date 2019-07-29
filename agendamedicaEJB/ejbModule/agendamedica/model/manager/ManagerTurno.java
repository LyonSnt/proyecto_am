package agendamedica.model.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Estado;
import agendamedica.model.entities.Medico;
import agendamedica.model.entities.Paciente;
import agendamedica.model.entities.Parametro;
import agendamedica.model.entities.Turno;
import agendamedica.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerTurno
 */
@Stateless
@LocalBean
public class ManagerTurno {
	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerSeguridad managerSeguridad;
	@EJB
	private ManagerPaciente managerPaciente;
	@EJB
	private ManagerMedico managerMedico;
	@EJB
	private ManagerParametro managerParametros;
	@EJB
	private ManagerEstado managerEstado;

	public ManagerTurno() {

	}

	/**
	 * Retorna el valor actual del contador de turno. Este contador es un parametro
	 * del sistema.
	 * 
	 * @return ultimo valor del contador de turno
	 * @throws Exception
	 */
	private int getContadorTurno() throws Exception {
		return managerParametros.getValorParametroInteger("cont_turnos");
	}

	/**
	 * Actualiza el valor del contador de turno.
	 * 
	 * @param nuevoContadorTurnos nuevo valor del contador.
	 * @throws Exception
	 */
	private void actualizarContTurnos(int nuevoContadorTurnos) throws Exception {
		managerParametros.actualizarParametro("cont_turnos", nuevoContadorTurnos);
	}
	// MANEJO DE FACTURAS:

	/**
	 * Metodo finder para la consulta de facturas. Hace uso del componente
	 * {@link agendamedica.model.manager.ManagerDAO ManagerDAO} de la capa model.
	 * 
	 * @return Listado de turnos ordenadas por fecha de emision y numero de turno.
	 */
	@SuppressWarnings("unchecked")
	public List<Turno> findAllTurno() {
		List<Turno> listado = managerDAO.findAll(Turno.class, "o.fecha_turno desc,o.id_turno desc");
		// recorremos los turnos cabecera para extraer los datos de los detalles:

		return listado;
	}

	/**
	 * Crea una nueva cabecera de turno temporal, para que desde el programa cliente
	 * pueda manipularla y llenarle con la informacion respectiva. Esta informacion
	 * solo se mantiene en memoria.
	 * 
	 * @return la nueva factura temporal.
	 */
	public Turno crearTurnoTmp() {
		Turno turnoTmp = new Turno();
		turnoTmp.setFechaTurno(new Date());
		turnoTmp.setValorTurno(new BigDecimal(20.00));
		
		
		return turnoTmp;
	}

	/**
	 * Asigna un paciente a un turno temporal.
	 * 
	 * @param turnoTmp   Turno temporal creada en memoria.
	 * @param idPaciente codigo del Paciente.
	 * @throws Exception
	 */
	public void asignarPacienteTurnoTmp(Turno turnoTmp, Integer idPaciente) throws Exception {

		Paciente paciente = new Paciente();
		if (idPaciente == null || idPaciente.toString().length() == 0)
			throw new Exception("Error debe especificar la id del paciente.");
		try {
			paciente = managerPaciente.findPacienteByIds(idPaciente);
			if (paciente == null)
				throw new Exception("Error al asignar Paciente.");
			turnoTmp.setPaciente(paciente);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al asignar paciente: " + e.getMessage());
		}
	}

	public void asignarMedicoTurnoTmp(Turno turnoTmp, Integer idMedico) throws Exception {

		Medico medico = new Medico();
		if (idMedico == null || idMedico.toString().length() == 0)
			throw new Exception("Error debe especificar la id del medico.");
		try {
			medico = managerMedico.findMedicoByIds(idMedico);
			if (medico == null)
				throw new Exception("Error al asignar Medico.");
			turnoTmp.setMedico(medico);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al asignar Medico: " + e.getMessage());
		}
	}

	public void asignarEstadoTurnoTmp(Turno turnoTmp, Integer idEstado) throws Exception {

		Estado estado = new Estado();
		if (idEstado == null || idEstado.toString().length() == 0)
			throw new Exception("Error debe especificar la id del Estado.");
		try {
			estado = managerEstado.findEstadoByIds(idEstado);
			if (estado == null)
				throw new Exception("Error al asignar Estado.");
			turnoTmp.setEstado(estado);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al asignar Estado: " + e.getMessage());
		}
	}

	/**
	 * Guarda en la base de datos una factura.
	 * 
	 * @param codigoUsuario Codigo del usuario que genera la factura.
	 * @param facturaCabTmp factura temporal creada en memoria.
	 * @throws Exception problemas ocurridos en la insercion.
	 */
	public void guardarTurnoTemporal(String idUsuario, Turno turnoTmp, int idPaciente, int idMedico, int idEstado)
			throws Exception {

		if (turnoTmp == null)
			throw new Exception("Debe crear una turno primero.");
		if (turnoTmp.getPaciente() == null)
			throw new Exception("Debe registrar el paciente.");
		if (turnoTmp.getMedico() == null)
			throw new Exception("Debe registrar el medico.");
		if (turnoTmp.getEstado() == null)
			throw new Exception("Debe registrar el estado.");

		Turno turno = new Turno();
		Paciente paciente = findPacienteById(idPaciente);
		Medico medico = findMedicoById(idMedico);
		Estado estado = findEstadoById(idEstado);

		// asignacion del usuario que crea el turno
		Usuario usuario = managerSeguridad.findUsuarioById(idUsuario);
		turnoTmp.setUsuario(usuario);
		
		// obtenemos el numero de la nueva turno:
		int contTurno;
		contTurno = getContadorTurno();
		contTurno++;
		turnoTmp.setIdTurno(contTurno);

		turno.setFechaTurno(turnoTmp.getFechaTurno());
		turno.setUsuario(turnoTmp.getUsuario());
		turno.setPaciente(paciente);
		turno.setMedico(medico);
		turno.setValorTurno(turnoTmp.getValorTurno());
		turno.setEstado(estado);
		turno.setIdTurno(turnoTmp.getIdTurno());
		

		// guardamos la turno completa en la bdd:
		//managerDAO.insertar(turno);
		em.persist(turno);

		// actualizamos los parametros contadores de turnos:
		actualizarContTurnos(contTurno);

		turno = null;

	}

	public List<Turno> findAllTurnos() {
		String consulta = "select t from Turno t ";
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}
	
	public List<Paciente> findAllPaciente() {
		String consulta = "select t from Paciente t ";
		Query q = em.createQuery(consulta, Paciente.class);
		return q.getResultList();
	}
	public List<Medico> findAllMedico() {
		String consulta = "select t from Medico t ";
		Query q = em.createQuery(consulta, Medico.class);
		return q.getResultList();
	}
	
	public List<Estado> findAllEstado() {
		String consulta = "select t from Estado t ";
		Query q = em.createQuery(consulta, Estado.class);
		return q.getResultList();
	}
	
	public List<Usuario> findAllUsuario() {
		String consulta = "select t from Usuario t ";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
	}
	
	

	public Paciente findPacienteById(int idPaciente) {
		Paciente paciente= em.find(Paciente.class, idPaciente);
		return paciente;
	}

	public Medico findMedicoById(int idMedico) {
		Medico medico= em.find(Medico.class, idMedico);
		return medico;
		
	}

	public Estado findEstadoById(int idEstado) {
		Estado estado= em.find(Estado.class, idEstado);
		return estado;
	}
	
	public Usuario findUsuarioById(String idUsuario) {
		Usuario usuario= em.find(Usuario.class, idUsuario);
		return usuario;
	}

	public void insertarTurno(String idUsuario, int idPaciente, int idMedico, int idEstado, BigDecimal valorTurno, Date fechaTurno)
			throws Exception {

		Turno turno = new Turno();
		Paciente paciente = findPacienteById(idPaciente);
		Medico medico = findMedicoById(idMedico);
		Estado estado = findEstadoById(idEstado);
		Usuario usuario= findUsuarioById(idUsuario);

		// asignacion del usuario que crea el turno
		//Usuario usuario = managerSeguridad.findUsuarioById(idUsuario);

		
		turno.setPaciente(paciente);
		turno.setMedico(medico);
		turno.setUsuario(usuario);
		turno.setEstado(estado);		
		turno.setFechaTurno(new Date());		
		turno.setValorTurno(new BigDecimal(20));
		
		
		Turno turnoTmp = new Turno();
			//turno.setIdTurno(idTurno);
		int contTurno;
		
			contTurno = getContadorTurno();
			contTurno++;
			
			turnoTmp.setIdTurno(contTurno);
			actualizarContTurnos(contTurno);

		
		
		em.persist(turno);
		
		
		
		
	}

	public void contador() {
		Turno turnoTmp = new Turno();
		int contTurno;
		try {
			contTurno = getContadorTurno();
			contTurno++;
			
			turnoTmp.setIdTurno(contTurno);
			actualizarContTurnos(contTurno);

		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
