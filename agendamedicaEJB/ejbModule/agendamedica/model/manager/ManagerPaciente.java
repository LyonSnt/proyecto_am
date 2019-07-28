package agendamedica.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Paciente;

/**
 * Session Bean implementation class ManagerPaciente
 */
@Stateless
@LocalBean
public class ManagerPaciente {
	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO managerDAO;

	/**
	 * Default constructor.
	 */
	public ManagerPaciente() {

	}

	/**
	 * Metodo finder para la consulta de paciente. Hace uso del componente
	 * {@link agendamedica.model.manager.ManagerDAO ManagerDAO} de la capa model.
	 * 
	 * @return listado de pacientes ordenados por apellidos.
	 */
	@SuppressWarnings("unchecked")
	public List<Paciente> findAllPacientes() {
		return managerDAO.findAll(Paciente.class, "o.apellidoPaciente");
	}

	/**
	 * Metodo finder para la consulta de un paciente especifico.
	 * 
	 * @param cedula cedula del paciente que se desea buscar.
	 * @return datos del paciente.
	 * @throws Exception
	 */
	public Paciente findPacienteByIds(Integer id) throws Exception {
		Paciente paciente = null;
		try {
			paciente = (Paciente) managerDAO.findById(Paciente.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar paciente: " + e.getMessage());
		}
		return paciente;
	}

	public List<Paciente> findAllPaciente() {
		String consulta = "select p from Paciente p ";
		Query q = em.createQuery(consulta, Paciente.class);
		return q.getResultList();
	}

	public List<Paciente> findPacienteByCedula(String cedulaPaciente) {
		String consulta = "SELECT p FROM Paciente p where p.cedulaPaciente='" + cedulaPaciente + "'";
		Query q = em.createQuery(consulta, Paciente.class);
		return q.getResultList();
	}

	public void insertarPaciente(Paciente paciente) throws Exception {
		if (findPacienteByCedula(paciente.getCedulaPaciente()).size() > 0)
			throw new Exception("Ya existe la cedula indicada");
		em.persist(paciente);
	}

	public Paciente findPacienteById(int id) {
		return em.find(Paciente.class, id);
	}

	public void eliminarPaciente(int id) {
		Paciente paciente = findPacienteById(id);
		if (paciente != null)
			em.remove(paciente);
	}

	public void actualizarPaciente(Paciente paciente) throws Exception {
		Paciente p = findPacienteById(paciente.getIdPaciente());
		if (p == null)
			throw new Exception("No existe el estudiante con la cédula especificada.");
		p.setApellidoPaciente(paciente.getApellidoPaciente());
		p.setNombrePaciente(paciente.getNombrePaciente());
		p.setCelularPaciente(paciente.getCelularPaciente());
		p.setDireccionPaciente(paciente.getDireccionPaciente());
		p.setCorreoPaciente(paciente.getCorreoPaciente());
		em.merge(p);

	}

}
